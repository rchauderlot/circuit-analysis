package es.chauder.circuitanalyzer.model.model.arithmetic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rchauderlot on 07/03/16.
 */
public class ComplexEquationSystem {

    List<ComplexEquationVariable> variables = new ArrayList<>();
    List<ComplexEquation> equations = new ArrayList<>();

    public List<ComplexEquationVariable> getVariables() {
        return variables;
    }


    public List<ComplexEquation> getEquations() {
        return equations;
    }

    public void addEquation(ComplexEquation equation) {

        if (equation != null) {
            for (ComplexEquationVariable variable : equation.getVariables()) {
                if (!variables.contains(variable)) {
                    variables.add(variable);
                }
            }
            equations.add(equation);
        }
    }
}
