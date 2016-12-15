package LeetCode.DynamicPrograming;

/**
 * Created by kashishtayal on 12/8/16.
 */
public class ClimbingStairs {
    public int climbStairs(int n) {
        if(n == 0) return 0;
        if(n == 1) return 1;
        int[] countHops = new int[n+1];
        countHops[0] = 1;
        countHops[1] = 1;
        for(int i = 2; i < n+1; i++){
            countHops[i] = countHops[i-1] + countHops[i-2];
        }
        return countHops[n];
    }
}
