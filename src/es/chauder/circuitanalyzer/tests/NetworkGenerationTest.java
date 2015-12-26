package es.chauder.circuitanalyzer.tests;

import es.chauder.circuitanalyzer.model.model.analysis.AnalysisGroup;
import es.chauder.circuitanalyzer.model.model.analysis.AnalysisTopology;
import es.chauder.circuitanalyzer.model.model.base.Circuit;
import es.chauder.circuitanalyzer.model.service.analysis.AnalysisTopologyGenerator;
import org.testng.annotations.Test;


/**
 * Created by rchauderlot on 13/12/15.
 */
public class NetworkGenerationTest {

    @Test
    public void testSeriesResistorCircuit() throws Exception {


        Circuit circuit1 = SampleCircuitGenerator.generateSeriesResistorCircuit();
        testNetworkNumber(circuit1, 1);

    }


    @Test
    public void testParallelResistorCircuit() throws Exception {


        Circuit circuit2 = SampleCircuitGenerator.generateParallelResistorCircuit();
        testNetworkNumber(circuit2, 2);


    }
    @Test
    public void testOpenLoopCircuit() throws Exception {


        Circuit circuit3 = SampleCircuitGenerator.generateOpenLoopCircuit();
        testNetworkNumber(circuit3, 0);


    }
    @Test
    public void testIsolatedBranchCircuit() throws Exception {


        Circuit circuit4 = SampleCircuitGenerator.generateIsolatedBranchCircuit();
        testNetworkNumber(circuit4, 1);


    }



    @Test
    public void testOpenBranchCircuit() throws Exception {

        Circuit circuit5 = SampleCircuitGenerator.generateOpenBranchCircuit();
        testNetworkNumber(circuit5, 1);

    }


    @Test
    public void testThreeNetworkCircuit() throws Exception {

        Circuit circuit6 = SampleCircuitGenerator.generateThreeNetworkCircuit();
        testNetworkNumber(circuit6, 3);

    }

    private void testNetworkNumber(Circuit circuit, int numberOfNetworks) throws Exception {

        AnalysisTopology topology = AnalysisTopologyGenerator.createElectronicTopology(circuit);

        int numOfGeneratedNetworks = 0;
        for (AnalysisGroup ag : topology.getAnalysisGroups()) {
            numOfGeneratedNetworks += ag.getNetworks().size();
        }

        assert numberOfNetworks == numOfGeneratedNetworks;

    }
}