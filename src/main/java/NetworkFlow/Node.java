package NetworkFlow;

import java.util.ArrayList;
import java.util.Collection;

class Node {
    protected int id;

    protected Collection<Edge> edges;

    public Node(int id) {
        this.id = id;
        this.edges = new ArrayList<Edge>();
    }

    public void addEdge(Node to, int capacity) {
        Edge e = new Edge(capacity, this, to);
        edges.add(e);
        to.getEdges().add(e.getBackwards());
    }

    public Collection<Edge> getEdges() {
        return edges;
    }

    public int getId() {
        return id;
    }

    public boolean equals(Object other) {
        if (other instanceof Node) {
            Node that = (Node) other;
            if (id == that.getId())
                return edges.equals(that.getEdges());
        }
        return false;
    }
}
