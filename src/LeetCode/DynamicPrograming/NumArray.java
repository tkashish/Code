package LeetCode.DynamicPrograming;

/**
 * Created by kashishtayal on 11/10/16.
 */
public class NumArray {
    int[] _sum;
    public NumArray(int[] nums) {
        _sum = new int[nums.length+1];
        _sum[0] = 0;
        for(int i = 1; i < _sum.length; i++ ){
            _sum[i] = nums[i-1]+_sum[i-1];
        }
    }

    public int sumRange(int i, int j) {
        return _sum[j+1]-_sum[i];
    }

    public static void main(String[] args) {
        NumArray numArray = new NumArray(new int[]{-2, 0, 3, -5, 2, -1});
        System.out.println(numArray.sumRange(0, 2));
        System.out.println(numArray.sumRange(2, 5));
        System.out.println(numArray.sumRange(0, 5));
    }
}