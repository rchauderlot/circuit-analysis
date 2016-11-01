package es.chauder.circuitanalyzer.model.model.pasive;

import es.chauder.circuitanalyzer.model.model.arithmetic.Complex;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by rchauderlot on 30/11/15.
 */
public class Resistor extends PassiveDevice {

    public Resistor(double resistance) {
        super();
        this.resistance = resistance;
    }

    @XmlElement
    public double getResistance() {
        return resistance;
    }

    public void setResistance(double resistance) {
        this.resistance = resistance;
    }


    public Complex getImpedance(double frequency) {
        return new Complex(resistance, 0);
    }

    public int getNumberOfTerminals() {
        return 2;
    }

    double resistance;

}
