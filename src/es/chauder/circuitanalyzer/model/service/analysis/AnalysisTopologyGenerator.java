package es.chauder.circuitanalyzer.model.service.analysis;

import es.chauder.circuitanalyzer.model.model.analysis.AnalysisGroup;
import es.chauder.circuitanalyzer.model.model.analysis.AnalysisTopology;
import es.chauder.circuitanalyzer.model.model.base.Circuit;


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
            for (AnalysisGroup group : topology.getAnalysisGroups()) {
                NodeGenerator.generateNodes(group);
                NetworkGenerator.generateNetworks(group);
            }
        }
        return topology;
    }


}
