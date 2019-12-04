import jdk.nashorn.internal.runtime.arrays.ArrayIndex;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

class Solution {

    public String outOfTheMaze(InputStream in) {
        Scanner sc = new Scanner(in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int start = sc.nextInt();
        int end = sc.nextInt();

        Node[] nodes = new Node[n + 1];

        for(int i = 0; i < m; i++) {
            if(!sc.hasNextInt()) break;

            int from = sc.nextInt();
            int to = sc.nextInt();
            sc.nextInt();

            if(nodes[from] == null) {
                nodes[from] = new Node();
            }

            if(nodes[to] == null) {
                nodes[to] = new Node();
            }

            nodes[from].outgoingEdges.add(nodes[to]);
        }

        sc.close();

        for(int i = 0; i < nodes.length; i++) {
            nodes[i] = (nodes[i] == null)? new Node(): nodes[i];
        }

        Queue<Node> q = new LinkedList<>();
        q.add(nodes[start]);

        while(!q.isEmpty()) {
            Node curr = q.poll();

            if(curr == nodes[end]) return "yes";

            for(Node child : curr.outgoingEdges) {
                q.add(child);
            }
        }

        return "no";
    }

    public String routingTrains(InputStream in) {
        Scanner sc = new Scanner(in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int s = sc.nextInt();

        Node[] nodes = new Node[n+1];

        return "false";
    }
}