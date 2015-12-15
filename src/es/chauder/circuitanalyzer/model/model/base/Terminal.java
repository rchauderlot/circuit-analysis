package es.chauder.circuitanalyzer.model.model.base;

/**
 * Created by rchauderlot on 2/12/15.
 */
public class Terminal extends Connector {

    Device device;
    int terminalIndex;
    Wire wire;

    public Terminal(Device device, int terminalIndex) {
        super();
        this.device = device;
        this.terminalIndex = terminalIndex;
    }

    public Device getDevice() {
        return device;
    }

    public int getTerminalIndex() {
        return terminalIndex;
    }

    public Wire getWire() {
        return wire;
    }

    public void setWire(Wire wire) {
        this.wire = wire;
    }

    public boolean isLinkedTo(Connector connector) {

        return (connector instanceof Terminal && getDevice() != null &&
                getDevice().equals(((Terminal)connector).getDevice()));
    }
}
