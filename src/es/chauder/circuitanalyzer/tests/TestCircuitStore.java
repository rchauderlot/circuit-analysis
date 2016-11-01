package es.chauder.circuitanalyzer.tests;

import es.chauder.circuitanalyzer.model.model.base.Circuit;
import es.chauder.circuitanalyzer.model.model.wiring.Wiring;
import es.chauder.circuitanalyzer.model.model.wiring.WiringCollection;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

/**
 * Created by rchauderlot on 14/2/16.
 */
public class TestCircuitStore {

    @Test
    public void testSeriesResistorCircuit() throws Exception {


        String filename = "test_intemediate.xml";

        Circuit circuit = SampleCircuitGenerator.generateSeriesResistorCircuit();
        Wiring wiring = new Wiring();
        wiring.setCircuit(circuit);

        WiringCollection wiringCollection = new WiringCollection();
        wiringCollection.getWirings().add(wiring);




    }


    private void deleteFile(String filename) {

//        File.createTempFile()
//        try {
//            Files.delete(path);
//        } catch (NoSuchFileException x) {
//            System.err.format("%s: no such" + " file or directory%n", path);
//        } catch (DirectoryNotEmptyException x) {
//            System.err.format("%s not empty%n", path);
//        } catch (IOException x) {
//            // File permission problems are caught here.
//            System.err.println(x);
//        }

    }
}
