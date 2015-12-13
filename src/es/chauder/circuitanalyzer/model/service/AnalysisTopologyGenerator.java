package es.chauder.circuitanalyzer.model.service;

import es.chauder.circuitanalyzer.model.model.base.*;
import es.chauder.circuitanalyzer.model.model.analysis.Branch;
import es.chauder.circuitanalyzer.model.model.analysis.AnalysisTopology;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by rchauderlot on 1/12/15.
 */
public class AnalysisTopologyGenerator {

    public static AnalysisTopology createElectronicTopology(Circuit circuit) {
        AnalysisTopology topology = new AnalysisTopology();
        topology.setCircuit(circuit);
        if (circuit != null) {
            generateBranches(topology);
            generateAnalysisGroups(topology);
            generateNetworks(topology);
        }
        return topology;
    }


    private static void generateBranches(AnalysisTopology topology) {

        if (topology.getCircuit().getWires() != null && topology.getCircuit().getDevices() != null) {
            generateBranches(topology, topology.getCircuit().getWires(), topology.getCircuit().getDevices());
        }

    }

    private static List<Branch> generateBranches(AnalysisTopology topology, List<Wire> wires, List<Device> devices) {

        List<Branch> branches = new ArrayList<Branch>();
        List<Wire> unUsedWires = new ArrayList<Wire>();
        List<Device> unUsedDevices = new ArrayList<Device>();


        List<Wire> wiresToBeAnalyzed = new ArrayList<Wire>(wires);
        List<Device> devicesToBeAnalyzed = new ArrayList<Device>(devices);

        while (wiresToBeAnalyzed.size() > 0) {

            Wire w = wiresToBeAnalyzed.get(0);

            if (w.getTerminals().size() == 0) {
               unUsedWires.add(w);
            } else if (w.getTerminals().size() == 1 ||
                    w.getTerminals().size() == 2) {
                Branch b = generateBranch(w, wiresToBeAnalyzed, devicesToBeAnalyzed);
                branches.add(b);
            } // If there is a wire with more than 2 terminals, its a node, not a single branch but the wire is used. Just remove it.

            // Every wire classified should be removed.
            wiresToBeAnalyzed.remove(w);
        }

        // not connected to wire devices
        while (devicesToBeAnalyzed.size() > 0) { // Isolated devices
            Device d = devicesToBeAnalyzed.get(0);

            // All pending devices are not linked to any wire, so they are unused.
            unUsedDevices.add(d);

            // Every device classified should be removed.
            devicesToBeAnalyzed.remove(d);
        }


        // Set the branches and the unused things
        topology.setBranches(branches);
        topology.setUnusedWires(unUsedWires);
        topology.setUnusedDevices(unUsedDevices);

        return branches;
    }


    private static Branch generateBranch(Wire wire, List<Wire> wires, List<Device> devices) {

        List<Connector> connectors = new ArrayList<>();


        if (wire.getTerminals().size() == 1 ||
                wire.getTerminals().size() == 2) { // if devices are more than 2 there is a node. So, stop this branch
            Terminal leftTerminal = wire.getTerminals().get(0);

            List<Connector> leftHandConnectors = getNextConnectors(wire, leftTerminal, wires, devices);
            connectors.addAll(leftHandConnectors);

        }

        connectors.add(wire);

        if (wire.getTerminals().size() == 2) {
            Terminal rightTerminal = wire.getTerminals().get(1);

            List<Connector> rightHandConnectors = getNextConnectors(wire, rightTerminal, wires, devices);
            // reverse elements
            Collections.reverse(rightHandConnectors);
            connectors.addAll(rightHandConnectors);
        }


        Branch b = new Branch();
        b.setElements(connectors);
        return b;
    }


    private static List<Connector> getNextConnectors(Wire currentWire, Terminal currentTerminal, List<Wire> wires, List<Device> devices) {

        List<Connector> connectors = new ArrayList<>();


        while (currentWire != null && currentTerminal != null) {

            // Add the terminal to be analyzed to the list, and mark the device as used
            connectors.add(currentTerminal);
            Device currentDevice = currentTerminal.getDevice();
            devices.remove(currentDevice);

            // Go to the other side of the device if any
            currentTerminal = getNextTerminalFromDevice(currentDevice, currentTerminal);
            if (currentTerminal != null) {

                // Add that terminal also, and get the new wire
                connectors.add(currentTerminal);
                currentWire = currentTerminal.getWire();

                if (currentWire != null) {
                    // Remove the wire to the list, and mark it as used
                    connectors.add(currentWire);
                    wires.remove(currentWire);

                    // Get the terminal to continue analysing if any
                    currentTerminal = getNextTerminalFromWire(currentWire, currentTerminal);
                }
            }
        }

        return connectors;
    }



    private static Terminal getNextTerminalFromDevice(Device device, Terminal currentTerminal) {

        Terminal nextTerminal = null;

        // if devices are just 2 it,
        if (device != null && device.getTerminals() != null && device.getTerminals().size() == 2 && currentTerminal != null) {
            List<Terminal> wireTerminals = device.getTerminals();

            // And one of the connectors is the current connector
            if (wireTerminals.get(0).equals(currentTerminal)) {
                nextTerminal = wireTerminals.get(1);
            } else if (wireTerminals.get(1).equals(currentTerminal)) {
                nextTerminal = wireTerminals.get(0);
            }
        }
        return nextTerminal;
    }

    private static Terminal getNextTerminalFromWire(Wire wire, Terminal currentTerminal) {

        Terminal nextTerminal = null;

        // if devices are just 2 it,
        if (wire != null && wire.getTerminals() != null && wire.getTerminals().size() == 2 && currentTerminal != null) {
            List<Terminal> wireTerminals = wire.getTerminals();

            // And one of the connectors is the current connector
            if (wireTerminals.get(0).equals(currentTerminal)) {
                nextTerminal = wireTerminals.get(1);
            } else if (wireTerminals.get(1).equals(currentTerminal)) {
                nextTerminal = wireTerminals.get(0);
            }
        }
        return nextTerminal;
    }



    private static void generateAnalysisGroups(AnalysisTopology topology) {





    }

    private static void generateNetworks(AnalysisTopology topology) {





    }
}