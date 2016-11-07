package es.chauder.circuitanalyzer.tests;

import es.chauder.circuitanalyzer.model.model.analysis.AnalysisGroup;
import es.chauder.circuitanalyzer.model.model.analysis.AnalysisTopology;
import es.chauder.circuitanalyzer.model.model.base.Circuit;
import es.chauder.circuitanalyzer.model.service.analysis.AnalysisTopologyGenerator;
import org.testng.annotations.Test;


/**
 * Created by rchauderlot on 13/12/15.
 */
public class ClosedBranchSetsGenerationTest {

    @Test
    public void testSeriesResistorCircuit() throws Exception {


        Circuit circuit1 = SampleCircuitGenerator.generateSeriesResistorCircuit();
        testClosedBranchNumber(circuit1, 1);

    }


    @Test
    public void testParallelResistorCircuit() throws Exception {


        Circuit circuit2 = SampleCircuitGenerator.generateParallelResistorCircuit();
        testClosedBranchNumber(circuit2, 3);


    }
    @Test
    public void testOpenLoopCircuit() throws Exception {


        Circuit circuit3 = SampleCircuitGenerator.generateOpenLoopCircuit();
        testClosedBranchNumber(circuit3, 0);


    }
    @Test
    public void testIsolatedBranchCircuit() throws Exception {


        Circuit circuit4 = SampleCircuitGenerator.generateIsolatedBranchCircuit();
        testClosedBranchNumber(circuit4, 1);


    }

    @Test
    public void testOpenBranchCircuit() throws Exception {

        Circuit circuit5 = SampleCircuitGenerator.generateOpenBranchCircuit();
        testClosedBranchNumber(circuit5, 1);

    }

    @Test
    public void testsThreeNetworkCircuit() throws Exception {

        Circuit circuit6 = SampleCircuitGenerator.generateThreeNetworkCircuit();
        testClosedBranchNumber(circuit6, 6);

    }


    private void testClosedBranchNumber(Circuit circuit, int closedBranches) throws Exception {

        AnalysisTopology topology = AnalysisTopologyGenerator.createElectronicTopology(circuit, null);

        int numOfClosedBranches = 0;
        for (AnalysisGroup ag : topology.getAnalysisGroups()) {
            numOfClosedBranches += ag.getClosedBranches().size();
        }

        assert closedBranches == numOfClosedBranches;

    }
}