package Greedy;

public class PackingTrucks {

     /**
     * @param n the number of packages
     * @param weights the weights of all packages 1 through n. Note that weights[0] should be ignored!
     * @param maxWeight the maximum weight a truck can carry
     * @return the minimal number of trucks required to ship the packages _in the given order_.
     */
     public static int minAmountOfTrucks(int n, int[] weights, int maxWeight) {
        if(n == 0) return 0;

        int c = 0;
        int totalWeight = weights[1];
        for(int i = 2; i <= n; i++) {

            if(totalWeight + weights[i] > maxWeight) {
                c++;
                totalWeight = weights[i];
            } else {
                totalWeight += weights[i];
            }
        }

        return ++c; //because it will skip one truck in the last iteration of for-loop
    }
}
