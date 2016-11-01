package es.chauder.circuitanalyzer.model.model.analysis;

import es.chauder.circuitanalyzer.model.model.arithmetic.ComplexEquationSystem;

/**
 * Created by rchauderlot on 27/10/16.
 */
public class FrequencyAnalysisGroupComputation {


    final private AnalysisGroup analysisGroup;

    private BranchComplexVariableAssignmentMap branchComplexVariableAssignmentMap = new BranchComplexVariableAssignmentMap();
    private ComplexEquationSystem equationSystem = new ComplexEquationSystem();


    public FrequencyAnalysisGroupComputation(AnalysisGroup analysisGroup) {
        super();
        this.analysisGroup = analysisGroup;
    }

    public ComplexEquationSystem getEquationSystem() {
        return equationSystem;
    }

    public BranchComplexVariableAssignmentMap getBranchComplexVariableAssignmentMap() {
        return branchComplexVariableAssignmentMap;
    }


    public AnalysisGroup getAnalysisGroup() {
        return analysisGroup;
    }



}
