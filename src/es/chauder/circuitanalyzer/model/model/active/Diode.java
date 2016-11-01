package es.chauder.circuitanalyzer.model.model.active;

import es.chauder.circuitanalyzer.model.model.base.Device;

/**
 * Created by rchauderlot on 30/11/15.
 */
public class Diode /* extends Device */ {

    public double getIntentsity(double frequency) {
        return 0;
    }
    public double getVoltage(double frequency) {
        return -0.7;
    }
    public double getImpedance(double frequency) {
        return 0;
    }

    public int getNumberOfTerminals() {
        return 2;
    }

}
