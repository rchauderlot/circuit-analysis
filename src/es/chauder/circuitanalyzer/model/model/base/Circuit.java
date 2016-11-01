package es.chauder.circuitanalyzer.model.model.base;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rchauderlot on 30/11/15.
 */
public class Circuit {

    @XmlElement
    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    @XmlElement
    public List<Wire> getWires() {
        return wires;
    }

    public void setWires(List<Wire> wires) {
        this.wires = wires;
    }

    @XmlElement
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
