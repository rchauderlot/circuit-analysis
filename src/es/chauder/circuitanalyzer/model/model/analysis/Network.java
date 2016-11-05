package es.chauder.circuitanalyzer.model.model.analysis;

import java.util.List;

import java.util.ArrayList;

/**
 * Created by rchauderlot on 30/11/15.
 */
public class Network {

    public List<BranchDirection> getBranchDirections() {
        return branchDirections;
    }

    public void setBranchDirections(List<BranchDirection> branchDirections) {
        this.branchDirections = branchDirections;
    }

    private List<BranchDirection> branchDirections = new ArrayList<BranchDirection>();
}
