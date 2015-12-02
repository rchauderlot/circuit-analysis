package es.chauder.circuitanalyzer.model.model.base;

import com.sun.tools.javac.util.List;

/**
 * Created by rchauderlot on 30/11/15.
 */
public class Wire extends Connector {

    private List<Device> devices;

    public List<Device> getDevices() {
        return devices;
    }

}
