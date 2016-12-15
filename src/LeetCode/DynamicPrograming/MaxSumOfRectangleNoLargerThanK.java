package LeetCode.DynamicPrograming;

import sun.awt.image.ImageWatched;
import sun.reflect.generics.tree.Tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;

/**
 * Created by kashishtayal on 11/18/16.
 */
public class MaxSumOfRectangleNoLargerThanK {
    public int maxSumSubmatrixI(int[][] matrix, int k) {
        int r = matrix.length;
        int c = matrix[0].length;
        int rc = r*c;
        int[][] dp = new int[rc][rc];
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < rc; i++){
//            System.out.println(i);
            int rCurr = i/c;
            int cCUrr = i%c;
            int index = rCurr*c+cCUrr;
            for(int rr = rCurr; rr < r; rr++){
                for (int cc = cCUrr; cc < c; cc++) {
//                    System.out.println(rr+"  " + cc + " : " + rCurr + " "+ cCUrr);
                    int currIndex = rr*c+cc;
                    if(rr == rCurr && cc == cCUrr){
                        dp[index][currIndex] = matrix[rr][cc];
                    }else if(rr-1 < rCurr){
                        dp[index][currIndex] = matrix[rr][cc]+dp[index][(rr*c)+cc-1];
                    }else if(cc- 1 < cCUrr){
                        dp[index][currIndex] = matrix[rr][cc]+dp[index][(rr-1)*c+cCUrr];
                    }else{
                        dp[index][currIndex] = matrix[rr][cc]+dp[index][(rr-1)*c+cc]
                        +dp[index][rr*c+cc-1] - dp[index][(rr-1)*c + cc-1];
                    }
                    if(dp[index][currIndex] < k){
                        max = Math.max(max,dp[index][currIndex]);
                    }else if(dp[index][currIndex] == k){
                        return k;
                    }
                }
            }
        }
        return max;
    }


    public int maxSumSubmatrix(int[][] matrix, int k) {
        int len = matrix.length;
        if(len == 0) return 0;
        int wid = matrix[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int r = Math.min(len,wid);
        int c = Math.max(len,wid);
        boolean isRowEqR = len < wid;
        if(!isRowEqR){
            matrix = transposeMatrix(matrix);
        }
        queue.add(new int[0]);
        queue.add(null);
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < r; i++){
            int[] arr = matrix[i];
            max = Math.max(max, findMaxLessThanK(arr,k));
            if(max == k) return k;
            queue.add(arr);
            int[] prev = null;
            while((prev = queue.remove()) != null){
                for(int m = 0; m < prev.length; m++){
                    prev[m] += arr[m];
                }
                queue.add(prev);
                max = Math.max(max, findMaxLessThanK(prev,k));
                if(max == k) return k;
            }
            queue.add(null);
        }
        return max;
    }
    public static int[][] transposeMatrix(int [][] m){
        int[][] temp = new int[m[0].length][m.length];
        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m[0].length; j++)
                temp[j][i] = m[i][j];
        return temp;
    }
    public int findMaxLessThanK(int[] nums, int k){
        if(nums.length == 0) return Integer.MIN_VALUE;
        if(nums[0] == k) return k;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        queue.add(null);
        int max = Integer.MIN_VALUE;
        for(int i = 0;i < nums.length; i++){
            queue.add(0);
            if(nums[i] == k) return k;
            else{
                Integer sum = null;
                while( (sum = queue.remove()) != null){
                    int num = sum+nums[i];
                    if(num == k){
                        return k;
                    }else{
                        queue.add(num);
                        if(num < k){
                            max = Math.max(max, num);
                        }
                    }
                }
                queue.add(null);
            }
        }
        return max;
    }
    private int maxSumSubArray(int[] nums , int k){

        int max = Integer.MIN_VALUE;
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        int sum = 0;
        for(int num : nums){
            sum += num;
            Integer lastSum = set.ceiling(sum-k);
            if(lastSum != null){
                max = Math.max(max,sum-lastSum);
            }
            set.add(sum);
        }
        return max;
    }
    public static void main(String[] args) {
        MaxSumOfRectangleNoLargerThanK max = new MaxSumOfRectangleNoLargerThanK();
        int[][] matrix = new int[][]{
                new int[]{1,0,1},
                new int[]{2,2,-1}
        };
        System.out.println(max.maxSumSubmatrix(matrix,3));
        System.out.println(max.maxSumSubmatrix(matrix,-2));
        System.out.println(max.maxSumSubmatrix(matrix,-1));
        System.out.println(max.maxSumSubmatrix(matrix,0));
        System.out.println(max.maxSumSubmatrix(matrix,1));
        System.out.println(max.maxSumSubmatrix(matrix,2));
        System.out.println(max.maxSumSubmatrix(matrix,3));
        System.out.println(max.maxSumSubmatrix(matrix,4));
        System.out.println(max.maxSumSubmatrix(matrix,5));

//        System.out.println(max.maxSumSubArray(new int[]{10,200,10}, 15));
    }
}
