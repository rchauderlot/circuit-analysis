package es.chauder.circuitanalyzer.model.model.pasive;

import es.chauder.circuitanalyzer.model.model.arithmetic.Complex;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by rchauderlot on 30/11/15.
 */
public class Capacitor extends PassiveDevice {

    public Capacitor(double capacitance) {
        super();
        this.capacitance = capacitance;
    }

    @XmlElement
    public double getCapacitance() {
        return capacitance;
    }

    public void setCapacitance(double capacitance) {
        this.capacitance = capacitance;
    }

    public Complex getImpedance(double frequency) {
        return Complex.ONE.divide(new Complex(0, capacitance*frequency));
    }

    public int getNumberOfTerminals() {
        return 2;
    }


    private double capacitance;


}
