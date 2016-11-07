package es.chauder.circuitanalyzer.model.service;

import java.util.List;
import es.chauder.circuitanalyzer.model.model.base.Circuit;
import es.chauder.circuitanalyzer.model.model.wiring.Instrument;

/**
 * Created by rchauderlot on 30/11/15.
 */
public class CircuitAnalyzer {



    public static FrequencyDomainAnalysis createFrequencyDomainAnalysis(Circuit circuit, List<Instrument> instruments) {

        return new FrequencyDomainAnalysis(circuit, instruments);
    }

}
