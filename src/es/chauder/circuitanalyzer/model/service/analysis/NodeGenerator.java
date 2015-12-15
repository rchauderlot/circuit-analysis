package es.chauder.circuitanalyzer.model.service.analysis;

import es.chauder.circuitanalyzer.model.model.analysis.AnalysisGroup;
import es.chauder.circuitanalyzer.model.model.analysis.Branch;
import es.chauder.circuitanalyzer.model.model.analysis.Node;
import es.chauder.circuitanalyzer.model.model.base.Connector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rchauderlot on 15/12/15.
 */
public class NodeGenerator {

    public static void generateNodes(AnalysisGroup analysisGroup) {

        List<Node> nodes = new ArrayList<Node>();

        for (Branch b : analysisGroup.getBranches()) {

            Node startNode = createStartNodeOfBranch(nodes, b);
            nodes.add(startNode);

            Node endNode = createEndNodeOfBranch(nodes, b);
            nodes.add(endNode);

        }

        analysisGroup.setNodes(nodes);
    }


    private static Node createStartNodeOfBranch(List<Node> nodes, Branch b) {

        Connector startConnector = b.getFirstElement();

        Node startNode = generateNodeReusingNodesForBranchConnector(nodes, b, startConnector);

        // Make the link
        startNode.getBranches().add(b);
        b.setStart(startNode);

        return startNode;
    }

    private static Node createEndNodeOfBranch(List<Node> nodes, Branch b) {

        Connector endConnector = b.getFirstElement();

        Node endNode = generateNodeReusingNodesForBranchConnector(nodes, b, endConnector);

        // Make the link
        endNode.getBranches().add(b);
        b.setEnd(endNode);

        return endNode;
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
            for (int j = 0; node == null && j < n.getBranches().size(); j++) {
                Branch analyzedBranch = n.getBranches().get(j);
                if (analyzedBranch.getFirstElement().isLinkedTo(c) || analyzedBranch.getLastElement().isLinkedTo(c)) {
                    node = n;
                }
            }
        }
        return node;
    }


}
