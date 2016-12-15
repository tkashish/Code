package LeetCode.DynamicPrograming;

import java.util.Arrays;

/**
 * Created by kashishtayal on 11/14/16.
 */
public class BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        int min = prices[0];
        int profit = 0;
        for(int i = 1; i < len; i++){
            min = Math.min(min,prices[i]);
            profit = Math.max(profit,prices[i]-min);
        }
        return profit;
    }

    public static void main(String[] args) {
        BestTimeToBuyAndSellStock best = new BestTimeToBuyAndSellStock();
        System.out.println(best.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println(best.maxProfit(new int[]{7, 6, 4, 3, 1}));
        System.out.println(best.maxProfit(new int[]{1,4,2}));
    }
}
