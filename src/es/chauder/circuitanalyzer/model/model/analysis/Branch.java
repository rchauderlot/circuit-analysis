package es.chauder.circuitanalyzer.model.model.analysis;


import es.chauder.circuitanalyzer.model.model.base.Connector;
import es.chauder.circuitanalyzer.model.model.base.Device;
import es.chauder.circuitanalyzer.model.model.base.Terminal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rchauderlot on 1/12/15.
 */
public class Branch {

    public List<Connector> getElements() {
        return elements;
    }

    public void setElements(List<Connector> elements) {
        this.elements = elements;
        updateDeviceMap(elements);
    }

    private void updateDeviceMap(List<Connector> connectors) {
        if (connectors != null) {
            for (Connector connector : connectors) {
                if (connector instanceof Terminal) {
                    Terminal t = (Terminal) connector;
                    if (deviceTerminalMap.get(t.getDevice()) == null) {
                        deviceTerminalMap.put(t.getDevice(), t);
                    }

                }
            }
        } else {
            deviceTerminalMap.clear();
        }
    }

    /**
     * It determines the device orientation in the branch
     * @return
     */
    public Terminal getFirstDeviceTerminal(Device device) {
        return deviceTerminalMap.get(device);
    }

    public Connector getFirstElement() {
        Connector connector = null;
        if (elements != null && elements.size() > 0) {
            connector = elements.get(0);
        }
        return connector;

    }

    public Connector getLastElement() {
        Connector connector = null;
        if (elements != null && elements.size() > 0) {
            connector = elements.get(elements.size() - 1);
        }
        return connector;

    }


    public boolean isConnectedTo(Branch other) {

        Connector branch1Start = getFirstElement();
        Connector branch1End = getLastElement();
        Connector branch2Start = other.getFirstElement();
        Connector branch2End = other.getLastElement();


        return (branch1Start.isLinkedTo(branch2Start) ||
                branch1Start.isLinkedTo(branch2End) ||
                branch1End.isLinkedTo(branch2Start) ||
                branch1End.isLinkedTo(branch2End));

    }


    public Node getStart() {
        return start;
    }

    public void setStart(Node start) {
        this.start = start;
    }

    public Node getEnd() {
        return end;
    }

    public void setEnd(Node end) {
        this.end = end;
    }

    public Node getOppositeBoundaryNode(Node node) {

        Node oppositeNode = null;
        if (node == getStart()) {
            oppositeNode = getEnd();
        } else if (node == getEnd()) {
            oppositeNode = getStart();
        }
        return oppositeNode;

    }


    public Connector getOppositeBoundaryElement(Connector element) {

        Connector oppositeElement = null;
        if (element == getFirstElement()) {
            oppositeElement = getLastElement();
        } else if (element == getLastElement()) {
            oppositeElement = getFirstElement();
        }
        return oppositeElement;

    }

    public Connector getFirstElementInTheBranchStartingFromNode(Node node) {

        Connector oppositeElement = null;
        if (node == getStart()) {
            oppositeElement = getFirstElement();
        } else if (node == getEnd()) {
            oppositeElement = getLastElement();
        }
        return oppositeElement;

    }


    Node start;
    Node end;
    List<Connector> elements = new ArrayList<Connector>();
    Map<Device, Terminal> deviceTerminalMap = new HashMap<Device, Terminal>();

}
