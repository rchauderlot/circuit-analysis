package es.chauder.circuitanalyzer.tests;

import es.chauder.circuitanalyzer.model.model.active.VoltageSource;
import es.chauder.circuitanalyzer.model.model.base.Circuit;
import es.chauder.circuitanalyzer.model.model.base.Device;
import es.chauder.circuitanalyzer.model.model.base.Terminal;
import es.chauder.circuitanalyzer.model.model.base.Wire;
import es.chauder.circuitanalyzer.model.model.pasive.Resistor;

import java.util.List;

/**
 * Created by rchauderlot on 13/12/15.
 */
public class SampleCircuitGenerator {

    //     __________    <--w0
    //    |          |
    //  __|__        R0
    //   ---         |   <--w1
    //    |          R1
    //    |__________|   <--w2
    //
    public static Circuit generateSeriesResistorCircuit() {

        Circuit ciruit = new Circuit();

        ciruit.setTitle("SeriesResistorCircuit");

        VoltageSource voltageSource = new VoltageSource();
        voltageSource.setVoltage(5.0);

        Resistor r0 = new Resistor(100);
        Resistor r1 = new Resistor(100);

        Wire w0 = new Wire();
        connectDeviceWithWire(voltageSource, 1, w0);
        connectDeviceWithWire(r0, 0, w0);

        Wire w1 = new Wire();
        connectDeviceWithWire(r0, 1, w1);
        connectDeviceWithWire(r1, 0, w1);

        Wire w2 = new Wire();
        connectDeviceWithWire(r1, 1, w2);
        connectDeviceWithWire(voltageSource, 0, w2);


        List<Device> devices = ciruit.getDevices();
        devices.add(voltageSource);
        devices.add(r0);
        devices.add(r1);

        List<Wire> wires = ciruit.getWires();
        wires.add(w0);
        wires.add(w1);
        wires.add(w2);

        return ciruit;
    }



    //     ______________    <--w0
    //    |          |   |
    //  __|__        |   |
    //   ---         R0  R1
    //    |          |   |
    //    |__________|___|   <--w1
    //
    public static Circuit generateParallelResistorCircuit() {

        Circuit ciruit = new Circuit();
        ciruit.setTitle("ParallelResistorCircuit");

        VoltageSource voltageSource = new VoltageSource();
        voltageSource.setVoltage(5.0);

        Resistor r0 = new Resistor(100);
        Resistor r1 = new Resistor(100);

        Wire w0 = new Wire();
        connectDeviceWithWire(voltageSource, 1, w0);
        connectDeviceWithWire(r0, 0, w0);
        connectDeviceWithWire(r1, 0, w0);

        Wire w1 = new Wire();
        connectDeviceWithWire(voltageSource, 0, w1);
        connectDeviceWithWire(r0, 1, w1);
        connectDeviceWithWire(r1, 1, w1);

        List<Device> devices = ciruit.getDevices();
        devices.add(voltageSource);
        devices.add(r0);
        devices.add(r1);

        List<Wire> wires = ciruit.getWires();
        wires.add(w0);
        wires.add(w1);

        return ciruit;

    }

    //        <--w0
    //    |
    //    R0
    //    |   <--w1
    //    R1
    //    |   <--w2
    //
    public static Circuit generateOpenLoopCircuit() {

        Circuit ciruit = new Circuit();
        ciruit.setTitle("OpenLoopCircuit");

        Resistor r0 = new Resistor(100);
        Resistor r1 = new Resistor(200);

        Wire w0 = new Wire();
        connectDeviceWithWire(r0, 0, w0);

        Wire w1 = new Wire();
        connectDeviceWithWire(r0, 1, w1);
        connectDeviceWithWire(r1, 0, w1);

        Wire w2 = new Wire();
        connectDeviceWithWire(r1, 1, w2);


        List<Device> devices = ciruit.getDevices();
        devices.add(r0);
        devices.add(r1);

        List<Wire> wires = ciruit.getWires();
        wires.add(w0);
        wires.add(w1);
        wires.add(w2);

        return ciruit;

    }

    //     __________    <--w0
    //    |          |
    //  __|__        R0            R2
    //   ---         |   <--w1     |   <-w3
    //    |          R1            R3
    //    |__________|   <--w2
    //
    public static Circuit generateIsolatedBranchCircuit() {


        Circuit ciruit = new Circuit();
        ciruit.setTitle("IsolatedBranchCircuit");

        VoltageSource voltageSource = new VoltageSource();
        voltageSource.setVoltage(5.0);

        Resistor r0 = new Resistor(100);
        Resistor r1 = new Resistor(200);

        Wire w0 = new Wire();
        connectDeviceWithWire(voltageSource, 1, w0);
        connectDeviceWithWire(r0, 0, w0);

        Wire w1 = new Wire();
        connectDeviceWithWire(r0, 1, w1);
        connectDeviceWithWire(r1, 0, w1);

        Wire w2 = new Wire();
        connectDeviceWithWire(r1, 1, w2);
        connectDeviceWithWire(voltageSource, 0, w2);

        List<Device> devices = ciruit.getDevices();
        devices.add(voltageSource);
        devices.add(r0);
        devices.add(r1);

        List<Wire> wires = ciruit.getWires();
        wires.add(w0);
        wires.add(w1);
        wires.add(w2);

        Resistor r2 = new Resistor(300);
        Resistor r3 = new Resistor(400);
        Wire w3 = new Wire();
        connectDeviceWithWire(r2, 1, w3);
        connectDeviceWithWire(r3, 0, w3);

        devices.add(r2);
        devices.add(r3);

        wires.add(w3);

        return ciruit;
    }


    //     __________    <--w0
    //    |          |
    //    |          R0
    //  __|__        |___________    <-- w1
    //   ---         |           |
    //    |          R1          R2
    //    |__________|   <--w2   |   <-- w3
    //                           R3
    //
    public static Circuit generateOpenBranchCircuit() {

        Circuit ciruit = new Circuit();
        ciruit.setTitle("OpenBranchCircuit");

        VoltageSource voltageSource = new VoltageSource();
        voltageSource.setVoltage(5.0);

        Resistor r0 = new Resistor(100);
        Resistor r1 = new Resistor(200);

        Wire w0 = new Wire();
        connectDeviceWithWire(voltageSource, 1, w0);
        connectDeviceWithWire(r0, 0, w0);

        Wire w1 = new Wire();
        connectDeviceWithWire(r0, 1, w1);
        connectDeviceWithWire(r1, 0, w1);

        Wire w2 = new Wire();
        connectDeviceWithWire(r1, 1, w2);
        connectDeviceWithWire(voltageSource, 0, w2);

        List<Device> devices = ciruit.getDevices();
        devices.add(voltageSource);
        devices.add(r0);
        devices.add(r1);

        List<Wire> wires = ciruit.getWires();
        wires.add(w0);
        wires.add(w1);
        wires.add(w2);


        Resistor r2 = new Resistor(300);
        Resistor r3 = new Resistor(400);
        Wire w3 = new Wire();
        connectDeviceWithWire(r2, 1, w3);
        connectDeviceWithWire(r3, 0, w3);
        connectDeviceWithWire(r2, 0, w1);

        devices.add(r2);
        devices.add(r3);

        wires.add(w3);

        return ciruit;

    }


    private static void connectDeviceWithWire(Device device, int terminalIndex, Wire wire) {

        Terminal terminal = device.getTerminals().get(terminalIndex);
        terminal.setWire(wire);

        List<Terminal> wireTerminalList = wire.getTerminals();
        wireTerminalList.add(terminal);
    }

}
