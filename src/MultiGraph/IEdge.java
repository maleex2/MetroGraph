package MultiGraph;

public interface IEdge {
    public INode getStart();
    public INode getEnd();
    public void connect(INode from, INode to);
    public String getLabel();
}
