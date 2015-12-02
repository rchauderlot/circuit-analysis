package es.chauder.circuitanalyzer.model.service;

import es.chauder.circuitanalyzer.model.model.base.*;
import es.chauder.circuitanalyzer.model.model.analysis.Branch;
import es.chauder.circuitanalyzer.model.model.analysis.AnalysisTopology;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by rchauderlot on 1/12/15.
 */
public class ElectronicTopologyGenerator {

    public static AnalysisTopology createElectronicTopology(Circuit circuit) {
        AnalysisTopology topology = new AnalysisTopology();
        topology.setCircuit(circuit);
        if (circuit != null) {
            generateBranches(topology);
            generateNetworks(topology);
        }
        return topology;
    }


    private static void generateBranches(AnalysisTopology topology) {

        if (topology.getCircuit().getWires() != null && topology.getCircuit().getDevices() != null) {
            List<Branch> branches = generateBranches(topology.getCircuit().getWires(), topology.getCircuit().getDevices());
            topology.setBranches(branches);
        }

    }

    private static List<Branch> generateBranches(List<Wire> wires, List<Device> devices) {

        List<Branch> branches = new ArrayList<Branch>();
        List<Wire> wiresToBeAnalyzed = new ArrayList<Wire>(wires);
        List<Device> devicesToBeAnalyzed = new ArrayList<Device>(devices);

        while (wiresToBeAnalyzed.size() > 0) {
            List<Connector> elements = new ArrayList<Connector>();
            Wire w = wiresToBeAnalyzed.get(0);
            if (w.getDevices().size() == 0) { // Isolated wire
                elements.add(w); // Lets put them into their own branch
            } else if (w.getDevices().size() == 1) { // Dead end loop
                // Lets connect the


            } else if (w.getDevices().size() == 2) { // Connection between 2 devices: branch

            } else if (w.getDevices().size() == 3) { // Node


            }


            Branch b = new Branch();
            b.setElements(elements);
            branches.add(b);
        }

        // not connected to wire devices
        for (Device d : devicesToBeAnalyzed) { // Isolated devices
            Branch b = new Branch(); // Lets put them into their own branch
            List<Connector> elements = b.getElements();
            for (Connector connector : d.getTerminals()) {
                elements.add(connector);
            }
            branches.add(b);
        }
        return branches;
    }


    private static void generateNetworks(AnalysisTopology topology) {





    }
}
