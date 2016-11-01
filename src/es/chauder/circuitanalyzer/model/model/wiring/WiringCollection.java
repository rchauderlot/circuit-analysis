package es.chauder.circuitanalyzer.model.model.wiring;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rchauderlot on 14/2/16.
 */
@XmlRootElement(name = "Emp")
public class WiringCollection {

    @XmlElement(name = "wirings")
    public List<Wiring> getWirings() {
        return wirings;
    }

    public void setWirings(List<Wiring> wirings) {
        this.wirings = wirings;
    }

    List<Wiring> wirings = new ArrayList<>();

}
