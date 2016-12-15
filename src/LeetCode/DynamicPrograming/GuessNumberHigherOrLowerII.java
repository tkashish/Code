package LeetCode.DynamicPrograming;

/**
 * Created by kashishtayal on 11/9/16.
 */
public class GuessNumberHigherOrLowerII {
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n+1][n+1];
        for(int i = 1; i  < n; i++ ){
            for(int j = 1; j <= n-i; j++){
                dp[j][j+i] = Integer.MAX_VALUE;
                for(int k = j;k <= j+i; k++){
                    int left = 0;
                    int right = 0;
                    if(k-1>=j){
                        left = dp[j][k-1];
                    }
                    if(k+1 <= j+i){
                        right = dp[k+1][i+j];
                    }
                    int currSum = k+Math.max(left,right);
                    dp[j][i+j] = Math.min(dp[j][j+i], currSum);
                }
            }
        }
        return dp[1][n];
    }

    public static void main(String[] args) {
        GuessNumberHigherOrLowerII gnhol = new GuessNumberHigherOrLowerII();
        System.out.println(gnhol.getMoneyAmount(6));
    }
}
