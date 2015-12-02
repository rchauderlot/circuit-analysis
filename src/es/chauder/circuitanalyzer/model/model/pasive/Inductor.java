package es.chauder.circuitanalyzer.model.model.pasive;

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
