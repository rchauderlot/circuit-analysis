package es.chauder.circuitanalyzer.model.model.base;

import java.util.ArrayList;
import java.util.List;

/**
 * Base class for all junction parts like device terminals or wires.
 *
 * Created by rchauderlot on 2/12/15.
 */
public abstract class Connector {

    public abstract boolean isLinkedTo(Connector connector);
    public abstract List<Connector> getConnectorsConnectedToConnector();

}
