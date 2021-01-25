package NetworkFlow;

import java.util.List;

class Graph {
    private List<Node> nodes;

    private Node source;

    private Node sink;

    public Graph(List<Node> nodes, Node source, Node sink) {
        this.nodes = nodes;
        this.source = source;
        this.sink = sink;
    }

    public Node getSink() {
        return sink;
    }

    public Node getSource() {
        return source;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public boolean equals(Object other) {
        if (other instanceof Graph) {
            Graph that = (Graph) other;
            return this.nodes.equals(that.nodes);
        }
        return false;
    }

    public void maximizeFlow() {
        MaxFlow.maximizeFlow(this);
    }
}
