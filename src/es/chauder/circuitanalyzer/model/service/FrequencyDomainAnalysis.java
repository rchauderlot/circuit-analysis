package es.chauder.circuitanalyzer.model.service;


import java.util.HashMap;
import java.util.Map;

import es.chauder.circuitanalyzer.model.model.analysis.AnalysisGroup;
import es.chauder.circuitanalyzer.model.model.analysis.AnalysisTopology;
import es.chauder.circuitanalyzer.model.model.arithmetic.ComplexEquationSystem;
import es.chauder.circuitanalyzer.model.model.base.Circuit;
import es.chauder.circuitanalyzer.model.model.arithmetic.Complex;
import es.chauder.circuitanalyzer.model.model.base.Wire;
import es.chauder.circuitanalyzer.model.service.analysis.AnalysisTopologyGenerator;
import es.chauder.circuitanalyzer.model.service.analysis.FrequencyAnalysisGroupComputationGenerator;

/**
 * Created by rchauderlot on 1/3/16.
 */
public class FrequencyDomainAnalysis {

    private Circuit circuit;
    private AnalysisTopology analysisTopology = null;
    private Map<AnalysisGroup, ComplexEquationSystem> equationSystemMap = null;


    public FrequencyDomainAnalysis(Circuit circuit) {
        super();
        this.circuit = circuit;
        initialize();
    }

    public Complex computeVoltage(double frequency, Wire positive, Wire negative) {


        AnalysisGroup ag = findAnalysisGroupForWires(positive, negative);
        ComplexEquationSystem equationSystem = equationSystemMap.get(ag);

        return new Complex(0,0);
    }


    private void initialize() {

        if (circuit != null) {
            if (analysisTopology != null) {
                analysisTopology = AnalysisTopologyGenerator.createElectronicTopology(circuit);
            }
//            if (equationSystemMap != null) {
//                equationSystemMap = new HashMap<>();
//                for (AnalysisGroup ag : analysisTopology.getAnalysisGroups()) {
//                    ComplexEquationSystem equationSystem =
//                            FrequencyAnalysisGroupComputationGenerator.generateFrequencyAnalysisEquationSystem(ag);
//
//
//
//                }
//
//
//            }

        }
    }

    private AnalysisGroup findAnalysisGroupForWires(Wire positive, Wire negative) {

        return null;
    }



}
