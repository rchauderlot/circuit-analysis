package es.chauder.circuitanalyzer.tests;

import es.chauder.circuitanalyzer.model.model.analysis.AnalysisGroup;
import es.chauder.circuitanalyzer.model.model.analysis.AnalysisTopology;
import es.chauder.circuitanalyzer.model.model.analysis.Branch;
import es.chauder.circuitanalyzer.model.model.base.*;
import es.chauder.circuitanalyzer.model.service.analysis.AnalysisTopologyGenerator;
import org.testng.annotations.Test;


/**
 * Created by rchauderlot on 13/12/15.
 */
public class NodeGenerationTest {

    @Test
    public void testSeriesResistorCircuit() throws Exception {


        Circuit circuit1 = SampleCircuitGenerator.generateSeriesResistorCircuit();
        testNodeNumber(circuit1);

    }


    @Test
    public void testParallelResistorCircuit() throws Exception {


        Circuit circuit2 = SampleCircuitGenerator.generateParallelResistorCircuit();
        testNodeNumber(circuit2);


    }
    @Test
    public void testOpenLoopCircuit() throws Exception {


        Circuit circuit3 = SampleCircuitGenerator.generateOpenLoopCircuit();
        testNodeNumber(circuit3);


    }
    @Test
    public void testIsolatedBranchCircuit() throws Exception {


        Circuit circuit4 = SampleCircuitGenerator.generateIsolatedBranchCircuit();
        testNodeNumber(circuit4);


    }



    @Test
    public void testOpenBranchCircuit() throws Exception {

        Circuit circuit5 = SampleCircuitGenerator.generateOpenBranchCircuit();
        testNodeNumber(circuit5);

    }


    private void testNodeNumber(Circuit circuit) throws Exception {

        int numberOfNodes = 0;
        for (Device d : circuit.getDevices()) {
            int connectedTerminals = 0;
            for (Terminal t : d.getTerminals()) {
                if (t.getWire() != null) {
                    connectedTerminals ++;
                }
            }

            if (connectedTerminals > 2) {
                numberOfNodes ++;
            }
        }

        for (Wire w : circuit.getWires()) {
            if (w.getTerminals().size() > 2) {
                numberOfNodes ++;
            }
        }


        AnalysisTopology topology = AnalysisTopologyGenerator.createElectronicTopology(circuit);

        int numOfGeneratedNodes = 0;
        for (AnalysisGroup ag : topology.getAnalysisGroups()) {
            numOfGeneratedNodes += ag.getNodes().size();
        }

        assert numberOfNodes == numOfGeneratedNodes;

    }
}