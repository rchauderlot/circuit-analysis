package es.chauder.circuitanalyzer.model.service;

import es.chauder.circuitanalyzer.model.model.base.Circuit;

/**
 * Created by rchauderlot on 30/11/15.
 */
public class CircuitAnalyzer {



    public static FrequencyDomainAnalysis createFrequencyDomainAnalysis(Circuit circuit) {

        return new FrequencyDomainAnalysis(circuit);
    }

}
