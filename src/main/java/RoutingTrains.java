import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

public class RoutingTrains {

    // Helper Classes ---------------
    private static class Node {
        List<Node> outgoingEdges;
        boolean marked;

        public Node() {
            this.outgoingEdges = new ArrayList<>();
            this.marked = false;
        }
    }

    public static String routingTrains(InputStream in) {
        Scanner sc = new Scanner(in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int start = sc.nextInt();
        int end = sc.nextInt();

        Node[] nodes = new Node[n + 1];

        for(int i = 0; i < nodes.length; i++)
            nodes[i] = new Node();

        while(sc.hasNextInt()) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            sc.nextInt();

            nodes[from].outgoingEdges.add(nodes[to]);
        }

        sc.close();

        Queue<Node> q = new LinkedList<>();
        q.add(nodes[start]);

        while(!q.isEmpty()) {
            Node curr = q.poll();

            if(curr == nodes[end]) return "yes";

            q.addAll(curr.outgoingEdges);
        }

        return "no";
    }

    public static void main(String[] args) throws FileNotFoundException {
        InputStream in = new FileInputStream("src/main/java/routingTrains.txt");

        System.out.println(routingTrains(in));
        System.out.println("Actual: yes");
    }
}
