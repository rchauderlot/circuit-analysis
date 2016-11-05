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

    public void setBranchDirections(List<BranchDirection> branchDirections) {
        this.branchDirections = branchDirections;
    }

    private List<BranchDirection> branchDirections = new ArrayList<BranchDirection>();



}
