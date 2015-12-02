package es.chauder.circuitanalyzer.model.model.analysis;

import java.util.List;
import es.chauder.circuitanalyzer.model.model.base.Circuit;
import es.chauder.circuitanalyzer.model.model.base.Device;

import java.util.ArrayList;

/**
 * Created by rchauderlot on 1/12/15.
 */
public class AnalysisTopology {

    public Circuit getCircuit() {
        return circuit;
    }

    public void setCircuit(Circuit circuit) {
        this.circuit = circuit;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

    public List<AnalysisGroup> getAnalysisGroups() {
        return analysisGroups;
    }

    public void setAnalysisGroups(List<AnalysisGroup> analysisGroups) {
        this.analysisGroups = analysisGroups;
    }

    public List<Branch> getDeadEndedBranches() {
        return deadEndedBranches;
    }

    public void setDeadEndedBranches(List<Branch> deadEndedBranches) {
        this.deadEndedBranches = deadEndedBranches;
    }

    public List<Branch> getIsolatedBranches() {
        return isolatedBranches;
    }

    public void setIsolatedBranches(List<Branch> isolatedBranches) {
        this.isolatedBranches = isolatedBranches;
    }

    Circuit circuit;
    List<Branch> branches = new ArrayList<>();
    List<AnalysisGroup> analysisGroups = new ArrayList<AnalysisGroup>();
    List<Branch> deadEndedBranches = new ArrayList<Branch>();
    List<Branch> isolatedBranches = new ArrayList<Branch>();

}
