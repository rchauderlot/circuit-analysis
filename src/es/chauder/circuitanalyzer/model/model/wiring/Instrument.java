package es.chauder.circuitanalyzer.model.model.wiring;

import es.chauder.circuitanalyzer.model.model.base.Circuit;
import es.chauder.circuitanalyzer.model.model.base.Wire;

/**
 * Created by rchauderlot on 1/3/16.
 */
public class Instrument {

    private Circuit circuit;
    private Wire lowerPotential;
    private Wire higherPotential;

    public Instrument(Circuit circuit, Wire lowerPotential, Wire higherPotential) {
        super();
        this.circuit = circuit;
        this.lowerPotential = lowerPotential;
        this.higherPotential = higherPotential;

    }
    public Circuit getCircuit() {
        return circuit;
    }

    public Wire getLowerPotential() {
        return lowerPotential;
    }

    public void setLowerPotential(Wire lowerPotential) {
        this.lowerPotential = lowerPotential;
    }

    public Wire getHigherPotential() {
        return higherPotential;
    }

    public void setHigherPotential(Wire higherPotential) {
        this.higherPotential = higherPotential;
    }

}
