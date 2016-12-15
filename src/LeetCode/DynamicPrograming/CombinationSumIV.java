package LeetCode.DynamicPrograming;

/**
 * Created by kashishtayal on 11/5/16.
 */
public class CombinationSumIV {
    /*
        i goes from [0 to target]
        dp[nums[], i] = SUM { forEach int in nums arrar [j] (nums,i-j)}
     */
    public int combinationSum4(int[] nums, int target) {
        int[] matrix = new int[target+1];
        matrix[0] = 1;
        for (int i = 1; i < target+1; i++) {
            for(int j = 0; j < nums.length; j++){
                int partialTarget = i;
                int num = nums[j];
                if(partialTarget >= num){
                    matrix[i]+=matrix[partialTarget-num];
                }
            }
        }
        return matrix[target];
    }

    public static void main(String[] args) {
        CombinationSumIV cs = new CombinationSumIV();
        System.out.println(cs.combinationSum4(new int[]{1,2,3}, 4));
    }
}
