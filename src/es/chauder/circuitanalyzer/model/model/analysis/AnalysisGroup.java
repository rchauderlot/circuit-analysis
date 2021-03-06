package es.chauder.circuitanalyzer.model.model.analysis;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rchauderlot on 2/12/15.
 */
public class AnalysisGroup {

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

    public List<Network> getNetworks() {
        return networks;
    }

    public void setNetworks(List<Network> networks) {
        this.networks = networks;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Branch> getOpenBranches() {
        return openBranches;
    }

    public void setOpenBranches(List<Branch> openBranches) {
        this.openBranches = openBranches;
    }

    public List<Branch> getClosedBranches() {
        return closedBranches;
    }

    public void setClosedBranches(List<Branch> closedBranches) {
        this.closedBranches = closedBranches;
    }


    List<Branch> branches = new ArrayList<>();
    List<Network> networks = new ArrayList<>();
    List<Node> nodes = new ArrayList<>();

    List<Branch> closedBranches = new ArrayList<Branch>();
    List<Branch> openBranches = new ArrayList<Branch>();
}
