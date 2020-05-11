package MultiGraph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

public class MultiGraphADT<TNode extends INode, TEdge extends IEdge> implements IGraph<TNode, TEdge> {
    private ArrayList<TNode> nodes = new ArrayList<>();
    private ArrayList<TEdge> edges = new ArrayList<>();

   /* public boolean isEmpty() {
        return nodes.isEmpty() && edges.isEmpty();
    }*/

    public Integer nodeCount() {
        return nodes.size();
    }

    public Integer edgeCount() {
        return edges.size();
    }
/*
    public Integer edgeCount() {
        return edges.size();
    }
*/
    public boolean hasEdge(TNode start, TNode end) {
        return edges.stream().anyMatch(x -> x.getStart().equals(start) && x.getEnd().equals(end));
    }

    public void addNode(TNode node) {
        nodes.add(node);
    }

    public void addEdge(TNode start, TNode end, TEdge edge) {
        edge.connect(start, end);
        edges.add(edge);
        start.addEdge(edge);
    }

    public TNode findNode(Predicate<? super TNode> predicate) {
        var optional = nodes.stream().filter(predicate).findFirst();
        if (optional.isPresent()) {
            return optional.get();
        } else {
            return null;
        }
    }

    public TEdge findEdge(TNode start, TNode end) {
        TEdge edge = null;
        for(TEdge e: edges){
            if(e.getStart().getId().equals(start.getId())&&e.getEnd().getId().equals(end.getId()) ||
                    e.getStart().getId().equals(end.getId())&&e.getEnd().getId().equals(start.getId())){
                edge = e;
            }
        }
        return edge;
    }

    @Override
    public ArrayList<TNode> getAdjacentNodes(TNode Node) {
        ArrayList<TNode> adj = new ArrayList<>();
        for(TNode n : nodes){
            if((hasEdge(Node, n) || hasEdge(n, Node) )&& !adj.contains(n))
                adj.add(n);
        }
        return adj;

    }
}