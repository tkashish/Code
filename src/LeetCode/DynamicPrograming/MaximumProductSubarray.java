package LeetCode.DynamicPrograming;

/**
 * Created by kashishtayal on 11/18/16.
 */
public class MaximumProductSubarray {
    public int maxProduct(int[] nums) {
        int len = nums.length;
        if(len == 0)return 0;
        int posPrev = 0;
        int negPrev = 0;
        int max = 0;
        if(nums[0] < 0){
            negPrev = nums[0];
            max = negPrev;
        }else{
            posPrev = nums[0];
            max = posPrev;
        }
        for(int i = 1; i < len; i++){
            int posCurr = 1;
            int negCurr = -1;
            if(nums[i] > 0){
                posCurr = Math.max(nums[i]*posPrev,nums[i]);
                negCurr = nums[i]*negPrev;
            }else if (nums[i] <  0){
                posCurr = nums[i]*negPrev;
                negCurr = Math.min(nums[i]*posPrev, nums[i]);
            }else{
                posCurr = 0;
                negCurr = 0;
            }
            posPrev = posCurr;
            negPrev = negCurr;
            max = Math.max(max, posPrev);
        }
        return max;
    }

    public static void main(String[] args) {
        MaximumProductSubarray mps = new MaximumProductSubarray();
        System.out.println(mps.maxProduct(new int[]{-2,3,4,6,0,-1}));
        System.out.println(mps.maxProduct(new int[]{-2,3,4,6,-1}));
        System.out.println(mps.maxProduct(new int[]{-2,-3,4,6,-1}));
        System.out.println(mps.maxProduct(new int[]{2,3,-2,4}));
        System.out.println(mps.maxProduct(new int[]{-2,0,-2}));
        System.out.println(mps.maxProduct(new int[]{-2,0,-2,4}));
        System.out.println(mps.maxProduct(new int[]{2,-5,-2,-4,3}));
        System.out.println(mps.maxProduct(new int[]{2}));
        System.out.println(mps.maxProduct(new int[]{-2}));
        System.out.println(mps.maxProduct(new int[]{}));
    }
}
