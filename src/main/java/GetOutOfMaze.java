import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

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

    public static void main(String[] args) throws FileNotFoundException {

        InputStream in = new FileInputStream("src/main/java/getOutOfMaze.txt");

        System.out.println(outOfMaze(in));
        System.out.println("Actual: yes");
    }
}
