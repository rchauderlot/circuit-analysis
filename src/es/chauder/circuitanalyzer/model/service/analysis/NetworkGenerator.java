package es.chauder.circuitanalyzer.model.service.analysis;

import es.chauder.circuitanalyzer.model.model.analysis.*;
import es.chauder.circuitanalyzer.model.model.base.Connector;
import es.chauder.circuitanalyzer.model.service.arithmetic.DijkstraSolver;

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
            branchDirections = createBranchDirectionsForClosedLoopBranch(startingBranch);
        } else if (startNode != null && endNode != null) {
            if (startNode.equals(endNode)) {
                branchDirections = createBranchDirectionsForClosedLoopBranch(startingBranch);
            } else {
                branchDirections = createBranchDirectionsForGeneralBranch(analysisGroup, startingBranch);
            }
        }

        Network network = new Network();
        for (BranchDirection branch : branchDirections) {
            network.addBranchDirection(branch);
        }
        return  network;

    }


    private static List<BranchDirection> createBranchDirectionsForClosedLoopBranch(Branch branch) {

        List<BranchDirection> branchDirections = new ArrayList<BranchDirection>();

        BranchDirection startBranchDirection = new BranchDirection(branch, branch.getFirstElement(), branch.getLastElement());
        branchDirections.add(startBranchDirection);

        return branchDirections;
    }

    private static List<BranchDirection> createBranchDirectionsForGeneralBranch(AnalysisGroup analysisGroup,
                                                                                final Branch branch) {

        // This creates a list without the orginal branch
        // This is done as this to find the minimal path which closes the loop but we dont want the that branch in the result
        List<BranchDirection> minimalEdgeListBetweenNodes =
                new DijkstraSolver<Node, BranchDirection>().createMinimalEdgeListBetweenNodes(
                        branch.getStart(),
                        branch.getEnd(),
                        analysisGroup.getNodes(),
                        new DijkstraSolver.DijkstraSolverDelegate<Node, BranchDirection>() {
                            @Override
                            public List<BranchDirection> getNeighbourEdges(Node node) {
                                List<BranchDirection> edges = new ArrayList<BranchDirection>();
                                for (BranchDirection b : node.getBranchDirections()) {
                                    if (b.getBranch() != branch) { // We remove the original branch of the dijkstra analysis
                                        Connector endElement = b.getBranch().getFirstElementInTheBranchStartingFromNode(node);
                                        Connector startElement = b.getBranch().getOppositeBoundaryElement(endElement);
                                        edges.add(new BranchDirection(
                                                b.getBranch(),
                                                startElement,
                                                endElement));
                                    }
                                }
                                return edges;
                            }

                            @Override
                            public Node getNextNodeFromEdge(Node currentNode, BranchDirection edge) {
                                return edge.getBranch().getOppositeBoundaryNode(currentNode);
                            }

                            @Override
                            public long getEdgeLength(BranchDirection edge) {
                                return edge.getBranch().getElements().size();
                            }
                        });

        // Add original branch
        //
        // But, it should be added in the opposite direction as the dijkstra analysed branches went from end to start,
        // this should go from start to end to close the loop following a single direction.
        minimalEdgeListBetweenNodes.add(new BranchDirection(branch, branch.getLastElement(), branch.getFirstElement()));

        return minimalEdgeListBetweenNodes;

    }

}
