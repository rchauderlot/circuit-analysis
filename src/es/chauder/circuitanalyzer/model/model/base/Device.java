package es.chauder.circuitanalyzer.model.model.base;

import es.chauder.circuitanalyzer.model.model.arithmetic.Complex;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Base class for all electronic devices.
 *
 * Created by rchauderlot on 30/11/15.
 */
public abstract class Device {

    public abstract int getNumberOfTerminals();
    public abstract Complex getIntentsity(double frequency);
    public abstract Complex getVoltage(double frequency);
    public abstract Complex getImpedance(double frequency);

    public Device(List<Terminal> terminals) {
        super();
        this.terminals = terminals;
    }

    public Device() {
        super();
        generateDefaultTerminals();
    }

    @XmlElement
    public List<Terminal> getTerminals() {
        return terminals;
    }

    public void setTerminals(List<Terminal> terminals) {
        this.terminals = terminals;
    }

    private List<Terminal> terminals = new ArrayList<>();

    private void generateDefaultTerminals() {

        for (int i = 0 ; i < getNumberOfTerminals(); i++) {
            Terminal terminal = new Terminal(this, i);
            terminals.add(terminal);
        }

    }
}

