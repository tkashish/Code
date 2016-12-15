package LeetCode.DynamicPrograming;

/**
 * Created by kashishtayal on 11/16/16.
 */
public class MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        if(matrix.length == 0){ return 0;}
        int[][] dp = new int[matrix.length][matrix[0].length];
        int max = 0;
        for(int i = 0; i < matrix.length; i++){
            if(matrix[i][0] == '1'){
                dp[i][0] = 1;
                max = 1;
            }
        }
        for(int i = 0; i < matrix[0].length; i++){
            if(matrix[0][i] == '1'){
                dp[0][i] = 1;
                max = 1;
            }
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if(matrix[i][j] == '1'){
                    if(dp[i-1][j-1] == 0 || dp[i-1][j] == 0 || dp[i][j-1] == 0){
                        dp[i][j] = 1;
                    }else{
                        dp[i][j] = 1+Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]));
                    }
                }
                System.out.print(dp[i][j]+" ");
                max = Math.max(max,dp[i][j]);
            }
            System.out.println();
        }
        return max*max;
    }

    public static void main(String[] args) {
        MaximalSquare ms = new MaximalSquare();
//        char[][] in = new char[][]{
//                "10100".toCharArray(),
//                "10111".toCharArray(),
//                "11111".toCharArray(),
//                "10010".toCharArray()
//        };
        char[][] in = new char[][]{
                "0001".toCharArray(),
                "1101".toCharArray(),
                "1111".toCharArray(),
                "0111".toCharArray(),
                "0111".toCharArray()
        };
        System.out.println(ms.maximalSquare(in));
    }
}