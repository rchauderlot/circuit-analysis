package es.chauder.circuitanalyzer.model.model.pasive;

import es.chauder.circuitanalyzer.model.model.Device;

/**
 * Created by rchauderlot on 30/11/15.
 */
public class Inductor extends PassiveDevice {


    double inductance;

    public Inductor(double inductance) {
        super();
        this.inductance = inductance;
    }

    public double getImpedance(double frequency) {
        return inductance*frequency;
    }

    public int getNumberOfTerminals() {
        return 2;
    }

}
