package MultiGraph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

public interface IGraph<TNode extends INode, TEdge extends IEdge> {
   // public boolean isEmpty();
    public Integer nodeCount();
   // public Integer edgeCount();
    public boolean hasEdge(TNode start, TNode end);
    public void addNode(TNode Node);
    public void addEdge(TNode start, TNode end, TEdge edge);
    //public Collection<TNode> getNodes();
    //public Collection<TEdge> getEdges();
    public TNode findNode(Predicate<? super TNode> predicate);
    public ArrayList<TNode> getAdjacentNodes(TNode Node);
}
