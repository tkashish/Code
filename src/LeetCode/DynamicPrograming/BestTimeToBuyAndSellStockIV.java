package LeetCode.DynamicPrograming;

import java.util.Arrays;

/**
 * Created by kashishtayal on 11/21/16.
 */
public class BestTimeToBuyAndSellStockIV {
    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        if(len == 0 || len == 1 || k == 0)return 0;
        int[][] dp = new int[k+1][len];
        for(int K = 1; K < k+1; K++){
            int lastBuyIndex = 0;
            int max = 0;
            for(int i = 1; i < len; i++) {
                if(lastBuyIndex > 0) {
                    max = Math.max(max, dp[K - 1][lastBuyIndex - 1] + prices[i] - prices[lastBuyIndex]);
                    if(prices[i]< prices[i-1] && dp[K-1][lastBuyIndex-1] < dp[K-1][i-1]){
                        lastBuyIndex = i;
                    }
                }else{
                    max = Math.max(max,prices[i] - prices[lastBuyIndex]);
                    if(prices[i]< prices[i-1] && prices[lastBuyIndex] > prices[i-1]){
                        lastBuyIndex = i;
                    }
                }
                max = Math.max(max,dp[K-1][i]);
                dp[K][i] = max;
            }
        }
        return dp[k][len-1];
    }
    public static void main(String[] args) {
        BestTimeToBuyAndSellStockIV best = new BestTimeToBuyAndSellStockIV();
        System.out.println(best.maxProfit(1,new int[]{1,2,3,4,5,6,7}));
        System.out.println(best.maxProfit(2,new int[]{1,2,3,4,5,6,7}));
        System.out.println(best.maxProfit(1,new int[]{5,9,1,10}));
        System.out.println(best.maxProfit(2,new int[]{5,9,1,10}));
        System.out.println(best.maxProfit(3,new int[]{5,9,1,10}));
        System.out.println(best.maxProfit(1,new int[]{5,9,6,10}));
        System.out.println(best.maxProfit(2,new int[]{5,9,6,10}));
        System.out.println(best.maxProfit(3,new int[]{5,9,6,10,2,1,5}));
        System.out.println(best.maxProfit(2,new int[]{1,2,4,2,5,7,2,4,9,0}));

    }
}

