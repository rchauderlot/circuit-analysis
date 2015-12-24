package es.chauder.circuitanalyzer.model.service.analysis;

import es.chauder.circuitanalyzer.model.model.analysis.AnalysisGroup;
import es.chauder.circuitanalyzer.model.model.analysis.Branch;
import es.chauder.circuitanalyzer.model.model.analysis.Network;
import es.chauder.circuitanalyzer.model.model.base.Connector;
import es.chauder.circuitanalyzer.model.model.base.Terminal;
import es.chauder.circuitanalyzer.model.model.base.Wire;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rchauderlot on 13/12/15.
 */
public class NetworkGenerator {

    public static void generateNetworks(AnalysisGroup analysisGroup) {

//        generateOpenBranches(analysisGroup);
//
//        List<Branch> closedNetworkBranches = new ArrayList<Branch>(analysisGroup.getBranches());
//        closedNetworkBranches.removeAll(analysisGroup.getOpenBranches());
//
//        List<Branch> branchesToAnalyse = new ArrayList<Branch>(closedNetworkBranches);
//
//        List<Network> analysisGroupNetworks = new ArrayList<Network>();
//
//        while (branchesToAnalyse.size() > 0) {
//            Branch b = branchesToAnalyse.get(0);
//
//            Network network = generateSmallestNetworkStartingInBranch(closedNetworkBranches, b);
//
//            branchesToAnalyse.removeAll(network.getBranches());
//
//            analysisGroupNetworks.add(network);
//        }
//
//
//        analysisGroup.setNetworks(analysisGroupNetworks);

    }



    private static Network generateSmallestNetworkStartingInBranch(List<Branch> closedNetworkBranches, Branch startingBranch) {

        List<Branch> branches = new ArrayList<Branch>();
        Connector startConnector = startingBranch.getFirstElement();
        Connector endConnector = startingBranch.getLastElement();

        branches = generateSmallestNetworkFromTo(startConnector, startingBranch, endConnector, branches);

        Network network = new Network();
        network.setBranches(branches);
        return  network;

    }


    private static List<Branch> generateSmallestNetworkFromTo(Connector currentConnector, Branch currentBranch , Connector goalConnector, List<Branch> visitedBranches) {

        List<Branch> branches = new ArrayList<>(visitedBranches);
        if (!currentConnector.equals(goalConnector)) {
            branches.add(currentBranch);
        } else {
//            for (currentBranch.getN)

        }


        return branches;

    }


//    private static List<Branch> getNeighboursBranches(Connector connectorNode, Branch ) {
//
//    }


}
