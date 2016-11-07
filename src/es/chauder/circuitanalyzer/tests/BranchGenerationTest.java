package es.chauder.circuitanalyzer.tests;

import es.chauder.circuitanalyzer.model.model.analysis.AnalysisTopology;
import es.chauder.circuitanalyzer.model.model.analysis.Branch;
import es.chauder.circuitanalyzer.model.model.base.*;
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

    @Test
    public void testBranchNumberThreeNetworkCircuit() throws Exception {

        Circuit circuit6 = SampleCircuitGenerator.generateThreeNetworkCircuit();
        testBranchNumber(circuit6, 6);

    }

    private void testBranchNumber(Circuit circuit, int branches) throws Exception {

        AnalysisTopology topology = AnalysisTopologyGenerator.createElectronicTopology(circuit, null);
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

        AnalysisTopology topology = AnalysisTopologyGenerator.createElectronicTopology(circuit, null);
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

    @Test
    public void testUnusedWiresThreeNetworkCircuit() throws Exception {

        Circuit circuit6 = SampleCircuitGenerator.generateThreeNetworkCircuit();
        testUnusedWires(circuit6, 0);

    }

    private void testUnusedWires(Circuit circuit, int unused) throws Exception {

        AnalysisTopology topology = AnalysisTopologyGenerator.createElectronicTopology(circuit, null);
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

    @Test
    public void testNumberOfTerminalsThreeNetworkCircuit() throws Exception {

        Circuit circuit6 = SampleCircuitGenerator.generateThreeNetworkCircuit();
        testNumberOfTerminals(circuit6);

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

        AnalysisTopology topology = AnalysisTopologyGenerator.createElectronicTopology(circuit, null);

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



    @Test
    public void testNumberOfWiresUsedByBranchesSeriesResistorCircuit() throws Exception {

        Circuit circuit1 = SampleCircuitGenerator.generateSeriesResistorCircuit();
        testNumberOfWiresUsedByBranches(circuit1);

    }

    @Test
    public void testNumberOfWiresUsedByBranchesParallelResistorCircuit() throws Exception {

        Circuit circuit2 = SampleCircuitGenerator.generateParallelResistorCircuit();
        testNumberOfWiresUsedByBranches(circuit2);

    }

    @Test
    public void testNumberOfWiresUsedByBranchesOpenLoopCircuit() throws Exception {

        Circuit circuit3 = SampleCircuitGenerator.generateOpenLoopCircuit();
        testNumberOfWiresUsedByBranches(circuit3);

    }

    @Test
    public void testNumberOfWiresUsedByBranchesIsolatedBranchCircuit() throws Exception {

        Circuit circuit4 = SampleCircuitGenerator.generateIsolatedBranchCircuit();
        testNumberOfWiresUsedByBranches(circuit4);

    }

    @Test
    public void testNumberOfWiresUsedByBranchesOpenBranchCircuit() throws Exception {

        Circuit circuit5 = SampleCircuitGenerator.generateOpenBranchCircuit();
        testNumberOfWiresUsedByBranches(circuit5);

    }

    @Test
    public void testNumberOfWiresUsedByBranchesThreeNetworkCircuit() throws Exception {

        Circuit circuit6 = SampleCircuitGenerator.generateThreeNetworkCircuit();
        testNumberOfWiresUsedByBranches(circuit6);

    }

    private void testNumberOfWiresUsedByBranches(Circuit circuit) throws Exception {

        int numberOfClassifiedWires = 0;
        for (Wire w : circuit.getWires()) {
            if (w.getTerminals().size() > 0 && w.getTerminals().size() <= 2) {
                numberOfClassifiedWires ++;
            } else {
                numberOfClassifiedWires += w.getTerminals().size();
            }
        }

        AnalysisTopology topology = AnalysisTopologyGenerator.createElectronicTopology(circuit, null);

        int numOfWiresInBranches = 0;
        for (Branch b : topology.getBranches()) {
            for (Connector c : b.getElements()) {
                if (c instanceof Wire) {
                    numOfWiresInBranches ++;
                }
            }
            if (b.getFirstElement() == b.getLastElement()) {
                Connector c = b.getFirstElement();
                if ((c instanceof Wire && ((Wire)c).getTerminals().size() == 2)) {
                    numOfWiresInBranches--;
                } else if ((c instanceof Terminal && ((Terminal)c).getDevice() != null && ((Terminal)c).getDevice().getTerminals().size() == 2)) {
                    numOfWiresInBranches--;
                }
            }

        }

        assert numberOfClassifiedWires == numOfWiresInBranches;

    }
}