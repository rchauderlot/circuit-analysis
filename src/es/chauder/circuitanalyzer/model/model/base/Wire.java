package es.chauder.circuitanalyzer.model.model.base;

import java.util.List;

import java.util.ArrayList;

/**
 * Created by rchauderlot on 30/11/15.
 */
public class Wire extends Connector {

    private List<Terminal> terminals = new ArrayList<Terminal>();

    public List<Terminal> getTerminals() {
        return terminals;
    }

    public boolean isLinkedTo(Connector connector) {

        return connector instanceof Wire && equals(connector);
    }

    public List<Connector> getConnectorsConnectedToConnector() {

        return new ArrayList<Connector>(getTerminals());

    }
}
