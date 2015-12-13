package es.chauder.circuitanalyzer.tests;

import es.chauder.circuitanalyzer.model.model.analysis.AnalysisTopology;
import es.chauder.circuitanalyzer.model.model.analysis.Branch;
import es.chauder.circuitanalyzer.model.model.base.Circuit;
import es.chauder.circuitanalyzer.model.model.base.Connector;
import es.chauder.circuitanalyzer.model.model.base.Device;
import es.chauder.circuitanalyzer.model.model.base.Terminal;
import es.chauder.circuitanalyzer.model.service.AnalysisTopologyGenerator;
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

    private void testNumberOfGroups(Circuit circuit, int groups) throws Exception {

        AnalysisTopology topology = AnalysisTopologyGenerator.createElectronicTopology(circuit);
        assert topology.getAnalysisGroups().size() == groups;


    }

}