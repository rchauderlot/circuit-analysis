package es.chauder.circuitanalyzer.model.service.analysis;

import java.util.List;

import es.chauder.circuitanalyzer.model.model.analysis.*;
import es.chauder.circuitanalyzer.model.model.arithmetic.*;
import es.chauder.circuitanalyzer.model.model.base.Connector;
import es.chauder.circuitanalyzer.model.model.base.Device;
import es.chauder.circuitanalyzer.model.model.base.Terminal;

/**
 * Created by rchauderlot on 07/03/16.
 */
public class FrequencyAnalysisGroupComputationGenerator {


    public static FrequencyAnalysisGroupComputation generateFrequencyAnalysisEquationSystem(double frequency, AnalysisGroup analysisGroup) {

        FrequencyAnalysisGroupComputation groupComputation = new FrequencyAnalysisGroupComputation(analysisGroup);

        generateEquationsForNetworks(frequency,
                analysisGroup.getNetworks(),
                groupComputation.getBranchComplexVariableAssignmentMap(),
                groupComputation.getEquationSystem());
        generateEquationsForNodes(analysisGroup.getNodes(),
                groupComputation.getBranchComplexVariableAssignmentMap(),
                groupComputation.getEquationSystem());

        return groupComputation;
    }


    private static void generateEquationsForNetworks(double frequency,
                                                     List<Network> networks,
                                                     BranchComplexVariableAssignmentMap assignmentMap,
                                                     ComplexEquationSystem equationSystem) {

        if (networks != null) {
            for (Network network : networks) {
                ComplexEquation equation = generateEquationsForNetwork(frequency, network, assignmentMap);
                if (equation != null) {
                    equationSystem.addEquation(equation);
                }
            }
        }

    }


    private static ComplexEquation generateEquationsForNetwork(double frequency,
                                                               Network network,
                                                               BranchComplexVariableAssignmentMap assignmentMap) {


        ComplexEquation complexEquation = new ComplexEquation();

        if (network != null && network.getBranchEnds() != null && network.getBranchEnds().size() > 0) {

            for (Node.BranchEnd branchEnd : network.getBranchEnds()) {

                ComplexEquationVariable complexVariable = createVariableIfNeededForBranch(branchEnd, assignmentMap);
                boolean followingBranchEndDirection = isBranchEndFollowingVariableDirection(branchEnd, complexVariable, assignmentMap);
                generateTermsForBranch(frequency, branchEnd, complexVariable, complexEquation, followingBranchEndDirection);
            }
        }

        return complexEquation;
    }


    private static void generateTermsForBranch(double frequency,
                                               Node.BranchEnd branchEnd,
                                               ComplexEquationVariable variable,
                                               ComplexEquation complexEquation,
                                               boolean followingBranchEndDirection) {

        for (Connector connector : branchEnd.branch.getElements()) {
            if (connector instanceof Terminal) {
                Terminal terminal = (Terminal) connector;
                if (terminal.getDevice() != null) {
                    generateTermForDeviceTerminalVoltage(terminal, frequency, complexEquation, followingBranchEndDirection);
                    generateTermForDeviceTerminalImpedance(terminal, frequency, variable, complexEquation, followingBranchEndDirection);
                }
            }
        }


    }


    private static void generateTermForDeviceTerminalVoltage(Terminal terminal,
                                                     double frequency,
                                                     ComplexEquation complexEquation,
                                                     boolean followingBranchEndDirection) {

        Device device = terminal.getDevice();
        Complex voltage = device.getVoltage(frequency);
        if (voltage != null && terminal.getDevice().getTerminals().size() == 2) {
            if ((terminal == terminal.getDevice().getTerminals().get(0) && followingBranchEndDirection) ||
                    (terminal == terminal.getDevice().getTerminals().get(1) && !followingBranchEndDirection) ) {
                complexEquation.addTerm(voltage, null);
            } else {
                complexEquation.addTerm(Complex.ZERO.substract(voltage), null);
            }
        }
    }



    private static void generateTermForDeviceTerminalImpedance(Terminal terminal,
                                                       double frequency,
                                                       ComplexEquationVariable variable,
                                                       ComplexEquation complexEquation,
                                                       boolean followingBranchEndDirection) {


        Device device = terminal.getDevice();
        Complex impedance = device.getImpedance(frequency);

        if (impedance != null && terminal.getDevice().getTerminals().size() == 2) {
            if ((terminal == terminal.getDevice().getTerminals().get(0) && followingBranchEndDirection) ||
                    (terminal == terminal.getDevice().getTerminals().get(1) && !followingBranchEndDirection) ) {
                complexEquation.addTerm(impedance, variable);
            } else {
                complexEquation.addTerm(Complex.ZERO.substract(impedance), variable);
            }
        }
    }



    private static void generateEquationsForNodes(List<Node> nodes,
                                                                   BranchComplexVariableAssignmentMap assignmentMap,
                                                                   ComplexEquationSystem equationSystem) {
        if (nodes != null) {
            for (Node node : nodes) {
                ComplexEquation equation = generateEquationForNode(node, assignmentMap);
                if (equation != null) {
                    equationSystem.addEquation(equation);
                }
            }
        }

    }

    private static ComplexEquation generateEquationForNode(Node node, BranchComplexVariableAssignmentMap assignmentMap) {

        ComplexEquation complexEquation = new ComplexEquation();
        if (node != null && node.getBranchEnds() != null) {
            for (Node.BranchEnd branchEnd : node.getBranchEnds()) {
                ComplexEquationVariable complexVariable = createVariableIfNeededForBranch(branchEnd, assignmentMap);
                boolean followingBranchEndDirection = isBranchEndFollowingVariableDirection(branchEnd, complexVariable, assignmentMap);
                if (followingBranchEndDirection) {
                    complexEquation.addTerm(new Complex(1, 0), complexVariable);
                } else {
                    complexEquation.addTerm(new Complex(-1, 0), complexVariable);
                }
            }
        }

        return complexEquation;
    }

    private static ComplexEquationVariable createVariableIfNeededForBranch(Node.BranchEnd branchEnd,
                                                                           BranchComplexVariableAssignmentMap assignmentMap) {
        ComplexEquationVariable complexVariable = assignmentMap.getVariableForBranch(branchEnd.branch);
        if (complexVariable == null) {
            complexVariable = new ComplexEquationVariable(assignmentMap.getVariableCount());
            assignmentMap.addVariableAndBranchEnd(complexVariable, branchEnd);
        }
        return complexVariable;
    }

    private static boolean isBranchEndFollowingVariableDirection(Node.BranchEnd branchEnd,
                                                                 ComplexEquationVariable complexVariable,
                                                                 BranchComplexVariableAssignmentMap assignmentMap) {

        boolean followingBranchEndDirection = true;
        if (branchEnd != null && branchEnd.branch != null && complexVariable != null) {
            followingBranchEndDirection = assignmentMap.getBranchEndForVariable(complexVariable) == branchEnd;
        }
        return followingBranchEndDirection;
    }
}
