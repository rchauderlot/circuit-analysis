package es.chauder.circuitanalyzer.model.model.analysis;

import java.util.List;
import es.chauder.circuitanalyzer.model.model.base.Circuit;
import es.chauder.circuitanalyzer.model.model.base.Device;
import es.chauder.circuitanalyzer.model.model.base.Wire;
import es.chauder.circuitanalyzer.model.model.wiring.Instrument;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by rchauderlot on 1/12/15.
 */
public class AnalysisTopology {

    public AnalysisTopology(Circuit circuit) {
        super();
        this.circuit = circuit;
    }

    public Circuit getCircuit() {
        return circuit;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

    public List<AnalysisGroup> getAnalysisGroups() {
        return analysisGroups;
    }

    public void setAnalysisGroups(List<AnalysisGroup> analysisGroups) {
        this.analysisGroups = analysisGroups;
    }

    public List<Wire> getUnusedWires() {
        return unusedWires;
    }

    public void setUnusedWires(List<Wire> unusedWires) {
        this.unusedWires = unusedWires;
    }

    public List<Device> getUnusedDevices() {
        return unusedDevices;
    }

    public void setUnusedDevices(List<Device> unusedDevices) {
        this.unusedDevices = unusedDevices;
    }

    public Map<Instrument, AnalysisTargetTopology> getAnalysisTargetTopologyMap() {
        return analysisTargetTopologyMap;
    }

    public void setAnalysisTargetTopologyMap(Map<Instrument, AnalysisTargetTopology> analysisTargetTopologyMap) {
        this.analysisTargetTopologyMap = analysisTargetTopologyMap;
    }

    Circuit circuit;

    List<Branch> branches = new ArrayList<>();
    List<AnalysisGroup> analysisGroups = new ArrayList<AnalysisGroup>();

    List<Wire> unusedWires = new ArrayList<Wire>();
    List<Device> unusedDevices = new ArrayList<Device>();

    Map<Instrument, AnalysisTargetTopology> analysisTargetTopologyMap;

}
