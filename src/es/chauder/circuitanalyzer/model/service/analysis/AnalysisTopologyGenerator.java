package es.chauder.circuitanalyzer.model.service.analysis;

import java.util.List;
import java.util.Map;

import es.chauder.circuitanalyzer.model.model.analysis.AnalysisGroup;
import es.chauder.circuitanalyzer.model.model.analysis.AnalysisTargetTopology;
import es.chauder.circuitanalyzer.model.model.analysis.AnalysisTopology;
import es.chauder.circuitanalyzer.model.model.base.Circuit;
import es.chauder.circuitanalyzer.model.model.wiring.Instrument;


/**
 * Created by rchauderlot on 1/12/15.
 */
public class AnalysisTopologyGenerator {

    public static AnalysisTopology createElectronicTopology(Circuit circuit, List<Instrument> instrumentList) {

        AnalysisTopology topology = null;

        if (circuit != null) {
            topology = new AnalysisTopology(circuit);

            BranchGenerator.generateBranches(topology);
            AnalysisGroupGenerator.generateAnalysisGroups(topology);
            for (AnalysisGroup group : topology.getAnalysisGroups()) {
                OpenClosedBranchSetsGenerator.generateOpenClosedBranchSets(group);
                NodeGenerator.generateNodes(group);
                NetworkGenerator.generateNetworks(group);
            }


            if (instrumentList != null) {
                Map<Instrument, AnalysisTargetTopology> analysisTargetTopologies =
                        AnalysisTargetTopologyGenerator.generateAnalysisTargetTopologies(instrumentList);
                if (analysisTargetTopologies != null) {
                    topology.setAnalysisTargetTopologyMap(analysisTargetTopologies);
                }
            }
        }




        return topology;
    }


}
