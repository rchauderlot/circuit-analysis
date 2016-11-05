package es.chauder.circuitanalyzer.model.model.analysis;

import es.chauder.circuitanalyzer.model.model.arithmetic.ComplexEquationVariable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rchauderlot on 27/10/16.
 */
public class BranchComplexVariableAssignmentMap {

    private Map<ComplexEquationVariable, BranchDirection> branchMap = new HashMap<>();
    private Map<BranchDirection, ComplexEquationVariable> complexEquationVariableMap = new HashMap<>();
    private Map<Branch, ComplexEquationVariable> branchEndMap = new HashMap<>();

    public ComplexEquationVariable getVariableForBranchEnd(BranchDirection branch) {

        return complexEquationVariableMap.get(branch);
    }

    public BranchDirection getBranchEndForVariable(ComplexEquationVariable variable) {

        return branchMap.get(variable);
    }

    public ComplexEquationVariable getVariableForBranch(Branch branch) {

        return branchEndMap.get(branch);
    }

    public void addVariableAndBranchEnd(ComplexEquationVariable variable, BranchDirection branchDirection) {
        branchMap.put(variable, branchDirection);
        complexEquationVariableMap.put(branchDirection, variable);
        branchEndMap.put(branchDirection.getBranch(), variable);
    }

    public int getVariableCount() {
        return branchMap.size();
    }

}
