package es.chauder.circuitanalyzer.model.model.base;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rchauderlot on 30/11/15.
 */
public class Circuit {

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public List<Wire> getWires() {
        return wires;
    }

    public void setWires(List<Wire> wires) {
        this.wires = wires;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    List<Device> devices = new ArrayList<>();
    List<Wire> wires = new ArrayList<>();
    String title = "";

}
