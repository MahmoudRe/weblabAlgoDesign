import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

public class GetOutOfMaze {

    public static String outOfMaze(InputStream in) {
        Scanner sc = new Scanner(in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int start = sc.nextInt();
        int end = sc.nextInt();

        MazeNode[] mazeNodes = new MazeNode[n + 1];

        for(int i = 0; i < mazeNodes.length; i++) {
            mazeNodes[i] =  new MazeNode();
        }

        while(sc.hasNextInt()) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            sc.nextInt();

            mazeNodes[from].outgoingEdges.add(mazeNodes[to]);
        }

        sc.close();

        Queue<MazeNode> q = new LinkedList<>();
        q.add(mazeNodes[start]);

        while(!q.isEmpty()) {
            MazeNode curr = q.poll();

            if(curr == mazeNodes[end]) return "yes";

            q.addAll(curr.outgoingEdges);
        }

        return "no";
    }

    public static String outOfMazeFastest(InputStream in) {
        Scanner sc = new Scanner(in);
        /*
         * We already parse the input for you and should not need to make changes to this part of the code.
         * You are free to change this input format however.
         */
        int n = sc.nextInt();
        int m = sc.nextInt();
        int s = sc.nextInt();
        int t = sc.nextInt();
        ArrayList<HashMap<Integer, Integer>> nodes = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            nodes.add(new HashMap<>());
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int cost = sc.nextInt();
            nodes.get(u).put(v, cost);
        }

        sc.close();

        PriorityQueue<MazeNode2> q = new PriorityQueue<>();
        q.add(new MazeNode2(s, nodes.get(s).size()));

        int[] costs = new int[n+1];     //the index is node number, the value is the cost
        Arrays.fill(costs, Integer.MAX_VALUE);  //at the beginning all node-costs are infinite
        boolean[] visited = new boolean[n+1];
        costs[s] = nodes.get(s).size(); //the cost of the start node is just #outGoingEdges

        while(!q.isEmpty()) {
            MazeNode2 curr = q.poll();
            visited[curr.i] = true;
            if (curr.i == t) break;

            for(int child : nodes.get(curr.i).keySet()) {
                // cost = #outGoingEdges + distance(start -> currNode) + cost (currNode -> childNode)
                int cost = nodes.get(child).size() + costs[curr.i] + nodes.get(curr.i).get(child);

                //in case the child is the endNode, remove #outGoingEdgjes from the cost as this doesn't count.
                if (child == t) costs[child] -= nodes.get(child).size();

                if(cost < costs[child]) {
                    q.add(new MazeNode2(child, cost));
                    costs[child] = cost;
                }
            }
        }

        if(costs[t] == Integer.MAX_VALUE) return "-1";
        return Integer.toString(costs[t]);
    }

    // Helper Classes ----------------------------- //
    private static class MazeNode {
        List<MazeNode> outgoingEdges;
        boolean marked;

        public MazeNode() {
            this.outgoingEdges = new ArrayList<>();
            this.marked = false;
        }
    }

    private static class MazeNode2 implements Comparable<MazeNode2> {
        int i, cost;

        public MazeNode2(int i, int cost) {
            this.i = i;
            this.cost = cost;
        }

        @Override
        public int compareTo(MazeNode2 n2) {
            return this.cost - n2.cost;
        }
    }

    // Main ----------------------
    public static void main(String[] args) throws FileNotFoundException {

        InputStream in = new FileInputStream("src/main/java/getOutOfMaze.txt");

        System.out.println(outOfMaze(in));
        System.out.println("Actual: yes");

        // --------- //

        InputStream in2 = new FileInputStream("src/main/java/getOutOfMazeFastest.txt");

        System.out.println(outOfMazeFastest(in2));
        System.out.println("Actual: 118");
    }
}
