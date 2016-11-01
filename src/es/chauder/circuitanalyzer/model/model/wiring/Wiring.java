package es.chauder.circuitanalyzer.model.model.wiring;

import java.util.List;
import es.chauder.circuitanalyzer.model.model.base.Circuit;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by rchauderlot on 21/1/16.
 */
public class Wiring {

    @XmlElement(name = "circuit")
    public Circuit getCircuit() {
        return circuit;
    }

    public void setCircuit(Circuit circuit) {
        this.circuit = circuit;
    }

    @XmlElement(name = "instruments")
    public List<Instrument> getInstruments() {
        return instruments;
    }

    public void setInstruments(List<Instrument> instruments) {
        this.instruments = instruments;
    }

    Circuit circuit;
    List<Instrument> instruments;

}
