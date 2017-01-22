package LeetCode.DivideAndConquer;

import java.util.Arrays;

/**
 * Created by kashishtayal on 1/21/17.
 */
public class KthLargestElementinanArray {
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length-k];
    }
}
