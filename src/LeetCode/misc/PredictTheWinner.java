package LeetCode.misc;

import java.util.Arrays;

/**
 * Created by tayalka on 3/6/2017.
 */
public class PredictTheWinner {
    int[][] dp;
    public boolean PredictTheWinner(int[] nums) {
        int len = nums.length;
        dp = new int[len][len];
        int totalSum = 0;
        for(int i = 0; i < len; i++ ){
            Arrays.fill(dp[i],-1);
            dp[i][i] = nums[i];
            if(i+1 < len) {
                dp[i][i + 1] = Math.max(nums[i],nums[i+1]);
            }
            totalSum += nums[i];
        }
        return 2*helper(nums,0,len-1) >= totalSum;
    }

    private int helper(int[] nums, int i, int j){
        if(dp[i][j] != -1){
            return dp[i][j];
        }else{
            dp[i][j] = Math.max(nums[i]+ Math.min(helper(nums, i + 2, j), helper(nums, i + 1, j - 1)),
                                nums[j]+ Math.min(helper(nums, i, j - 2), helper(nums, i + 1, j - 1)));
            return dp[i][j];
        }
    }

    public static void main(String[] args) {
        PredictTheWinner predict = new PredictTheWinner();
        System.out.println(predict.PredictTheWinner(new int[]{0, 0, 7, 6, 5, 6, 1}));
    }
}
