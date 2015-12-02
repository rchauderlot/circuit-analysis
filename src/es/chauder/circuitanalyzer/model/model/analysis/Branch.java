package es.chauder.circuitanalyzer.model.model.analysis;


import es.chauder.circuitanalyzer.model.model.base.Connector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rchauderlot on 1/12/15.
 */
public class Branch {

    public List<Connector> getElements() {
        return elements;
    }

    public void setElements(List<Connector> elements) {
        this.elements = elements;
    }

    List<Connector> elements = new ArrayList<Connector>();


    double current;

}
