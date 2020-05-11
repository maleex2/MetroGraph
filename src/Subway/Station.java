package Subway;

import MultiGraph.IEdge;
import MultiGraph.INode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Station implements INode {
    private ArrayList<IEdge> edges = new ArrayList<>();

    public Integer stationId;
    public String stationName;

    public Station(Integer stationId, String stationName) {
        this.stationId = stationId;
        this.stationName = stationName;
    }

    @Override
    public Collection<IEdge> getEdges() {
        return edges;
    }

    @Override
    public void addEdge(IEdge edge) {
        edges.add(edge);
    }
/*
    @Override
    public void removeEdge(IEdge edge) {
        edges.remove(edge);
    }
*/
    @Override
    public Integer getId() {
        return stationId;
    }
}
