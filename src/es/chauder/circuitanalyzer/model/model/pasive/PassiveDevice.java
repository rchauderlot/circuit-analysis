package es.chauder.circuitanalyzer.model.model.pasive;

import es.chauder.circuitanalyzer.model.model.arithmetic.Complex;
import es.chauder.circuitanalyzer.model.model.base.Device;

/**
 * Created by rchauderlot on 30/11/15.
 */
public abstract class PassiveDevice extends Device {

    public Complex getIntentsity(double frequency) {
        return null;
    }

    public Complex getVoltage(double frequency) {
        return null;
    }

}
