package LeetCode.DynamicPrograming;

import java.util.Arrays;

/**
 * Created by kashishtayal on 11/16/16.
 */
public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        for(int currAmount = 1; currAmount<amount+1; currAmount++){
            int min = Integer.MAX_VALUE;
            for(int j = 0; j < coins.length; j++){
                int coin = coins[j];
                if(currAmount >= coin){
                    if(dp[currAmount-coin] >= 0) {
                        min = Math.min(min, 1 + dp[currAmount - coin]);
                    }
                }
            }
            dp[currAmount] = (min == Integer.MAX_VALUE)?-1:min;
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        CoinChange cc = new CoinChange();
        System.out.println(cc.coinChange(new int[]{1,2,5}, 11));
        System.out.println(cc.coinChange(new int[]{2}, 3));
        System.out.println(cc.coinChange(new int[]{}, 3));
    }
}
