package es.chauder.circuitanalyzer.model.model.arithmetic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rchauderlot on 07/03/16.
 */
public class ComplexEquationSystemSolution {


    public Map<ComplexEquationVariable, Complex> getSolution() {
        return solution;
    }

    public void setSolution(Map<ComplexEquationVariable, Complex> solution) {
        this.solution = solution;
    }

    Map<ComplexEquationVariable, Complex> solution = new HashMap();

}
