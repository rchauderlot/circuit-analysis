package es.chauder.circuitanalyzer.model.model.analysis;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rchauderlot on 2/12/15.
 */
public class Node {

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

    private List<Branch> branches = new ArrayList<Branch>();



}
