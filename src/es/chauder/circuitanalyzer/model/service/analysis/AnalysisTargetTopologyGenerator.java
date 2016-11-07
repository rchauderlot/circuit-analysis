package es.chauder.circuitanalyzer.model.service.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.chauder.circuitanalyzer.model.model.analysis.AnalysisTargetTopology;
import es.chauder.circuitanalyzer.model.model.analysis.BranchDirection;
import es.chauder.circuitanalyzer.model.model.analysis.Node;
import es.chauder.circuitanalyzer.model.model.base.*;
import es.chauder.circuitanalyzer.model.model.wiring.Instrument;
import es.chauder.circuitanalyzer.model.service.arithmetic.DijkstraSolver;

/**
 * Created by rchauderlot on 6/11/16.
 */
public class AnalysisTargetTopologyGenerator {

    public static Map<Instrument, AnalysisTargetTopology> generateAnalysisTargetTopologies(List<Instrument> instruments) {

        Map<Instrument, AnalysisTargetTopology> analysisTargetTopologies = new HashMap<>();
        if (instruments != null) {
            for (Instrument instrument : instruments) {
                analysisTargetTopologies.put(instrument, generateAnalysisTargetTopology(instrument));
            }
        }
        return analysisTargetTopologies;
    }


    private static AnalysisTargetTopology generateAnalysisTargetTopology(Instrument instrument) {


        List<Terminal> elements = null;
        if (instrument.getCircuit() != null &&
                instrument.getHigherPotential() != null &&
                instrument.getLowerPotential() != null) {
            elements = new DijkstraSolver<Wire, Terminal>().createMinimalEdgeListBetweenNodes(
                    instrument.getLowerPotential(),
                    instrument.getHigherPotential(),
                    instrument.getCircuit().getWires(),
                    new DijkstraSolver.DijkstraSolverDelegate<Wire, Terminal>() {
                        @Override
                        public List<Terminal> getNeighbourEdges(Wire node) {

                            List<Terminal> terminals = new ArrayList<Terminal>();
                            for (Connector connector : node.getConnectorsConnectedToConnector()) {
                                if (connector instanceof Terminal) {
                                    terminals.add((Terminal) connector);
                                }
                            }

                            return terminals;
                        }

                        @Override
                        public Wire getNextNodeFromEdge(Wire currentNode, Terminal edge) {

                            for (Terminal terminal : edge.getDevice().getTerminals()) {
                                if (terminal != edge) {
                                    return terminal.getWire();
                                }
                            }
                            return null;
                        }

                        @Override
                        public long getEdgeLength(Terminal edge) {
                            return 1;
                        }

                    });
        }

        return new AnalysisTargetTopology(instrument, elements);

    }

}
