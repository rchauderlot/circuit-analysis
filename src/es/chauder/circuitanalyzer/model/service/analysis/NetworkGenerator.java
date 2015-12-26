package es.chauder.circuitanalyzer.model.service.analysis;

import es.chauder.circuitanalyzer.model.model.analysis.AnalysisGroup;
import es.chauder.circuitanalyzer.model.model.analysis.Branch;
import es.chauder.circuitanalyzer.model.model.analysis.Network;
import es.chauder.circuitanalyzer.model.model.analysis.Node;

import java.util.*;

/**
 * Created by rchauderlot on 13/12/15.
 */
public class NetworkGenerator {

    public static void generateNetworks(AnalysisGroup analysisGroup) {


        List<Branch> branchesToAnalyse = new ArrayList<Branch>(analysisGroup.getClosedBranches());

        List<Network> analysisGroupNetworks = new ArrayList<Network>();

        while (branchesToAnalyse.size() > 0) {
            Branch b = branchesToAnalyse.get(0);

            Network network = generateSmallestNetworkStartingInBranch(analysisGroup, b);

            for (Node.BranchEnd branchEnd : network.getBranchEnds()) {
                branchesToAnalyse.remove(branchEnd.branch);
            }

            analysisGroupNetworks.add(network);
        }


        analysisGroup.setNetworks(analysisGroupNetworks);

    }



    private static Network generateSmallestNetworkStartingInBranch(AnalysisGroup analysisGroup,
                                                                   Branch startingBranch) {

        List<Node.BranchEnd> branchEnds = new ArrayList<Node.BranchEnd>();
        Node startNode = startingBranch.getStart();
        Node endNode = startingBranch.getEnd();


        if (startNode == null && endNode == null) {
            branchEnds = createBranchEndsForClosedLoopBranch(startingBranch);
        } else if (startNode != null && endNode != null) {
            if (startNode.equals(endNode)) {
                branchEnds = createBranchEndsForClosedLoopBranch(startingBranch);
            } else {
                branchEnds = createBranchEndsForGeneralBranch(analysisGroup, startingBranch);
            }
        }

        Network network = new Network();
        network.setBranchEnds(branchEnds);
        return  network;

    }


    private static List<Node.BranchEnd> createBranchEndsForClosedLoopBranch(Branch branch) {

        List<Node.BranchEnd> branchEnds = new ArrayList<Node.BranchEnd>();

        Node.BranchEnd startBranchEnd = new Node.BranchEnd();
        startBranchEnd.branch = branch;
        startBranchEnd.connector = branch.getFirstElement();
        branchEnds.add(startBranchEnd);

        Node.BranchEnd endBranchEnd = new Node.BranchEnd();
        endBranchEnd.branch = branch;
        endBranchEnd.connector = branch.getLastElement();
        branchEnds.add(endBranchEnd);

        return branchEnds;
    }

    private static List<Node.BranchEnd> createBranchEndsForGeneralBranch(AnalysisGroup analysisGroup,
                                                                         Branch branch) {


        List<Node.BranchEnd> branchEnds = new ArrayList<Node.BranchEnd>();

        Map<Node, DijkstraVertex> dijkstraVertexMap = computeDijkstraVertexes(analysisGroup, branch.getStart(), branch);


        Node.BranchEnd endBranchEnd = findBranchEndForNode(branch, branch.getEnd());
        branchEnds.add(endBranchEnd);

        Node currentNode = branch.getEnd();
        while (currentNode != branch.getStart()) {
            DijkstraVertex vertex = dijkstraVertexMap.get(currentNode);

            Branch currentBranch = getBranchBetweenNodes(currentNode, vertex.previous);


            Node.BranchEnd currentStartBranchEnd = findBranchEndForNode(currentBranch, currentNode);
            branchEnds.add(currentStartBranchEnd);

            Node.BranchEnd currentEndBranchEnd = findBranchEndForNode(currentBranch, vertex.previous);
            branchEnds.add(currentEndBranchEnd);

            currentNode = vertex.previous;
        }

        Node.BranchEnd startBranchEnd = findBranchEndForNode(branch, branch.getStart());
        branchEnds.add(startBranchEnd);

        return branchEnds;
    }

    private static Node.BranchEnd findBranchEndForNode(Branch branch, Node node) {

        Node.BranchEnd branchEnd = null;
        for (Node.BranchEnd be : node.getBranchEnds()) {
            if (be.branch == branch) {
                branchEnd = be;
                break;
            }
        }
        return branchEnd;
    }

    private static Branch getBranchBetweenNodes(Node node1, Node node2) {

        Branch branch = null;

        for (Node.BranchEnd branchEndInNode1 : node1.getBranchEnds()) {
            for (Node.BranchEnd branchEndInNode2 : node2.getBranchEnds()) {
                if (branchEndInNode1.branch == branchEndInNode2.branch) {
                    branch = branchEndInNode1.branch;
                    break;
                }
            }
        }
        return branch;
    }


    private static class DijkstraVertex {
        long distance = Long.MAX_VALUE;
        Node previous = null;
    }


    private static Map<Node, DijkstraVertex> computeDijkstraVertexes(AnalysisGroup analysisGroup, Node startNode, Branch branchToAvoid) {

        Map<Node, DijkstraVertex> vertexMap = new HashMap<Node, DijkstraVertex>();
        List<Node> nodeList = new ArrayList<Node>();

        for (Node n : analysisGroup.getNodes()) {
            DijkstraVertex v = new DijkstraVertex();
            vertexMap.put(n, v);
            nodeList.add(n);
        }

        vertexMap.get(startNode).distance = 0;

        while (!nodeList.isEmpty()) {

            Node minNode = nodeList.get(0);
            long minDistance = vertexMap.get(minNode).distance;

            for (int i = 1; i < nodeList.size(); i++) {
                Node node = nodeList.get(i);
                long nodeDistance = vertexMap.get(node).distance;
                if (nodeDistance < minDistance) {
                    minNode = node;
                    minDistance = nodeDistance;
                }
            }

            nodeList.remove(minNode);

            for (Node.BranchEnd neighborBranch : minNode.getBranchEnds()) {

                if (neighborBranch.branch != branchToAvoid) {
                    Node neighborNode = neighborBranch.branch.getOppositeEnd(minNode);
                    if (nodeList.contains(neighborNode)) {

                        long alt = minDistance + neighborBranch.branch.getElements().size();
                        DijkstraVertex neighborVertex = vertexMap.get(neighborNode);
                        if (alt < neighborVertex.distance) {
                            neighborVertex.distance = alt;
                            neighborVertex.previous = minNode;
                        }

                    }
                }
            }
        }


        return vertexMap;
    }

}
