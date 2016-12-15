package LeetCode.DynamicPrograming;

/**
 * Created by kashishtayal on 11/7/16.
 */

import java.util.Stack;

/**
 * embarassing code
 * bad fucking code
 */
public class WiggleSubsequence {
    int[][][] matrix;
    public int wiggleMaxLength(int[] nums) {
        matrix = new int[nums.length][nums.length][3];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                for (int k = 0; k < 3; k++){
                    matrix[i][j][k] = -1;
                }
            }
        }
        return helper(0,0,nums,0);
    }
    public int helper(int lastIndex, int index, int[] nums, int diff){
        if(index >= nums.length){
            return 0;
        }
        if(matrix[lastIndex][index][diff] >=0){
            return matrix[lastIndex][index][diff];
        }
        int val = 0;
        if(diff == 2 && nums[index] < nums[lastIndex]){
            val = Math.max(1+helper(index,index+1,nums,1), helper(lastIndex, index+1,nums,diff));
        }else if(diff == 1 && nums[index] > nums[lastIndex]){
            val = Math.max(1+helper(index,index+1,nums,2), helper(lastIndex, index+1,nums,diff));
        }else if(diff == 0){
            int neg = 1+helper(lastIndex, index+1,nums,2);
            int pos = 1+helper(lastIndex, index+1,nums, 1);
            int nut = helper(lastIndex, index+1,nums, 0);
            val = Math.max(neg,Math.max(pos,nut));
        }
        matrix[lastIndex][index][diff] = val;
        return val;
    }

    public static void main(String[] args) {
        WiggleSubsequence ws = new WiggleSubsequence();
        System.out.println(ws.wiggleMaxLengthI(new int[]{1,2,3,4}));
        System.out.println(ws.wiggleMaxLengthI(new int[]{1,9,2,8,3,7}));
        System.out.println(ws.wiggleMaxLengthI(new int[]{1}));
        System.out.println(ws.wiggleMaxLengthI(new int[]{1,1}));
        System.out.println(ws.wiggleMaxLengthI(new int[]{}));
        System.out.println(ws.wiggleMaxLengthI(new int[]{1,7,4,9,2,5}));
        System.out.println(ws.wiggleMaxLengthI(new int[]{1,17,5,10,13,15,10,5,16,8}));
        System.out.println(ws.wiggleMaxLengthI(new int[]{1,2,3,4,5,6,7,8,9}));
    }

    /*
        better code
     */
    public int wiggleMaxLengthI(int[] nums) {
        if(nums.length == 0) return 0;
        Stack<Integer> result = new Stack<>();
        result.push(nums[0]);
        int state = 0;
        for(int i = 1; i < nums.length; i++){
            int num = result.pop();
            int curr = nums[i]-num;
            if(state != 0){
                if(curr * state < 0){
                    result.push(num);
                    state = curr;
                }
                result.push(nums[i]);
            }else{
                result.push(num);
                if(curr != 0) {
                    result.push(nums[i]);
                    state = curr;
                }
            }
        }
        return result.size();
    }

}
