import jdk.nashorn.internal.runtime.arrays.ArrayIndex;

import java.io.InputStream;
import java.util.*;

class Solution {

    public String outOfTheMaze(InputStream in) {
        Scanner sc = new Scanner(in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int start = sc.nextInt();
        int end = sc.nextInt();

        Node[] nodes = new Node[n + 1];

        for(int i = 0; i < nodes.length; i++) {
            nodes[i] =  new Node();
        }

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

    public String routingTrains(InputStream in) {
        Scanner sc = new Scanner(in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int s = sc.nextInt();
        Node[] nodes = new Node[n+1];

        for(int i = 1; i < nodes.length; i++)
            nodes[i] = new Node();

        while(sc.hasNextInt()) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            sc.nextInt();

            nodes[from].outgoingEdges.add(nodes[to]);
        }

        sc.close();

        Stack<Node> stack = new Stack<>();
        stack.push(nodes[s]);

        while(!stack.isEmpty()) {
            Node node = stack.pop();

            if(node.marked) return "yes";
            node.marked = true;

            stack.addAll(node.outgoingEdges);
        }

        return "no";
    }
}