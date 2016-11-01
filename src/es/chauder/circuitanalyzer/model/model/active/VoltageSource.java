package es.chauder.circuitanalyzer.model.model.active;

import es.chauder.circuitanalyzer.model.model.arithmetic.Complex;
import es.chauder.circuitanalyzer.model.model.base.Device;

/**
 * Created by rchauderlot on 2/12/15.
 */
public class VoltageSource extends Device {


    private double voltage;

    public double getVoltage() {
        return voltage;
    }

    public void setVoltage(double voltage) {
        this.voltage = voltage;
    }


    @Override
    public int getNumberOfTerminals() {
        return 2;
    }

    @Override
    public Complex getIntentsity(double frequency) {
        return null;
    }

    @Override
    public Complex getVoltage(double frequency) {
        return new Complex(voltage, 0);
    }

    @Override
    public Complex getImpedance(double frequency) {
        return null;
    }
}
