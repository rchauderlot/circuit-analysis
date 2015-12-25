package es.chauder.circuitanalyzer.model.model.analysis;

import es.chauder.circuitanalyzer.model.model.base.Connector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rchauderlot on 2/12/15.
 */
public class Node {

    public static class BranchEnd {
        public Branch branch;
        public Connector connector;
    }


    public List<BranchEnd> getBranchEnds() {
        return branchEnds;
    }

    public void setBranchEnds(List<BranchEnd> branchEnds) {
        this.branchEnds = branchEnds;
    }

    private List<BranchEnd> branchEnds = new ArrayList<BranchEnd>();



}
