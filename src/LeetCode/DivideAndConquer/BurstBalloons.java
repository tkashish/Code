package LeetCode.DivideAndConquer;

/**
 * Created by kashishtayal on 1/17/17.
 */
public class BurstBalloons {
    public int maxCoins(int[] nums) {
        if(nums.length == 1) return nums[0];
        int max = 0;
        for(int i = 0; i < nums.length; i++){
            int value = nums[i];
            if(i != 0 && i != nums.length-1){
                value *= nums[i-1]*nums[i+1];
            }else if(i == 0){
                if(i != nums.length-1) {
                    value *= nums[i + 1];
                }
            }else{
                if(i != 0) {
                    value *= nums[i - 1];
                }
            }
            int[] modifiedArray = removeAt(nums,i);
            value += maxCoins(modifiedArray);
            max = Math.max(max,value);
        }
        return max;
    }
    private int[] removeAt(int[] nums, int index){
        int[] result = new int[nums.length-1];
        if(index == 0){
            System.arraycopy(nums, 1, result, 0, nums.length-1);
        }else if(index == nums.length-1){
            System.arraycopy(nums, 0, result, 0, nums.length-1);
        }else {
            System.arraycopy(nums, 0, result, 0, index);
            System.arraycopy(nums, index + 1, result, index, nums.length - index -1);
        }
        return result;
    }

    public int maxCoinsI(int[] nums){
        int[] extendedNums = new int[nums.length+2];
        System.arraycopy(nums,0,extendedNums,1,nums.length);

        extendedNums[0] = 1;
        extendedNums[extendedNums.length-1] = 1;

        int[][] dp = new int[extendedNums.length][extendedNums.length];
        for(int subArrayLen = 1; subArrayLen < nums.length+1; subArrayLen++){
            for (int start = 0; start < extendedNums.length-subArrayLen-1; start++){
                int end = start + subArrayLen + 1;
                for(int i = start+1; i < end; i++){
                    dp[start][end] = Math.max(dp[start][end],
                            extendedNums[start]*extendedNums[i]*extendedNums[end] + dp[start][i] + dp[i][end]);
                }
            }
        }
        return dp[0][extendedNums.length-1];
    }
    public static void main(String[] args) {
        BurstBalloons bb = new BurstBalloons();
        System.out.println(bb.maxCoins(new int[]{3,1,5,8}));
        System.out.println(bb.maxCoinsI(new int[]{3,1,5,8}));
    }
}
