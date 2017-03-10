package LeetCode.DynamicPrograming;

/**
 * Created by tayalka on 3/9/2017.
 */
public class LongestPalindromicSubsequence {
    public static int longestPalindromeSubseq(String s) {
        int len= s.length();
        int[][] dp = new int[len][len];
        for(int diff = 0; diff <= len-1; diff++){
            for(int i = 0; i < len-diff; i++){
                int j = i+diff;
                if(diff == 0){
                    dp[i][j] = 1;
                }else{
                    if(s.charAt(i) == s.charAt(j)){
                        dp[i][j] = 2+dp[i+1][j-1];
                    }else{
                        dp[i][j] = Math.max(dp[i+1][j],dp[i][j-1]);
                    }
                }
            }
        }
        return dp[0][len-1];
    }

    public static void main(String[] args) {
        System.out.println(longestPalindromeSubseq("bbbab"));
    }
}
