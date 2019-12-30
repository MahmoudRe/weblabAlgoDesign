import java.io.*;
import java.util.*;

class Solution {

    private static long schedulingCostLogM(InputStream in) {

        Scanner sc = new Scanner(in);
        int numCases = sc.nextInt();
        int numCourtRooms = sc.nextInt();
        long[] caseTimes = new long[numCases];
        for (int i = 0; i < numCases; i++) {
            sc.next();
            caseTimes[i] = sc.nextLong();
        }
        sc.close();

        PriorityQueue<Long> pq = new PriorityQueue<>();
        for (int i = 0; i < numCourtRooms; i++) {
            pq.add(0l);
        }
        long result = 0;
        for (int i = 0; i < numCases; i++) {
            long earliestCourtRoom = pq.poll();
            pq.add(earliestCourtRoom + caseTimes[i] + 1);
            result = Math.max(result, earliestCourtRoom + caseTimes[i]);
        }
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {

        InputStream in = new FileInputStream("src/main/java/getOutOfMaze.txt");

        System.out.println(schedulingCostLogM(in));
    }
}