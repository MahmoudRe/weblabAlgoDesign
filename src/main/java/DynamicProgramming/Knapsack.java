package DynamicProgramming;

import java.io.*;
import java.util.*;

public class Knapsack {

    /**
     * Given a knapsack of weight 𝑊, and 𝑛 items each with a weight and value,
     * find a subset of items in the knapsack such that the value of
     * the combined items is maximized.
     * @param in input stream
     * @return the maximum possible value for that instance.
     */
    public static String solve(InputStream in) {
        Scanner sc = new Scanner(in);

        int W = sc.nextInt();
        int n = sc.nextInt();

        Item[] items = new Item[n+1];
        for(int i = 1; i <= n; i++)
            items[i] = new Item(sc.nextInt(), sc.nextInt());

        sc.close();

        int[][] mem  = new int[n+1][W+1];

        for(int i = 1; i <= n; i++)
            for(int w = 0; w <= W; w++)
                if( items[i].w > w )
                    mem[i][w] = mem[i-1][w];
                else
                    mem[i][w] = Integer.max(mem[i-1][w], items[i].v + mem[i-1][w-items[i].w]);

        return mem[n][W] + "";
    }

    // Helper Class ---------------------
    static class Item {
        int w;
        int v;

        public Item(int w, int v) {
            this.w = w;
            this.v = v;
        }
    }

    // Main ----------------------------
    public static void main(String[] args) throws FileNotFoundException {

        InputStream in = new FileInputStream("src/main/java/DynamicProgramming/knapsack.txt");

        System.out.println(solve(in));
    }
}


