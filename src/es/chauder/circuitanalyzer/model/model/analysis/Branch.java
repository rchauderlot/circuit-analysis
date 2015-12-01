package es.chauder.circuitanalyzer.model.model.analysis;


import es.chauder.circuitanalyzer.model.model.ElectronicElement;

import java.util.List;

/**
 * Created by rchauderlot on 1/12/15.
 */
public class Branch {

    public List<ElectronicElement> getElements() {
        return elements;
    }

    public void setElements(List<ElectronicElement> elements) {
        this.elements = elements;
    }

    List<ElectronicElement> elements;
    double current;

}
