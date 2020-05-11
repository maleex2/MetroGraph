package MultiGraph;

import java.util.Collection;

public interface INode {
    public Collection<IEdge> getEdges();
    public void addEdge(IEdge edge);
    //public void removeEdge(IEdge edge);
    public Integer getId();
}
