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
    private Map<Branch, ComplexEquationVariable> branchDirectionMap = new HashMap<>();

    public ComplexEquationVariable getVariableForBranchDirection(BranchDirection branch) {

        return complexEquationVariableMap.get(branch);
    }

    public BranchDirection getBranchDirectionForVariable(ComplexEquationVariable variable) {

        return branchMap.get(variable);
    }

    public ComplexEquationVariable getVariableForBranch(Branch branch) {

        return branchDirectionMap.get(branch);
    }

    public void addVariableAndBranchDirection(ComplexEquationVariable variable, BranchDirection branchDirection) {
        branchMap.put(variable, branchDirection);
        complexEquationVariableMap.put(branchDirection, variable);
        branchDirectionMap.put(branchDirection.getBranch(), variable);
    }

    public int getVariableCount() {
        return branchMap.size();
    }

}
