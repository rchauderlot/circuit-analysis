package es.chauder.circuitanalyzer.model.model.analysis;

import es.chauder.circuitanalyzer.model.model.arithmetic.ComplexEquationVariable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rchauderlot on 27/10/16.
 */
public class BranchComplexVariableAssignmentMap {

    private Map<ComplexEquationVariable, Node.BranchEnd> branchMap = new HashMap<>();
    private Map<Node.BranchEnd, ComplexEquationVariable> complexEquationVariableMap = new HashMap<>();
    private Map<Branch, ComplexEquationVariable> branchEndMap = new HashMap<>();

    public ComplexEquationVariable getVariableForBranchEnd(Node.BranchEnd branch) {

        return complexEquationVariableMap.get(branch);
    }

    public Node.BranchEnd getBranchEndForVariable(ComplexEquationVariable variable) {

        return branchMap.get(variable);
    }

    public ComplexEquationVariable getVariableForBranch(Branch branch) {

        return branchEndMap.get(branch);
    }

    public void addVariableAndBranchEnd(ComplexEquationVariable variable, Node.BranchEnd branchEnd) {
        branchMap.put(variable, branchEnd);
        complexEquationVariableMap.put(branchEnd, variable);
        branchEndMap.put(branchEnd.branch, variable);
    }

    public int getVariableCount() {
        return branchMap.size();
    }

}
