package es.chauder.circuitanalyzer.tests;

import es.chauder.circuitanalyzer.model.model.analysis.AnalysisTopology;
import es.chauder.circuitanalyzer.model.model.analysis.Branch;
import es.chauder.circuitanalyzer.model.model.base.Circuit;
import es.chauder.circuitanalyzer.model.model.base.Connector;
import es.chauder.circuitanalyzer.model.model.base.Device;
import es.chauder.circuitanalyzer.model.model.base.Terminal;
import es.chauder.circuitanalyzer.model.service.analysis.AnalysisTopologyGenerator;
import org.testng.annotations.Test;


/**
 * Created by rchauderlot on 13/12/15.
 */
public class BranchGenerationTest {

    @Test
    public void testBranchNumberSeriesResistorCircuit() throws Exception {


        Circuit circuit1 = SampleCircuitGenerator.generateSeriesResistorCircuit();
        testBranchNumber(circuit1, 1);

    }


    @Test
    public void testBranchNumberParallelResistorCircuit() throws Exception {


        Circuit circuit2 = SampleCircuitGenerator.generateParallelResistorCircuit();
        testBranchNumber(circuit2, 3);


    }
    @Test
    public void testBranchNumberSeriesOpenLoopCircuit() throws Exception {


        Circuit circuit3 = SampleCircuitGenerator.generateOpenLoopCircuit();
        testBranchNumber(circuit3, 1);


    }
    @Test
    public void testBranchNumberIsolatedBranchCircuit() throws Exception {


        Circuit circuit4 = SampleCircuitGenerator.generateIsolatedBranchCircuit();
        testBranchNumber(circuit4, 2);


    }

    @Test
    public void testBranchNumberOpenBranchCircuit() throws Exception {

        Circuit circuit5 = SampleCircuitGenerator.generateOpenBranchCircuit();
        testBranchNumber(circuit5, 2);

    }

    private void testBranchNumber(Circuit circuit, int branches) throws Exception {

        AnalysisTopology topology = AnalysisTopologyGenerator.createElectronicTopology(circuit);
        assert topology.getBranches().size() == branches;


    }


    @Test
    public void testUnusedDevicesSeriesResistorCircuit() throws Exception {


        Circuit circuit1 = SampleCircuitGenerator.generateSeriesResistorCircuit();
        testUnusedDevices(circuit1, 0);

    }

    @Test
    public void testUnusedDevicesParallelResistorCircuit() throws Exception {

        Circuit circuit2 = SampleCircuitGenerator.generateParallelResistorCircuit();
        testUnusedDevices(circuit2, 0);

    }

    @Test
    public void testUnusedDevicesOpenLoopCircuit() throws Exception {


        Circuit circuit3 = SampleCircuitGenerator.generateOpenLoopCircuit();
        testUnusedDevices(circuit3, 0);


    }

    @Test
    public void testUnusedDevicesIsolatedBranchCircuit() throws Exception {


        Circuit circuit4 = SampleCircuitGenerator.generateIsolatedBranchCircuit();
        testUnusedDevices(circuit4, 0);

    }


    @Test
    public void testUnusedDevicesOpenBranchCircuit() throws Exception {

        Circuit circuit5 = SampleCircuitGenerator.generateOpenBranchCircuit();
        testUnusedDevices(circuit5, 0);

    }

    private void testUnusedDevices(Circuit circuit, int unused) throws Exception {

        AnalysisTopology topology = AnalysisTopologyGenerator.createElectronicTopology(circuit);
        assert topology.getUnusedDevices().size() == unused;

    }

    @Test
    public void testUnusedWiresSeriesResistorCircuit() throws Exception {

        Circuit circuit1 = SampleCircuitGenerator.generateSeriesResistorCircuit();
        testUnusedWires(circuit1, 0);

    }

    @Test
    public void testUnusedWiresParallelResistorCircuit() throws Exception {

        Circuit circuit2 = SampleCircuitGenerator.generateParallelResistorCircuit();
        testUnusedWires(circuit2, 0);

    }

    @Test
    public void testUnusedWiresOpenLoopCircuit() throws Exception {

        Circuit circuit3 = SampleCircuitGenerator.generateOpenLoopCircuit();
        testUnusedWires(circuit3, 0);

    }

    @Test
    public void testUnusedWiresIsolatedBranchCircuit() throws Exception {

        Circuit circuit4 = SampleCircuitGenerator.generateIsolatedBranchCircuit();
        testUnusedWires(circuit4, 0);

    }

    @Test
    public void testUnusedWiresOpenBranchCircuit() throws Exception {

        Circuit circuit5 = SampleCircuitGenerator.generateOpenBranchCircuit();
        testUnusedWires(circuit5, 0);

    }


    private void testUnusedWires(Circuit curcuit, int unused) throws Exception {

        AnalysisTopology topology = AnalysisTopologyGenerator.createElectronicTopology(curcuit);
        assert topology.getUnusedWires().size() == unused;

    }


    @Test
    public void testNumberOfTerminalsSeriesResistorCircuit() throws Exception {

        Circuit circuit1 = SampleCircuitGenerator.generateSeriesResistorCircuit();
        testNumberOfTerminals(circuit1);

    }

    @Test
    public void testNumberOfTerminalsParallelResistorCircuit() throws Exception {

        Circuit circuit2 = SampleCircuitGenerator.generateParallelResistorCircuit();
        testNumberOfTerminals(circuit2);

    }

    @Test
    public void testNumberOfTerminalsOpenLoopCircuit() throws Exception {

        Circuit circuit3 = SampleCircuitGenerator.generateOpenLoopCircuit();
        testNumberOfTerminals(circuit3);

    }

    @Test
    public void testNumberOfTerminalsIsolatedBranchCircuit() throws Exception {

        Circuit circuit4 = SampleCircuitGenerator.generateIsolatedBranchCircuit();
        testNumberOfTerminals(circuit4);

    }

    @Test
    public void testNumberOfTerminalsOpenBranchCircuit() throws Exception {

        Circuit circuit5 = SampleCircuitGenerator.generateOpenBranchCircuit();
        testNumberOfTerminals(circuit5);

    }


    private void testNumberOfTerminals(Circuit circuit) throws Exception {

        int numberOfUsedTerminals = 0;
        for (Device d : circuit.getDevices()) {
            for (Terminal t : d.getTerminals()) {
                if (t.getWire() != null) {
                    numberOfUsedTerminals ++;
                }
            }
        }

        AnalysisTopology topology = AnalysisTopologyGenerator.createElectronicTopology(circuit);

        int numOfTerminalInBranches = 0;
        for (Branch b : topology.getBranches()) {
            for (Connector c : b.getElements()) {
                if (c instanceof Terminal) {
                    numOfTerminalInBranches ++;
                }
            }

        }

        assert numberOfUsedTerminals == numOfTerminalInBranches;

    }
}