package LeetCode.DynamicPrograming;

import java.util.Arrays;

/**
 * Created by kashishtayal on 11/13/16.
 */
public class BestTimeToBuySellStocks {
    int[] buy;
    int[] sell;
    int[] rest;
    private static final int MIN = Integer.MIN_VALUE;
    public int maxProfit(int[] prices) {
        buy = new int[prices.length+2];
        sell = new int[prices.length+2];
        rest = new int[prices.length+2];
        buy[0] = MIN;
        buy[1] = MIN;
        for(int i = 2; i < buy.length; i++){
            int price = prices[i-2];
            int maxProfit = MIN;
            for(int j = i-2; j >= 0; j--){
                maxProfit = Math.max(maxProfit, sell[j]);
            }
            buy[i] = maxProfit-price;
            maxProfit = MIN;
            for(int j = i-1; j >=0 ; j--){
                maxProfit = Math.max(maxProfit, buy[j]);
            }
            if(maxProfit != MIN){
                sell[i] = maxProfit+price;
            }else{
                sell[i] = maxProfit;
            }
            rest[i] = Math.max(buy[i-1], Math.max(sell[i-1], rest[i-1]));
        }
        return Math.max(buy[buy.length-1], Math.max(sell[buy.length-1], rest[buy.length-1]));
    }
    public static void main(String[] args) {
        BestTimeToBuySellStocks best = new BestTimeToBuySellStocks();
        System.out.println(best.maxProfit(new int[]{1,4,2}));
        System.out.println(best.maxProfit(new int[]{1,2,3,0,2}));
    }
}
