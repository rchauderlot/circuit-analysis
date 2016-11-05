package es.chauder.circuitanalyzer.model.model.analysis;

import es.chauder.circuitanalyzer.model.model.base.Connector;

import java.util.List;

import java.util.ArrayList;

/**
 * Created by rchauderlot on 30/11/15.
 */
public class Network {

    public List<BranchDirection> getBranchDirections() {
        return branchDirections;
    }

    public void addBranchDirection(BranchDirection branchDirection) {
        branchDirections.add(branchDirection);
    }

    private List<BranchDirection> branchDirections = new ArrayList<>();

}
