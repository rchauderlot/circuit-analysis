package es.chauder.circuitanalyzer.model.model.analysis;

import java.util.List;

import java.util.ArrayList;

/**
 * Created by rchauderlot on 30/11/15.
 */
public class Network {

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

    private List<Branch> branches = new ArrayList<Branch>();
}
