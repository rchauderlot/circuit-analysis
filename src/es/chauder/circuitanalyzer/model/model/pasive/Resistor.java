package es.chauder.circuitanalyzer.model.model.pasive;

/**
 * Created by rchauderlot on 30/11/15.
 */
public class Resistor extends PassiveDevice {

    double resistance;

    public Resistor(double resistance) {
        super();
        this.resistance = resistance;
    }

    public double getImpedance(double frequency) {
        return resistance;
    }

    public int getNumberOfTerminals() {
        return 2;
    }


}
