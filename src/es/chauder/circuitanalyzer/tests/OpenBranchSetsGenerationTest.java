package es.chauder.circuitanalyzer.tests;

import es.chauder.circuitanalyzer.model.model.analysis.AnalysisGroup;
import es.chauder.circuitanalyzer.model.model.analysis.AnalysisTopology;
import es.chauder.circuitanalyzer.model.model.base.Circuit;
import es.chauder.circuitanalyzer.model.service.analysis.AnalysisTopologyGenerator;
import org.testng.annotations.Test;


/**
 * Created by rchauderlot on 13/12/15.
 */
public class OpenBranchSetsGenerationTest {

    @Test
    public void testSeriesResistorCircuit() throws Exception {


        Circuit circuit1 = SampleCircuitGenerator.generateSeriesResistorCircuit();
        testOpenBranchNumber(circuit1, 0);

    }


    @Test
    public void testParallelResistorCircuit() throws Exception {


        Circuit circuit2 = SampleCircuitGenerator.generateParallelResistorCircuit();
        testOpenBranchNumber(circuit2, 0);


    }
    @Test
    public void testOpenLoopCircuit() throws Exception {


        Circuit circuit3 = SampleCircuitGenerator.generateOpenLoopCircuit();
        testOpenBranchNumber(circuit3, 1);


    }
    @Test
    public void testIsolatedBranchCircuit() throws Exception {


        Circuit circuit4 = SampleCircuitGenerator.generateIsolatedBranchCircuit();
        testOpenBranchNumber(circuit4, 1);


    }



    @Test
    public void testOpenBranchCircuit() throws Exception {

        Circuit circuit5 = SampleCircuitGenerator.generateOpenBranchCircuit();
        testOpenBranchNumber(circuit5, 1);

    }

    @Test
    public void testsThreeNetworkCircuit() throws Exception {

        Circuit circuit6 = SampleCircuitGenerator.generateThreeNetworkCircuit();
        testOpenBranchNumber(circuit6, 0);

    }


    private void testOpenBranchNumber(Circuit circuit, int openBranches) throws Exception {

        AnalysisTopology topology = AnalysisTopologyGenerator.createElectronicTopology(circuit, null);

        int numOfOpenBranches = 0;
        for (AnalysisGroup ag : topology.getAnalysisGroups()) {
            numOfOpenBranches += ag.getOpenBranches().size();
        }

        assert openBranches == numOfOpenBranches;

    }
}