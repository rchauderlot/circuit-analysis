package es.chauder.circuitanalyzer.model.service;

import es.chauder.circuitanalyzer.model.model.analysis.AnalysisGroup;
import es.chauder.circuitanalyzer.model.model.base.*;
import es.chauder.circuitanalyzer.model.model.analysis.Branch;
import es.chauder.circuitanalyzer.model.model.analysis.AnalysisTopology;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by rchauderlot on 1/12/15.
 */
public class AnalysisTopologyGenerator {

    public static AnalysisTopology createElectronicTopology(Circuit circuit) {
        AnalysisTopology topology = new AnalysisTopology();
        topology.setCircuit(circuit);
        if (circuit != null) {
            BranchGenerator.generateBranches(topology);
            AnalysisGroupGenerator.generateAnalysisGroups(topology);
            generateNetworks(topology);
        }
        return topology;
    }




    private static void generateNetworks(AnalysisTopology topology) {





    }
}
