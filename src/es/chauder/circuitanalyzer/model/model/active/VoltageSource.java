package es.chauder.circuitanalyzer.model.model.active;

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
    public double getIntentsity(double frequency) {
        return 0;
    }

    @Override
    public double getVoltage(double frequency) {
        return voltage;
    }

    @Override
    public double getImpedance(double frequency) {
        return 0;
    }
}
