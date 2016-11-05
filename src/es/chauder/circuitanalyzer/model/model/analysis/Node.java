package es.chauder.circuitanalyzer.model.model.analysis;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rchauderlot on 2/12/15.
 */
public class Node {


    public List<BranchDirection> getBranchDirections() {
        return branchDirections;
    }

    public void addBranchDirection(BranchDirection branchDirection) {
        branchDirections.add(branchDirection);
    }

    private List<BranchDirection> branchDirections = new ArrayList<BranchDirection>();



}
