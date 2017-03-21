package LeetCode.DynamicPrograming;

import java.util.Arrays;

/**
 * Created by kashishtayal on 11/21/16.
 */
public class BestTimeToBuyAndSellStockIV {
    private static int maxProfitI(int k, int[] prices) {
        int[] buyMax = new int[k + 1];
        Arrays.fill(buyMax, Integer.MIN_VALUE);
        buyMax[0] = 0;
        int[] sellMax = new int[k + 1];
        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = 1; j < k + 1; j++) {
                sellMax[j] = Math.max(buyMax[j] + prices[i], sellMax[j]);
                buyMax[j] = Math.max(buyMax[j], sellMax[j - 1] - prices[i]);
                max = Math.max(max, Math.max(sellMax[j], buyMax[j]));
            }
        }
        return max;
    }
    public static void main(String[] args) {
        BestTimeToBuyAndSellStockIV best = new BestTimeToBuyAndSellStockIV();
        System.out.println(maxProfitI(1,new int[]{1,2,3,4,5,6,7}));
        System.out.println(maxProfitI(2,new int[]{1,2,3,4,5,6,7}));
        System.out.println(maxProfitI(1,new int[]{5,9,1,10}));
        System.out.println(maxProfitI(2,new int[]{5,9,1,10}));
        System.out.println(maxProfitI(3,new int[]{5,9,1,10}));
        System.out.println(maxProfitI(1,new int[]{5,9,6,10}));
        System.out.println(maxProfitI(2,new int[]{5,9,6,10}));
        System.out.println(maxProfitI(3,new int[]{5,9,6,10,2,1,5}));
        System.out.println(maxProfitI(2,new int[]{1,2,4,2,5,7,2,4,9,0}));

    }
}

