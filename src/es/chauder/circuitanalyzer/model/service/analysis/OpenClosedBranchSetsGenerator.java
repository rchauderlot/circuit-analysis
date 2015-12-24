package es.chauder.circuitanalyzer.model.service.analysis;

import es.chauder.circuitanalyzer.model.model.analysis.AnalysisGroup;
import es.chauder.circuitanalyzer.model.model.analysis.Branch;
import es.chauder.circuitanalyzer.model.model.base.Connector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rchauderlot on 24/12/15.
 */
public class OpenClosedBranchSetsGenerator {

    public static void generateOpenClosedBranchSets(AnalysisGroup analysisGroup) {


        List<Branch> remainingBranches = new ArrayList<Branch>(analysisGroup.getBranches());
        List<Branch> openBranches = new ArrayList<Branch>();

        boolean areAllRemainingBranchesClosed = false;
        while (!areAllRemainingBranchesClosed) {

            List<Branch> openBranchesInThisIteration = getOpenBranches(remainingBranches, openBranches);
            if (openBranchesInThisIteration.size() == 0) {
                areAllRemainingBranchesClosed = true;
            } else {
                openBranches.addAll(openBranchesInThisIteration);
                remainingBranches.removeAll(openBranchesInThisIteration);
            }

        }

        analysisGroup.setOpenBranches(openBranches);
        analysisGroup.setClosedBranches(remainingBranches);

    }

    private static List<Branch> getOpenBranches(List<Branch> unknownBranches, List<Branch> knownOpenBranches) {

        List<Branch> openBranches = new ArrayList<Branch>();

        for (Branch b : unknownBranches) {

            Connector startConnector = b.getFirstElement();
            Connector endConnector = b.getLastElement();

            if (isDeadEndConnector(startConnector, knownOpenBranches) ||
                    isDeadEndConnector(endConnector, knownOpenBranches)) {
                openBranches.add(b);
            }
        }
        return openBranches;
    }

    private static boolean isDeadEndConnector(Connector connector, List<Branch> knownOpenBraches) {


        int numberOfOpenBranchesConnectedToTheConnector = 0;
        List<Connector> connectorsConnectedToConnector = connector.getConnectorsConnectedToConnector();

        for (Connector c : connectorsConnectedToConnector) {
            if (isConnectorEndOfOpenBranch(c, knownOpenBraches)) {
                numberOfOpenBranchesConnectedToTheConnector ++;
            }
        }
        return connectorsConnectedToConnector.size() - numberOfOpenBranchesConnectedToTheConnector < 2;
    }


    private static boolean isConnectorEndOfOpenBranch(Connector connector, List<Branch> knownOpenBraches) {

        boolean isConnectedToOpenBranch = false;

        for (Branch b : knownOpenBraches) {
            if (isConnectorEndOfABranch(connector, b)) {
                isConnectedToOpenBranch = true;
                break;
            }

        }
        return  isConnectedToOpenBranch;
    }


    private static boolean isConnectorEndOfABranch(Connector connector, Branch branch) {

        return connector.isLinkedTo(branch.getFirstElement()) || connector.isLinkedTo(branch.getLastElement());


    }


}
