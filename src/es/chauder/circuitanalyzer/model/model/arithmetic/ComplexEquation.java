package es.chauder.circuitanalyzer.model.model.arithmetic;

import java.util.*;

/**
 * Equal to zero equation
 *
 * Created by rchauderlot on 07/03/16.
 */
public class ComplexEquation {

    /**
     * Created by rchauderlot on 27/10/16.
     */
    static class ComplexEquationTerm {

        Complex factor;
        ComplexEquationVariable variable;

        public ComplexEquationTerm(Complex factor, ComplexEquationVariable variable) {
            super();
            this.factor = factor;
            this.variable = variable;
        }
    }


//    public Collection<ComplexEquationTerm> getTerms() {
//
//        Collection<ComplexEquationTerm> terms = variableComplexEquationTermMap.values();
//        terms.add(new ComplexEquationTerm(independentTerm, null));
//
//        return terms;
//    }


    public Set<ComplexEquationVariable> getVariables() {
        return variableComplexEquationTermMap.keySet();
    }

    public Complex getFactorForVariable(ComplexEquationVariable variable) {

        Complex factor = null;
        ComplexEquationTerm term = variableComplexEquationTermMap.get(variable);
        if (term != null) {
            factor = term.factor;
        }
        return factor;
    }

    public Complex getIndependentTerm() {
        return independentTerm;
    }



    public void addTerm(Complex factor, ComplexEquationVariable variable) {
        if (variable == null) {
            addIndependentTerm(factor);
        } else {
            addVariableDependentTerm(factor, variable);
        }
    }


    public void multiply(Complex complex) {

        Map<ComplexEquationVariable, ComplexEquationTerm> newVariableComplexEquationTermMap = new HashMap<>();
        for (ComplexEquationTerm term : variableComplexEquationTermMap.values()) {
            ComplexEquationTerm newterm = new ComplexEquationTerm(term.factor.multiply(complex), term.variable);
            newVariableComplexEquationTermMap.put(term.variable, newterm);
        }
        variableComplexEquationTermMap = newVariableComplexEquationTermMap;
        if (independentTerm != null) {
            independentTerm = independentTerm.multiply(complex);
        }
    }

    public void divide(Complex complex) {

        Map<ComplexEquationVariable, ComplexEquationTerm> newVariableComplexEquationTermMap = new HashMap<>();
        for (ComplexEquationTerm term : variableComplexEquationTermMap.values()) {
            ComplexEquationTerm newterm = new ComplexEquationTerm(term.factor.divide(complex), term.variable);
            newVariableComplexEquationTermMap.put(term.variable, newterm);
        }
        variableComplexEquationTermMap = newVariableComplexEquationTermMap;
        if (independentTerm != null) {
            independentTerm = independentTerm.divide(complex);
        }
    }

    public void substract(ComplexEquation equation) {

        substractVariableDependentTermsFromEquation(equation);
        substractIndependentTermFromEquation(equation);

    }

    public ComplexEquation clone() {
        ComplexEquation newEquation = new ComplexEquation();

        newEquation.variableComplexEquationTermMap.putAll(variableComplexEquationTermMap);
        newEquation.independentTerm = independentTerm;

        return newEquation;
    }


    private void substractVariableDependentTermsFromEquation(ComplexEquation equation) {
        Set<ComplexEquationVariable> variables = variableComplexEquationTermMap.keySet();
        for (ComplexEquationVariable otherEquationVariable : equation.variableComplexEquationTermMap.keySet()) {
            if (!variables.contains(otherEquationVariable)) {
                variables.add(otherEquationVariable);
            }
        }
        for (ComplexEquationVariable variable : variables) {
            ComplexEquationTerm otherTerm = equation.variableComplexEquationTermMap.get(variable);
            if (otherTerm != null) {
                substractVariableDependentTerm(otherTerm.factor, variable);
            }
        }
    }


    private void substractIndependentTermFromEquation(ComplexEquation equation) {
        if (equation.independentTerm != null) {
            substractIndependentTerm(equation.independentTerm);
        }
    }


    private void addVariableDependentTerm(Complex factor, ComplexEquationVariable variable) {

        ComplexEquationTerm previousVariableTerm = variableComplexEquationTermMap.get(variable);
        ComplexEquationTerm newTerm;

        if (previousVariableTerm == null) {
            newTerm = new ComplexEquationTerm(factor, variable);
        } else {
            newTerm = new ComplexEquationTerm(previousVariableTerm.factor.add(factor), variable);
        }

        if (newTerm.factor.equals(Complex.ZERO)) {
            if (previousVariableTerm != null) {
                variableComplexEquationTermMap.remove(newTerm.variable);
            }
        } else {
            variableComplexEquationTermMap.put(newTerm.variable, newTerm);
        }
    }

    private void substractVariableDependentTerm(Complex factor, ComplexEquationVariable variable) {

        ComplexEquationTerm previousVariableTerm = variableComplexEquationTermMap.get(variable);
        ComplexEquationTerm newTerm;

        if (previousVariableTerm == null) {
            newTerm = new ComplexEquationTerm(Complex.ZERO.substract(factor), variable);
        } else {
            newTerm = new ComplexEquationTerm(previousVariableTerm.factor.substract(factor), variable);
        }

        if (newTerm.factor.equals(Complex.ZERO)) {
            if (previousVariableTerm != null) {
                variableComplexEquationTermMap.remove(newTerm.variable);
            }
        } else {
            variableComplexEquationTermMap.put(newTerm.variable, newTerm);
        }
    }


    private void addIndependentTerm(Complex factor) {

        Complex newIndependentTerm;
        if (independentTerm == null) {
            newIndependentTerm = factor;
        } else {
            newIndependentTerm = independentTerm.add(factor);
        }

        if (newIndependentTerm == null || newIndependentTerm.equals(Complex.ZERO)) {
            independentTerm = null;
        } else {
            independentTerm = newIndependentTerm;
        }
    }

    private void substractIndependentTerm(Complex factor) {

        Complex newIndependentTerm;
        if (independentTerm == null) {
            newIndependentTerm = Complex.ZERO.substract(factor);
        } else {
            newIndependentTerm = independentTerm.substract(factor);
        }

        if (newIndependentTerm == null || newIndependentTerm.equals(Complex.ZERO)) {
            independentTerm = null;
        } else {
            independentTerm = newIndependentTerm;
        }
    }

    Map<ComplexEquationVariable, ComplexEquationTerm> variableComplexEquationTermMap = new HashMap<>();
    Complex independentTerm = null;

}
