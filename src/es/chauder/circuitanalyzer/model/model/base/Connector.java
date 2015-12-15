package es.chauder.circuitanalyzer.model.model.base;

/**
 * Base class for all junction parts like device terminals or wires.
 *
 * Created by rchauderlot on 2/12/15.
 */
public class Connector {

    public boolean isLinkedTo(Connector connector) {
        return equals(connector);
    }
}
