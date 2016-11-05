package es.chauder.circuitanalyzer.model.model.analysis;

import es.chauder.circuitanalyzer.model.model.base.Connector;

/**
 * Created by rchauderlot on 5/11/16.
 */
public class BranchDirection {

    private Branch branch;
    private Connector startingConnector;
    private Connector endingConnector;

    public BranchDirection(
            Branch branch,
            Connector startingConnector,
            Connector endingConnector) {
        super();
        this.branch = branch;
        this.startingConnector = startingConnector;
        this.endingConnector = endingConnector;
    }

    public Branch getBranch() {
        return branch;
    }

    public Connector getStartingConnector() {
        return startingConnector;
    }

    public Connector getEndingConnector() {
        return endingConnector;
    }


}
