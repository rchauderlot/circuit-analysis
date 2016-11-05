package es.chauder.circuitanalyzer.model.service.analysis;

import es.chauder.circuitanalyzer.model.model.analysis.AnalysisGroup;
import es.chauder.circuitanalyzer.model.model.analysis.Branch;
import es.chauder.circuitanalyzer.model.model.analysis.BranchDirection;
import es.chauder.circuitanalyzer.model.model.analysis.Node;
import es.chauder.circuitanalyzer.model.model.base.Connector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rchauderlot on 15/12/15.
 */
public class NodeGenerator {

    public static void generateNodes(AnalysisGroup analysisGroup) {

        List<Node> nodes = generatePossibleNodes(analysisGroup);
        nodes = filterNodesRemovingWires(nodes);
        analysisGroup.setNodes(nodes);

    }


    public static List<Node> generatePossibleNodes(AnalysisGroup analysisGroup) {

        List<Node> nodes = new ArrayList<Node>();

        for (Branch b : analysisGroup.getClosedBranches()) {

            linkStartNodeOfBranch(nodes, b);
            linkEndNodeOfBranch(nodes, b);

        }
        return nodes;
    }

    public static List<Node> filterNodesRemovingWires(List<Node> nodesToFilter) {

        List<Node> nodes = new ArrayList<Node>(nodesToFilter);
        List<Node> nodesToRemove = new ArrayList<Node>();
        for (Node n : nodes) {
            if (n.getBranchDirections().size() < 3) {
                nodesToRemove.add(n);
            }
        }
        nodes.removeAll(nodesToRemove);
        return nodes;
    }



    private static void linkStartNodeOfBranch(List<Node> nodes, Branch b) {

        Connector startConnector = b.getFirstElement();

        Node startNode = generateNodeReusingNodesForBranchConnector(nodes, b, startConnector);

        if (startNode != null) {
            BranchDirection branchDirection = new BranchDirection(b, startConnector, b.getOppositeBoundaryElement(startConnector));

            // Make the link
            startNode.addBranchDirection(branchDirection);
            b.setStart(startNode);
        }
    }

    private static void linkEndNodeOfBranch(List<Node> nodes, Branch b) {

        Connector endConnector = b.getLastElement();

        Node endNode = generateNodeReusingNodesForBranchConnector(nodes, b, endConnector);

        if (endNode != null) {
            BranchDirection branchDirection = new BranchDirection(b, endConnector, b.getOppositeBoundaryElement(endConnector));

            // Make the link
            endNode.addBranchDirection(branchDirection);
            b.setEnd(endNode);
        }
    }


    private static Node generateNodeReusingNodesForBranchConnector(List<Node> nodes, Branch b, Connector c) {
        Node node = findAnalyzedNodeLinkedToConnector(nodes, c);
        if (node == null) {
            node = new Node();
            nodes.add(node);
        }
        return node;
    }



    private static Node findAnalyzedNodeLinkedToConnector(List<Node> analyzedNodes, Connector c) {
        Node node = null;
        for (int i = 0; node == null && i < analyzedNodes.size(); i++) {
            Node n = analyzedNodes.get(i);
            for (int j = 0; node == null && j < n.getBranchDirections().size(); j++) {
                BranchDirection analyzedBranch = n.getBranchDirections().get(j);
                if (analyzedBranch.getStartingConnector().isLinkedTo(c)) {
                    node = n;
                }
            }
        }
        return node;
    }

}
