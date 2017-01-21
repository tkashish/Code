package LeetCode.DivideAndConquer;

import java.util.Arrays;

/**
 * Created by kashishtayal on 1/20/17.
 */
public class MajorityElement {
    public static int majorityElement(int[] nums) {
        Arrays.sort(nums);
        int counter = 1;
        int num = nums[0];
        for(int i = 1; i < nums.length; i++){
            if(nums[i] == num){
                counter++;
                if(counter > nums.length/2) return num;
            }else{
                num = nums[i];
                counter = 1;
            }
        }
        return num;
    }

    public static void main(String[] args) {
        System.out.println(MajorityElement.majorityElement(new int[]{2,2,2,3,3,3,3,4}));
    }
}
