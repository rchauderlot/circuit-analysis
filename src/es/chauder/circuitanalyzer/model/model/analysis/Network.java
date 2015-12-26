package es.chauder.circuitanalyzer.model.model.analysis;

import java.util.List;

import java.util.ArrayList;

/**
 * Created by rchauderlot on 30/11/15.
 */
public class Network {

    public List<Node.BranchEnd> getBranchEnds() {
        return branchEnds;
    }

    public void setBranchEnds(List<Node.BranchEnd> branchEnds) {
        this.branchEnds = branchEnds;
    }

    private List<Node.BranchEnd> branchEnds = new ArrayList<Node.BranchEnd>();
}
