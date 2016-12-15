package LeetCode.DynamicPrograming;

import java.util.Arrays;

/**
 * Created by kashishtayal on 12/14/16.
 */
public class MaximalRectangles {
    public int maximalRectangle(char[][] matrix) {
        if(matrix.length == 0) return 0;
        int[] dp = new int[matrix[0].length];
        int max = 0;
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if(matrix[i][j] == '1'){
                    dp[j]++;
                    int height = dp[j];
                    int width = 1;
                    int currArea = width*height;
                    for(int k = j-1; k > -1; k--){
                        if(dp[k] == 0){
                            break;
                        }
                        width++;
                        height = Math.min(height,dp[k]);
                        currArea = Math.max(currArea,width*height);
                    }
                    max = Math.max(max,currArea);
                }else{
                    dp[j] = 0;
                }
            }
        }
        return max;
    }
    public static void main(String[] args) {
        char[][] matrix = new char[][]{
                "10100".toCharArray(),
                "10111".toCharArray(),
                "11111".toCharArray(),
                "10010".toCharArray()
        };
        MaximalRectangles mr = new MaximalRectangles();
        System.out.println(mr.maximalRectangle(matrix));
    }
}
