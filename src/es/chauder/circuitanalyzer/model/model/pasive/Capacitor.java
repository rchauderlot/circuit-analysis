package es.chauder.circuitanalyzer.model.model.pasive;

/**
 * Created by rchauderlot on 30/11/15.
 */
public class Capacitor extends PassiveDevice {

    private double capacitance;

    public Capacitor(double capacitance) {
        super();
        this.capacitance = capacitance;
    }

    public double getImpedance(double frequency) {
        return 1/(capacitance*frequency);
    }

    public int getNumberOfTerminals() {
        return 2;
    }

}
