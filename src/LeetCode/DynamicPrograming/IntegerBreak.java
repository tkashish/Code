package LeetCode.DynamicPrograming;

/**
 * Created by tayalka on 11/17/2016.
 */
public class IntegerBreak {
    public int integerBreakI(int n) {
        int[] dp = new int[n+1];
        dp[1] = 1;
        for(int i = 2; i < n+1; i++){
            for(int j = 1; j <= i/2; j++){
                dp[i] = Math.max(dp[i], Math.max(dp[j],j)*Math.max(i-j,dp[i-j]));
            }
        }
        return dp[n];
    }

    public int integerBreak(int n){
        if(n == 1) return 1;
        int dp[] = new int[n+1];
        dp[1] = 0;
        for(int i = 2; i < n+1; i++){
            dp[i] = dp[i-1];
            if(i < 8){
                dp[i] += i/2;
            }else{
                dp[i] += 3*(dp[i-3]-dp[i-4]);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        IntegerBreak ib = new IntegerBreak();
        for(int i = 1; i < 22; i++){
            System.out.println("---------------");
            System.out.println(ib.integerBreakI(i) + "  " + i );
            System.out.println(ib.integerBreak(i) + "  " + i );
        }
    }
}
