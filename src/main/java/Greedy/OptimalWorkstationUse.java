package Greedy;

import java.util.*;

class OptimalWorkstationUse {


    public static /**
     * @param n number of researchers
     * @param m number of processes
     * @param start start times of jobs 1 through n. NB: you should ignore start[0]
     * @param end end times of jobs 1 through n. NB: you should ignore start[0]
     * @return the number of unlocks that can be saved.
     */
    int optimalWorkstationUseAnswer(int n, int m, int[] start, int[] end) {
        ArrayList<Session> sessionList = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            sessionList.add(new Session(start[i], end[i]));
        }
        Collections.sort(sessionList);
        int unlocksSaved = 0;
        PriorityQueue<Machine> machineQueue = new PriorityQueue<>();
        for (Session s : sessionList) {
            while (!machineQueue.isEmpty()) {
                if (machineQueue.peek().available > s.start) {
                    break;
                }
                Machine firstMachine = machineQueue.poll();
                // and an unlock is saved
                if (firstMachine.locked >= s.start) {
                    unlocksSaved++;
                    break;
                }
            }
            machineQueue.add(new Machine(s.start + s.duration, s.start + s.duration + m));
        }
        return unlocksSaved;
    }

    private static  class Session implements Comparable<Session> {

        public int start;

        public int duration;

        public Session(int arrives, int duration) {
            this.start = arrives;
            this.duration = duration;
        }

        @Override
        public int compareTo(Session otherJob) {
            return Integer.compare(start, otherJob.start);
        }
    }

    private static class Machine implements Comparable<Machine> {

        public int available;

        public int locked;

        public Machine(int available, int locked) {
            this.available = available;
            this.locked = locked;
        }

        @Override
        public int compareTo(Machine otherMachine) {
            return Integer.compare(locked, otherMachine.locked);
        }
    }


    public static /**
     * @param n number of researchers
     * @param m number of processes
     * @param start start times of jobs 1 through n. NB: you should ignore start[0]
     * @param duration duration of jobs 1 through n. NB: you should ignore duration[0]
     * @return the number of unlocks that can be saved.
     */
    int optimalWorkstationUse(int n, int m, int[] start, int[] duration) {
        if(n <= 0) return 0;

        //define list of jobs
        Job[] jobs = new Job[n];
        PriorityQueue<Integer> endTimesPQ = new PriorityQueue<>();

        //add jobs in array
        for(int i = 0; i < n; i++)
            jobs[i] = new Job(start[i+1], duration[i+1]);

        //sort ASCending on end time
        Arrays.sort(jobs);

        //add the first end time in PQ
        endTimesPQ.add(jobs[0].end);

        //start from 2nd end time
        for(int i = 1; i < n; i++) {
            Job j = jobs[i];


            List<Integer> rejectedTimes = new ArrayList<>();

            while(endTimesPQ.size() != 0) {

                int nextEarliestEndTime = endTimesPQ.poll();

                //check if there is no overlapping
                //check if the interval between the end of first and start of this job less than the automatic lock time
                if(j.s >= nextEarliestEndTime
                        && (j.s - nextEarliestEndTime <= m || nextEarliestEndTime == Integer.MIN_VALUE)) {

                    endTimesPQ.add(j.end);
                    break;
                } else {
                    rejectedTimes.add(nextEarliestEndTime);

                    if(endTimesPQ.size() == 0)
                        endTimesPQ.add(Integer.MIN_VALUE);
                }
            }

            endTimesPQ.addAll(rejectedTimes);
        }

        return n - endTimesPQ.size();
    }

    public static void main(String[] arg) {
        int n = 0;
        int m = 10;
        int[] start =   { 0};
        int[] end =     { 0};
        System.out.println(optimalWorkstationUse(n, m, start, end));
        System.out.println(optimalWorkstationUseAnswer(n, m, start, end));
    }


    //Help ---------------------------
    private static class Job implements Comparable<Job> {
        int s;  //start
        int d;  //duration
        int end; //finish time

        Job(int s, int d) {
            this.s = s;
            this.d = d;
            this.end = s+d;
        }

        @Override
        public int compareTo(Job that) {
            return this.s - that.s;  //ASC
        }
    }
}


