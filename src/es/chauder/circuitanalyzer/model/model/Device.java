package es.chauder.circuitanalyzer.model.model;

import java.util.List;

/**
 * Created by rchauderlot on 30/11/15.
 */
public abstract class Device extends ElectronicElement{

    private List<Wire> connections;

    public List<Wire> getConnections() {
        return connections;
    }

    public abstract int getNumberOfTerminals();
    public abstract double getIntentsity(double frequency);
    public abstract double getVoltage(double frequency);
    public abstract double getImpedance(double frequency);

}
