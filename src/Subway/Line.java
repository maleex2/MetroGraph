package Subway;

import MultiGraph.IEdge;
import MultiGraph.INode;

public class Line implements IEdge {
    private INode start;
    private INode end;

    public String lineName;
    public Integer startId;
    public Integer endId;
    public Integer weight;

    public Line(String lineName, Integer startId, Integer endId) {
        this.lineName = lineName;
        this.startId = startId;
        this. endId = endId;
    }

    //public Line() {}

    @Override
    public INode getStart() {
        return start;
    }

    @Override
    public INode getEnd() {
        return end;
    }

    @Override
    public void connect(INode from, INode to) {
        start = from;
        end = to;
        start.addEdge(this);
    }

    @Override
    public String getLabel() {
        return this.lineName;
    }
}
