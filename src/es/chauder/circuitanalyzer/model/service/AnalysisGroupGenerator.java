package es.chauder.circuitanalyzer.model.service;

import es.chauder.circuitanalyzer.model.model.analysis.AnalysisGroup;
import es.chauder.circuitanalyzer.model.model.analysis.AnalysisTopology;
import es.chauder.circuitanalyzer.model.model.analysis.Branch;
import es.chauder.circuitanalyzer.model.model.base.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by rchauderlot on 1/12/15.
 */
public class AnalysisGroupGenerator {


    public static void generateAnalysisGroups(AnalysisTopology topology) {

        List<AnalysisGroup> analysisGroups = new ArrayList<AnalysisGroup>();


        List<AnalysisGroup> connectedToGroups = new ArrayList<AnalysisGroup>();
        for (Branch b : topology.getBranches()) {

            connectedToGroups.clear();

            AnalysisGroup analysisGroup = null;
            for (AnalysisGroup ag : analysisGroups) {
                if (isBranchConnectedToAnalyisGroup(b, ag)) {
                    connectedToGroups.add(ag);
                }
            }

            if (connectedToGroups.size() == 0) {
                analysisGroup = new AnalysisGroup();
                analysisGroups.add(analysisGroup);
            } else if (connectedToGroups.size() == 1) {
                analysisGroup = connectedToGroups.get(0);
            } else {
                analysisGroups.removeAll(connectedToGroups);
                analysisGroup = mergeAnalysisGroups(connectedToGroups);
            }
            analysisGroup.getBranches().add(b);

        }

        topology.setAnalysisGroups(analysisGroups);
    }


    private static boolean isBranchConnectedToAnalyisGroup(Branch branch, AnalysisGroup group) {

        boolean connected = false;
        for (Branch b : group.getBranches()) {
            if (areBranchesConnected(b, branch)) {
                connected = true;
                break;
            }
        }
        return connected;

    }

    private static boolean areBranchesConnected(Branch branch1, Branch branch2) {


        Connector branch1Start = branch1.getElements().get(0);
        Connector branch1End = branch1.getElements().get(branch1.getElements().size() - 1);
        Connector branch2Start = branch2.getElements().get(0);
        Connector branch2End = branch2.getElements().get(branch2.getElements().size() - 1);


        return (areConnectorsLinked(branch1Start, branch2Start) ||
                areConnectorsLinked(branch1Start, branch2End) ||
                areConnectorsLinked(branch1End, branch2Start) ||
                areConnectorsLinked(branch1End, branch2End));


    }

    private static boolean areConnectorsLinked(Connector connector1, Connector connector2) {

        boolean linked = false;
        if (connector1 instanceof Wire && connector2 instanceof Wire && connector1.equals(connector2)) {
            linked = true;
        } else if (connector1 instanceof Terminal && connector2 instanceof Terminal &&
                ((Terminal)connector1).getDevice().equals(((Terminal)connector2).getDevice())) {
            linked = true;
        }
        return linked;
    }


    private static AnalysisGroup mergeAnalysisGroups(List<AnalysisGroup> analysisGroups) {

        AnalysisGroup mergedAnalysisGroup = new AnalysisGroup();
        for (AnalysisGroup group : analysisGroups) {

            mergedAnalysisGroup.getBranches().addAll(group.getBranches());
            mergedAnalysisGroup.getNetworks().addAll(group.getNetworks());
            mergedAnalysisGroup.getNodes().addAll(group.getNodes());

        }
        return mergedAnalysisGroup;
    }

}
