package es.chauder.circuitanalyzer.model.service.arithmetic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.chauder.circuitanalyzer.model.model.arithmetic.*;

/**
 * Created by rchauderlot on 07/03/16.
 */
public class ComplexEquationSystemSolver {

    public static ComplexEquationSystemSolution solve(ComplexEquationSystem complexEquationSystem) {

        ComplexEquationSystemSolution solution = null;
        if (complexEquationSystem != null && complexEquationSystem.getEquations().size() >= complexEquationSystem.getVariables().size()) {

            // copy?

            reduceCoeficients(complexEquationSystem);
            if (isSystemSolvable(complexEquationSystem)) {
                solution = generateSolution(complexEquationSystem);
            }

        }
        return  solution;
    }

    private static void reduceCoeficients(ComplexEquationSystem complexEquationSystem) {

        List<ComplexEquationVariable> variables = complexEquationSystem.getVariables();

        for (ComplexEquationVariable variable : variables) {
            ComplexEquation equation = findEquationWithVariable(variable, complexEquationSystem);

            // make the variable unitary
            Complex factor = equation.getFactorForVariable(variable);
            equation.divide(factor);


            // divide remove the variable from other equations
            for (ComplexEquation otherEquation : complexEquationSystem.getEquations()) {
                if (otherEquation != equation) {
                    Complex otherEquationFactor = otherEquation.getFactorForVariable(variable);
                    if (otherEquationFactor != null && !otherEquationFactor.equals(Complex.ZERO)) {
                        ComplexEquation substractionPartialEquation = equation.clone();
                        substractionPartialEquation.multiply(otherEquationFactor);
                        otherEquation.substract(substractionPartialEquation);
                    }
                }
            }
        }
    }


    private static ComplexEquation findEquationWithVariable(ComplexEquationVariable variable, ComplexEquationSystem complexEquationSystem) {
        ComplexEquation equationWithVariable = null;

        for (ComplexEquation eq : complexEquationSystem.getEquations()) {
            if (eq.getVariables().contains(variable)) {
                equationWithVariable = eq;
                break;
            }
        }

        return equationWithVariable;
    }

    private static boolean isSystemSolvable(ComplexEquationSystem complexEquationSystem) {

        int countOfIndependentEquations = 0;
        for (ComplexEquation equation : complexEquationSystem.getEquations()) {
            if (equation != null && equation.getVariables().size() == 1) {
                countOfIndependentEquations ++;
            }
        }

        return countOfIndependentEquations == complexEquationSystem.getVariables().size();
    }

    private static ComplexEquationSystemSolution generateSolution(ComplexEquationSystem complexEquationSystem) {


        Map<ComplexEquationVariable, Complex> solution = new HashMap<>();

        List<ComplexEquationVariable> variables = complexEquationSystem.getVariables();
        for (ComplexEquationVariable variable : variables) {
            ComplexEquation equation = findEquationWithVariable(variable, complexEquationSystem);
            if (equation != null && equation.getVariables().size() == 1) {
                Complex value = equation.getIndependentTerm();
                if (value == null) {
                    value = Complex.ZERO;
                }
                solution.put(variable, value);
            }

        }


        ComplexEquationSystemSolution solutionObject = new ComplexEquationSystemSolution();
        solutionObject.setSolution(solution);
        return solutionObject;
    }


}
