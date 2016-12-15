package LeetCode.DynamicPrograming;

import java.util.*;

/**
 * Created by kashishtayal on 11/15/16.
 */
public class PerfectSquare {
    public int numSquaresDPN2(int n) {
        int[] dp = new int[n+1];
        dp[1] = 1;
        for(int i = 2; i <= n; i++){
            double sqrt = Math.sqrt((double)i);
            if(sqrt % 1 == 0) {
                dp[i] = 1;
                continue;
            }
            int mid = i/2;
            dp[i] = Integer.MAX_VALUE;
            for(int j = 1; j <= mid; j++){
                dp[i] = Math.min(dp[i],dp[j]+dp[i-j]);
            }
        }
        return dp[n];
    }

    public int numSquareBFS(int n){
        if(n == 0) return 0;
        if(Math.sqrt(n)%1 == 0) return 1;
        int maxNum = (int) Math.sqrt(n);
        Set<Integer> set = new HashSet<>();
        set.add(0);
        int count = 0;
        while(true) {
            count++;
            HashSet<Integer> nextSet = new HashSet<>();
            for (int i = 1; i <= maxNum; i++) {
                int square = i*i;
                for(int j : set){
                    int nextNumber = square+j;
                    if(nextNumber == n) return count;
                    if(nextNumber < n)nextSet.add(nextNumber);
                }
            }
            set = nextSet;
        }
    }

    public static void main(String[] args) {
        PerfectSquare ps = new PerfectSquare();
//        for (int i = 1; i < 14; i++) {
//            System.out.println(i+"  "+ps.numSquares(i));
//            System.out.println(i+"  "+ps.numSquare(i));
//        }
        System.out.println(ps.numSquareBFS(17));
    }
}
