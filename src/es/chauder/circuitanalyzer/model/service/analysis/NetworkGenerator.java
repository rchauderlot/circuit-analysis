package es.chauder.circuitanalyzer.model.service.analysis;

import es.chauder.circuitanalyzer.model.model.analysis.*;

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

            for (BranchDirection branchDirection : network.getBranchDirections()) {
                branchesToAnalyse.remove(branchDirection.getBranch());
            }

            analysisGroupNetworks.add(network);
        }


        analysisGroup.setNetworks(analysisGroupNetworks);

    }



    private static Network generateSmallestNetworkStartingInBranch(AnalysisGroup analysisGroup,
                                                                   Branch startingBranch) {

        List<BranchDirection> branchDirections = new ArrayList<BranchDirection>();
        Node startNode = startingBranch.getStart();
        Node endNode = startingBranch.getEnd();


        if (startNode == null && endNode == null) {
            branchDirections = createBranchEndsForClosedLoopBranch(startingBranch);
        } else if (startNode != null && endNode != null) {
            if (startNode.equals(endNode)) {
                branchDirections = createBranchEndsForClosedLoopBranch(startingBranch);
            } else {
                branchDirections = createBranchEndsForGeneralBranch(analysisGroup, startingBranch);
            }
        }

        Network network = new Network();
        network.setBranchDirections(branchDirections);
        return  network;

    }


    private static List<BranchDirection> createBranchEndsForClosedLoopBranch(Branch branch) {

        List<BranchDirection> branchDirections = new ArrayList<BranchDirection>();

        BranchDirection startBranchDirection = new BranchDirection(branch, branch.getFirstElement(), branch.getLastElement());
        branchDirections.add(startBranchDirection);

        return branchDirections;
    }

    private static List<BranchDirection> createBranchEndsForGeneralBranch(AnalysisGroup analysisGroup,
                                                                          Branch branch) {


        List<BranchDirection> branchDirections = new ArrayList<BranchDirection>();

        Map<Node, DijkstraVertex> dijkstraVertexMap = computeDijkstraVertexes(analysisGroup, branch.getStart(), branch);


        BranchDirection endBranchDirection = findBranchEndForNode(branch, branch.getEnd());
        branchDirections.add(endBranchDirection);

        Node currentNode = branch.getEnd();
        while (currentNode != branch.getStart()) {
            DijkstraVertex vertex = dijkstraVertexMap.get(currentNode);

            Branch currentBranch = getBranchBetweenNodes(currentNode, vertex.previous);


            BranchDirection currentStartBranchDirection = findBranchEndForNode(currentBranch, currentNode);
            branchDirections.add(currentStartBranchDirection);

            BranchDirection currentEndBranchDirection = findBranchEndForNode(currentBranch, vertex.previous);
            branchDirections.add(currentEndBranchDirection);

            currentNode = vertex.previous;
        }

        BranchDirection startBranchDirection = findBranchEndForNode(branch, branch.getStart());
        branchDirections.add(startBranchDirection);

        return branchDirections;
    }

    private static BranchDirection findBranchEndForNode(Branch branch, Node node) {

        BranchDirection branchDirection = null;
        for (BranchDirection be : node.getBranchDirections()) {
            if (be.getBranch() == branch) {
                branchDirection = be;
                break;
            }
        }
        return branchDirection;
    }

    private static Branch getBranchBetweenNodes(Node node1, Node node2) {

        Branch branch = null;

        for (BranchDirection branchDirectionInNode1 : node1.getBranchDirections()) {
            for (BranchDirection branchDirectionInNode2 : node2.getBranchDirections()) {
                if (branchDirectionInNode1.getBranch() == branchDirectionInNode2.getBranch()) {
                    branch = branchDirectionInNode1.getBranch();
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

            for (BranchDirection neighborBranch : minNode.getBranchDirections()) {

                if (neighborBranch.getBranch() != branchToAvoid) {
                    Node neighborNode = neighborBranch.getBranch().getOppositeBoundaryNode(minNode);
                    if (nodeList.contains(neighborNode)) {

                        long alt = minDistance + neighborBranch.getBranch().getElements().size();
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
