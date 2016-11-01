package es.chauder.circuitanalyzer.model.service;

import es.chauder.circuitanalyzer.model.model.wiring.WiringCollection;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by rchauderlot on 30/11/15.
 */
public class CircuitStore {

    public static WiringCollection XMLToWiringCollection(String filename) {
        try {
            JAXBContext context = JAXBContext.newInstance(WiringCollection.class);
            Unmarshaller un = context.createUnmarshaller();
            WiringCollection emp = (WiringCollection) un.unmarshal(new File(filename));
            return emp;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void wiringCollectionToXML(WiringCollection emp, String filename) {

        try {
            JAXBContext context = JAXBContext.newInstance(WiringCollection.class);
            Marshaller m = context.createMarshaller();
            //for pretty-print XML in JAXB
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // Write to System.out for debugging
            // m.marshal(emp, System.out);

            // Write to File
            m.marshal(emp, new File(filename));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
