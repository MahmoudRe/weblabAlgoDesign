import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

public class PlanningBikeRepairs {

    public static String planningBikeRepairs(InputStream in) {
        Scanner sc = new Scanner(in);
        int n = sc.nextInt();

        //case if there is no jobs!
        if( n == 0 ) {
            sc.close();
            return "0";
        }

        List<Job> jobs = new ArrayList<>();
        while(sc.hasNextInt()) {
            int s = sc.nextInt();
            int d = sc.nextInt();

            jobs.add(new Job(s, d));
        }
        sc.close();

        //sort jobs in asc order (earliest first)
        Collections.sort(jobs);

        //define workers queue, we use priority queue so we can get directly
        //the worker who is going to finish the first and then we can assign another task to him
        PriorityQueue<Integer> pqWorkers = new PriorityQueue<>();
        pqWorkers.add(0);

        for(int i = 0; i < n; i++) {

            //check if the end time of any workers is less than the start time of the this i-th job
            if( pqWorkers.peek() != null && pqWorkers.peek() <= jobs.get(i).s)
                pqWorkers.poll();

            //if all of them are busy at any iteration, we are gonna introduce new worker by skip poll()
            pqWorkers.add(jobs.get(i).end());
        }

        return Integer.toString(pqWorkers.size());
    }

    public static void main(String[] args) throws FileNotFoundException {
        InputStream in = new FileInputStream("src/main/java/planningBikeRepairs.txt");

        System.out.println(planningBikeRepairs(in));
        System.out.println("Actual: 5");
    }


    //Helper Classes ----------------------------------
    private static class Job implements Comparable<Job> {

        int s;  //start
        int d; //duration

        Job(int s, int d) {
            this.s = s;
            this.d = d;
        }

        int end() {
            return s + d;
        }

        @Override
        public int compareTo(Job job) {
            return this.s - job.s;
        }
    }
}
