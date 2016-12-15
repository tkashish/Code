package LeetCode.DynamicPrograming;

/**
 * Created by kashishtayal on 11/9/16.
 */
public class UniqueBinarySearchTree {
    public int numTrees(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        for(int i = 1; i < n+1; i++){
            int num = i/2;
            for(int j = 0; j < num; j++){
                dp[i] += 2*dp[j]*dp[i-1-j];
            }
            if(i%2 != 0){
                dp[i] += dp[num]*dp[num];
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        UniqueBinarySearchTree ubst = new UniqueBinarySearchTree();
        for (int i = 0; i <= 6; i++) {
            System.out.println("Number of ubst for: " + i + " is = " + ubst.numTrees(i));
        }
        System.out.println(ubst.numTrees(2));
    }
}
