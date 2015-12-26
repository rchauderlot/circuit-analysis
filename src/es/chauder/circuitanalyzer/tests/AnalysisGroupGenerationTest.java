package es.chauder.circuitanalyzer.tests;

import es.chauder.circuitanalyzer.model.model.analysis.AnalysisGroup;
import es.chauder.circuitanalyzer.model.model.analysis.AnalysisTopology;
import es.chauder.circuitanalyzer.model.model.base.Circuit;
import es.chauder.circuitanalyzer.model.service.analysis.AnalysisTopologyGenerator;
import org.testng.annotations.Test;


/**
 * Created by rchauderlot on 13/12/15.
 */
public class AnalysisGroupGenerationTest {

    @Test
    public void testNumberOfGroupsSeriesResistorCircuit() throws Exception {


        Circuit circuit1 = SampleCircuitGenerator.generateSeriesResistorCircuit();
        testNumberOfGroups(circuit1, 1);

    }


    @Test
    public void testNumberOfGroupsParallelResistorCircuit() throws Exception {


        Circuit circuit2 = SampleCircuitGenerator.generateParallelResistorCircuit();
        testNumberOfGroups(circuit2, 1);


    }
    @Test
    public void testNumberOfGroupsSeriesOpenLoopCircuit() throws Exception {


        Circuit circuit3 = SampleCircuitGenerator.generateOpenLoopCircuit();
        testNumberOfGroups(circuit3, 1);


    }
    @Test
    public void testNumberOfGroupsIsolatedBranchCircuit() throws Exception {


        Circuit circuit4 = SampleCircuitGenerator.generateIsolatedBranchCircuit();
        testNumberOfGroups(circuit4, 2);


    }

    @Test
    public void testNumberOfGroupsOpenBranchCircuit() throws Exception {

        Circuit circuit5 = SampleCircuitGenerator.generateOpenBranchCircuit();
        testNumberOfGroups(circuit5, 1);

    }

    @Test
    public void testNumberOfGroupsThreeNetworkCircuit() throws Exception {

        Circuit circuit6 = SampleCircuitGenerator.generateThreeNetworkCircuit();
        testNumberOfGroups(circuit6, 1);

    }

    private void testNumberOfGroups(Circuit circuit, int groups) throws Exception {

        AnalysisTopology topology = AnalysisTopologyGenerator.createElectronicTopology(circuit);
        assert topology.getAnalysisGroups().size() == groups;


    }


    @Test
    public void testNumberOfBranchesInGroupsSeriesResistorCircuit() throws Exception {


        Circuit circuit1 = SampleCircuitGenerator.generateSeriesResistorCircuit();
        testNumberOfBranchesInGroups(circuit1);

    }


    @Test
    public void testNumberOfBranchesInGroupsParallelResistorCircuit() throws Exception {


        Circuit circuit2 = SampleCircuitGenerator.generateParallelResistorCircuit();
        testNumberOfBranchesInGroups(circuit2);


    }
    @Test
    public void testNumberOfBranchesInGroupsSeriesOpenLoopCircuit() throws Exception {


        Circuit circuit3 = SampleCircuitGenerator.generateOpenLoopCircuit();
        testNumberOfBranchesInGroups(circuit3);


    }
    @Test
    public void testNumberOfBranchesInGroupsIsolatedBranchCircuit() throws Exception {


        Circuit circuit4 = SampleCircuitGenerator.generateIsolatedBranchCircuit();
        testNumberOfBranchesInGroups(circuit4);


    }

    @Test
    public void testNumberOfBranchesInGroupsOpenBranchCircuit() throws Exception {

        Circuit circuit5 = SampleCircuitGenerator.generateOpenBranchCircuit();
        testNumberOfBranchesInGroups(circuit5);

    }

    @Test
    public void testNumberOfBranchesThreeNetworkCircuit() throws Exception {

        Circuit circuit6 = SampleCircuitGenerator.generateThreeNetworkCircuit();
        testNumberOfBranchesInGroups(circuit6);

    }

    private void testNumberOfBranchesInGroups(Circuit circuit) throws Exception {

        AnalysisTopology topology = AnalysisTopologyGenerator.createElectronicTopology(circuit);

        int numberOfBranchesInGroups = 0;
        for (AnalysisGroup ag : topology.getAnalysisGroups()) {
            numberOfBranchesInGroups += ag.getBranches().size();
        }


        assert topology.getBranches().size() == numberOfBranchesInGroups;


    }
}