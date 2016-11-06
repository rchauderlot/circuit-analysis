package es.chauder.circuitanalyzer.model.service.arithmetic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rchauderlot on 2/11/16.
 */
public class DijkstraSolver<N, E> {



    public interface DijkstraSolverDelegate<T, S> {
        List<S> getNeighbourEdges(T node);
        T getNextNodeFromEdge(T currentNode, S edge);
        long getEdgeLength(S edge);
    }


    public List<E> createMinimalEdgeListBetweenNodes(N startNode, N endNode, List<N> allNodes, DijkstraSolverDelegate<N, E> delegate) {


        List<E> edges = new ArrayList<>();

        Map<N, DijkstraVertex<N, E>> dijkstraVertexMap = computeDijkstraVertexes(allNodes, startNode, delegate);


        N currentNode = endNode;
        while (currentNode != startNode) {
            DijkstraVertex<N, E> vertex = dijkstraVertexMap.get(currentNode);

            edges.add(vertex.edge);

            currentNode = vertex.previous;
        }

        return edges;
    }


    private Map<N, DijkstraVertex<N, E>> computeDijkstraVertexes(List<N> nodes, N startNode, DijkstraSolverDelegate<N, E> delegate) {

        Map<N, DijkstraVertex<N, E>> vertexMap = new HashMap<>();
        List<N> nodeList = new ArrayList<N>();

        for (N n : nodes) {
            DijkstraVertex<N, E> v = new DijkstraVertex<N, E>();
            vertexMap.put(n, v);
            nodeList.add(n);
        }

        vertexMap.get(startNode).distance = 0;

        while (!nodeList.isEmpty()) {

            N minNode = nodeList.get(0);
            long minDistance = vertexMap.get(minNode).distance;

            for (int i = 1; i < nodeList.size(); i++) {
                N node = nodeList.get(i);
                long nodeDistance = vertexMap.get(node).distance;
                if (nodeDistance < minDistance) {
                    minNode = node;
                    minDistance = nodeDistance;
                }
            }

            nodeList.remove(minNode);

            for (E neighborEdge : delegate.getNeighbourEdges(minNode)) {

                N neighborNode = delegate.getNextNodeFromEdge(minNode, neighborEdge);
                if (nodeList.contains(neighborNode)) {

                    long alt = minDistance + delegate.getEdgeLength(neighborEdge);
                    DijkstraVertex neighborVertex = vertexMap.get(neighborNode);
                    if (alt < neighborVertex.distance) {
                        neighborVertex.distance = alt;
                        neighborVertex.previous = minNode;
                        neighborVertex.edge = neighborEdge;
                    }

                }

            }
        }


        return vertexMap;
    }

    private static class DijkstraVertex<T, S> {
        long distance = Long.MAX_VALUE;
        T previous = null;
        S edge = null;
    }

}
