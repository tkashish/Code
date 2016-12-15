package LeetCode.DynamicPrograming;

/**
 * Created by kashishtayal on 11/21/16.
 */
public class BestTimeToBuyAndSellStockIII {
    public int maxProfitSingleTransaction(int[] prices, int start) {
        if(start >= prices.length) return 0;
        int maxProfit = 0;
        int lastBuy = prices[start];
        for(int i = 1+start; i < prices.length; i++){
            maxProfit = Math.max(maxProfit,prices[i]-lastBuy);
            lastBuy = Math.min(prices[i],lastBuy);
        }
        return maxProfit;
    }

    public int maxProfitSlow(int[] prices) {
        int maxProfit = 0;
        int maxProfitFirstTranaction= 0;
        int lastBuy = prices[0];
        for(int i = 1; i < prices.length; i++){
            maxProfitFirstTranaction = Math.max(maxProfitFirstTranaction,prices[i]-lastBuy);
            lastBuy = Math.min(lastBuy,prices[i]);
            maxProfit = Math.max(maxProfit, maxProfitFirstTranaction+maxProfitSingleTransaction(prices,i+1));
            System.out.println(maxProfitFirstTranaction);
        }
        return maxProfit;
    }

    public int maxProfit(int[] prices) {
        int len = prices.length;
        if(len == 0 || len == 1) return 0;
        int[] forward = new int[len];
        int lastBuy = prices[0];
        int max = 0;
        for(int i = 1; i < len; i++){
            max = Math.max(max,prices[i]-lastBuy);
            lastBuy = Math.min(lastBuy,prices[i]);
            forward[i] = max;
        }
        int maxProfit = 0;
        int lastSell = prices[len-1];
        max = 0;
        for(int i = len-2; i > -1; i--){
            max = Math.max(max,lastSell-prices[i]);
            lastSell = Math.max(lastSell,prices[i]);
            if(i > 0) {
                maxProfit = Math.max(maxProfit, forward[i - 1] + max);
            }else{
                maxProfit = Math.max(maxProfit,max);
            }
        }
        return maxProfit;
    }
    public static void main(String[] args) {
        BestTimeToBuyAndSellStockIII best = new BestTimeToBuyAndSellStockIII();
//        System.out.println(best.maxProfit(new int[]{9,7,6,4,2}));
//        System.out.println(best.maxProfit(new int[]{1,2,3,4,5,2,3}));
//        System.out.println(best.maxProfit(new int[]{}));
//        System.out.println(best.maxProfit(new int[]{1}));
//        System.out.println(best.maxProfit(new int[]{1,2}));
        System.out.println(best.maxProfit(new int[]{1,2,3}));
    }
}
