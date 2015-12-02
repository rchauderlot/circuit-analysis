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

    List<Branch> branches = new ArrayList<>();
    List<Network> networks = new ArrayList<>();
    List<Node> nodes = new ArrayList<>();

}
