package es.chauder.circuitanalyzer.model.model.pasive;

import es.chauder.circuitanalyzer.model.model.Device;

/**
 * Created by rchauderlot on 30/11/15.
 */
public abstract class PassiveDevice extends Device {

    public double getIntentsity(double frequency) {
        return 0;
    }

    public double getVoltage(double frequency) {
        return 0;
    }

}
