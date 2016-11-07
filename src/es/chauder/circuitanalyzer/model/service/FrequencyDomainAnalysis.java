package es.chauder.circuitanalyzer.model.service;


import es.chauder.circuitanalyzer.model.model.analysis.*;
import es.chauder.circuitanalyzer.model.model.arithmetic.Complex;
import es.chauder.circuitanalyzer.model.model.arithmetic.ComplexEquationSystemSolution;
import es.chauder.circuitanalyzer.model.model.arithmetic.ComplexEquationVariable;
import es.chauder.circuitanalyzer.model.model.base.Circuit;
import es.chauder.circuitanalyzer.model.model.base.Connector;
import es.chauder.circuitanalyzer.model.model.base.Terminal;
import es.chauder.circuitanalyzer.model.model.base.Wire;
import es.chauder.circuitanalyzer.model.model.wiring.Instrument;
import es.chauder.circuitanalyzer.model.service.analysis.AnalysisTopologyGenerator;
import es.chauder.circuitanalyzer.model.service.analysis.FrequencyAnalysisGroupComputationGenerator;
import es.chauder.circuitanalyzer.model.service.arithmetic.ComplexEquationSystemSolver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rchauderlot on 1/3/16.
 */
public class FrequencyDomainAnalysis {

    /* params */
    private Circuit circuit;
    private List<Instrument> instruments;




    private AnalysisTopology analysisTopology = null;
    private Map<AnalysisGroup, AnalysisGroupSolution> equationSystemSolutionMap = null;

    private static class AnalysisGroupSolution {
        FrequencyAnalysisGroupComputation computation;
        ComplexEquationSystemSolution solution;
    }

    public FrequencyDomainAnalysis(Circuit circuit, List<Instrument> instruments) {
        super();
        this.circuit = circuit;
        this.instruments = instruments;
    }



    public void initialize() {

        if (circuit != null) {
            if (analysisTopology != null) {
                analysisTopology = AnalysisTopologyGenerator.createElectronicTopology(circuit, instruments);
            }
        }
    }


    public Map<Instrument, Map<Double, Double> > doFrequencyAnalysis(List<Double> frequencies) {

        Map<Instrument, Map<Double, Double> > frequencyAnalysisResult = new HashMap<Instrument, Map<Double, Double>>();
        if (analysisTopology != null) {
            for (Instrument instrument : instruments) {
                Map<Double, Double> frequencyVoltageMap = new HashMap<Double, Double>();
                for (Double frequency : frequencies) {
                    frequencyVoltageMap.put(frequency, getVoltageForInstrument(instrument, frequency));
                }
                frequencyAnalysisResult.put(instrument, frequencyVoltageMap);
            }
        }
        return frequencyAnalysisResult;
    }


    private double getVoltageForInstrument(Instrument instrument, double frequency) {


        for (AnalysisGroup analysisGroup : analysisTopology.getAnalysisGroups()) {
            if (analysisGroupContainsElement(analysisGroup, instrument.getLowerPotential()) &&
                    analysisGroupContainsElement(analysisGroup, instrument.getHigherPotential())) {

                AnalysisGroupSolution solution = computeCircuitSolutionIfNeeded(analysisGroup, frequency);
                Complex voltage = substituteVoltageForInstrumentWithCircuitSolution(instrument, solution);
                return voltage.abs();
            }
        }
        return 0;
    }

    private AnalysisGroupSolution computeCircuitSolutionIfNeeded(AnalysisGroup analysisGroup, double frequency) {
        AnalysisGroupSolution solution = equationSystemSolutionMap.get(analysisGroup);
        if (solution == null) {
            solution = new AnalysisGroupSolution();
            solution.computation =
                    FrequencyAnalysisGroupComputationGenerator.generateFrequencyAnalysisEquationSystem(frequency, analysisGroup);
            solution.solution = ComplexEquationSystemSolver.solve(solution.computation.getEquationSystem());
            equationSystemSolutionMap.put(analysisGroup, solution);
        }
        return solution;
    }

    private Complex substituteVoltageForInstrumentWithCircuitSolution(Instrument instrument,
                                                                      AnalysisGroupSolution solution) {

        Complex voltageSum = Complex.ZERO;
        AnalysisTargetTopology targetTopology = analysisTopology.getAnalysisTargetTopologyMap().get(instrument);
        for (Connector connector : targetTopology.getElements()) {
            if (connector instanceof Terminal) {
                Terminal terminal = (Terminal) connector;

                voltageSum = voltageSum.add(getVoltageForDeviceTerminal(terminal, solution));
            }
        }
        return voltageSum;
    }

    private Complex getVoltageForDeviceTerminal(Terminal terminal, AnalysisGroupSolution solution) {

        Complex voltage = Complex.ZERO;
        Branch branch = getBranchForTerminal(solution.computation.getAnalysisGroup(), terminal);

        if (branch != null) {
            ComplexEquationVariable variable = solution.computation.getBranchComplexVariableAssignmentMap().getVariableForBranch(branch);
            BranchDirection direction = solution.computation.getBranchComplexVariableAssignmentMap().getBranchDirectionForVariable(variable);


            Terminal firstDeviceTerminalInBranch = branch.getFirstDeviceTerminal(terminal.getDevice());
            if (terminal == firstDeviceTerminalInBranch) {
                if (direction.getStartingConnector() == branch.getFirstElement()) {
                    voltage = solution.solution.getSolution().get(variable);
                } else {
                    voltage.substract(solution.solution.getSolution().get(variable));
                }
            } else if (terminal.getDevice().getTerminals().contains(terminal)) {
                if (direction.getStartingConnector() == branch.getFirstElement()) {
                    voltage.substract(solution.solution.getSolution().get(variable));
                } else {
                    voltage = solution.solution.getSolution().get(variable);
                }
            }
        }

        return voltage;
    }

    private Branch getBranchForTerminal(AnalysisGroup analysisGroup, Terminal terminal) {
        Branch branch = null;
        for (Branch b : analysisGroup.getBranches()) {
            if (b.getElements().contains(terminal)) {
                branch = b;
                break;
            }
        }
        return branch;
    }


    private boolean analysisGroupContainsElement(AnalysisGroup analysisGroup, Wire wire) {
        for (Branch b : analysisGroup.getBranches()) {
            for (Connector c : b.getElements()) {
                if (c == wire) {
                    return true;
                }
            }
        }
        return false;
    }
}
