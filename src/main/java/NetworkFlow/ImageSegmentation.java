package NetworkFlow;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class ImageSegmentation {
    public static int solve(InputStream in) {
        Graph g = parse(in);
        MaxFlow.maximizeFlow(g);
        int sum = 0;
        for (Edge e : g.getSource().getEdges()) {
            sum += e.getFlow();
        }
        return sum;
    }

    public static Graph parse(InputStream in) {
        Scanner sc = new Scanner(in);
        ArrayList<Node> nodes = new ArrayList<>();
        // no. of nodes
        int n = sc.nextInt();
        // no. of edges
        int m = sc.nextInt();
        // source
        int s = 0;
        // sink
        int t = n + 1;
        Node source = new Node(s);
        Node sink = new Node(t);
        // Create nodes list and add source
        nodes.add(source);
        for (int x = 0; x < n; x++) {
            int id = sc.nextInt();
            int f_i = sc.nextInt();
            int b_i = sc.nextInt();
            Node node = new Node(id);
            nodes.add(node);
            source.addEdge(node, f_i);
            node.addEdge(sink, b_i);
        }
        nodes.add(sink);
        // Create all edges
        for (int x = 0; x < m; x++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            // Initialize capacity with maximum penalty
            nodes.get(from).addEdge(nodes.get(to), 10);
            nodes.get(to).addEdge(nodes.get(from), 10);
        }
        return new Graph(nodes, source, sink);
    }

    // Main ----------------------------
    public static void main(String[] args) throws FileNotFoundException {

        InputStream in = new FileInputStream("src/main/java/NetworkFlow/imageSegmentation.txt");

        System.out.println(solve(in));
    }
}
