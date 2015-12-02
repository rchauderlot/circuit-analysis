package es.chauder.circuitanalyzer.model.model.base;

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
    public abstract double getIntentsity(double frequency);
    public abstract double getVoltage(double frequency);
    public abstract double getImpedance(double frequency);

    public Device(List<Terminal> terminals) {
        super();
        this.terminals = terminals;
    }

    public Device() {
        super();
        generateDefaultTerminals();
    }

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

