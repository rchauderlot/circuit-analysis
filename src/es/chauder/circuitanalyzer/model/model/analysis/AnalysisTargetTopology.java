package es.chauder.circuitanalyzer.model.model.analysis;

import es.chauder.circuitanalyzer.model.model.base.Terminal;
import es.chauder.circuitanalyzer.model.model.wiring.Instrument;

import java.util.List;

/**
 * Created by rchauderlot on 6/11/16.
 */
public class AnalysisTargetTopology {


    public AnalysisTargetTopology(Instrument instrument, List<Terminal> elements) {
        super();
        this.instrument = instrument;
        this.elements = elements;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public List<Terminal> getElements() {
        return elements;
    }

    private Instrument instrument;
    private List<Terminal> elements;

}
