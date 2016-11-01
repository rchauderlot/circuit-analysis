package es.chauder.circuitanalyzer.model.model.pasive;

import es.chauder.circuitanalyzer.model.model.arithmetic.Complex;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by rchauderlot on 30/11/15.
 */
public class Inductor extends PassiveDevice {


    public Inductor(double inductance) {
        super();
        this.inductance = inductance;
    }

    @XmlElement
    public double getInductance() {
        return inductance;
    }

    public void setInductance(double inductance) {
        this.inductance = inductance;
    }

    public Complex getImpedance(double frequency) {
        return new Complex(0, inductance*frequency);
    }

    public int getNumberOfTerminals() {
        return 2;
    }


    double inductance;

}
