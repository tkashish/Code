package DynamicPrograming;

import java.util.Arrays;

/**
 * Created by tayalka on 11/3/2016.
 */
public class CombinationSumIV {
    public int combinationSum4(int[] nums, int target) {
        int len = nums.length;
        int[][] dp = new int[len+1][target+1];
        for(int i = 1; i < len+1; i++){
            dp[i][0] = 1;
        }
        for(int i = 0; i < target+1; i++){
            dp[0][i] = 0;
        }
        for (int i = 1; i < len+1; i++) {
            for (int j = 1; j < target+1; j++) {
                dp[i][j] = dp[i-1][j];
                if(j >= nums[i-1]){
                    dp[i][j] += 2*dp[i-1][j-nums[i-1]]+1;
                }
            }
        }
        System.out.println(Arrays.deepToString(dp));
        return dp[len][target];
    }

    public static void main(String[] args) {
//        CombinationSumIV cs = new CombinationSumIV();
//        System.out.println(cs.combinationSum4(new int[]{1,2}, 4));
        Arrays.asList(null);
    }
}
