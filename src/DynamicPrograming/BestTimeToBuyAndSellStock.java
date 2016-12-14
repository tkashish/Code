package DynamicPrograming;

/**
 * Created by tayalka on 11/14/2016.
 */
public class BestTimeToBuyAndSellStock {
    private static final int MIN = Integer.MIN_VALUE;

    /**
     * This would find the ma profit if multiple buys are allowed
     */
    public int maxProfitWithMultipleBuyOrSell(int[] prices) {
        int lastBuy = -prices[0];
        int lastSell = 0;
        for(int i = 1; i < prices.length; i++){
            int price = prices[i];
            lastBuy = Math.max(lastBuy, lastSell-price);
            lastSell = Math.max(lastSell, lastBuy+price);
        }
        return Math.max(lastBuy,lastSell);
    }


    public int maxProfit(int[] prices) {
        int len = prices.length;
        if(len == 0 || len == 1 ) return 0;
        int buyIndex = 0;
        int sellIndex = len-1;
        int result = prices[sellIndex] - prices[buyIndex];
        for(int i = 1, j = len-2; i < sellIndex || j > buyIndex ; i++, j--){
            if(prices[buyIndex] > prices[i]) buyIndex = i;
            if(prices[sellIndex] < prices[j]) sellIndex = j;
            if(sellIndex > buyIndex)result = Math.max(result, prices[sellIndex] - prices[buyIndex]);
            System.out.println(buyIndex+ " " + sellIndex + " " + i + " " + j);
        }
        return result < 0 ? 0 : result;
    }
    public static void main(String[] args) {
        BestTimeToBuyAndSellStock best = new BestTimeToBuyAndSellStock();
//        System.out.println(best.maxProfit(new int[]{7,1,5,3,6,4}));
//        System.out.println(best.maxProfit(new int[]{7, 6, 4, 3, 1}));
//        System.out.println(best.maxProfit(new int[]{1,4,2}));
//        System.out.println(best.maxProfit(new int[]{3, 2, 6, 5, 0, 3}));
        System.out.println(best.maxProfit(new int[]{2, 1, 2, 1, 0, 0, 1}));
    }
}
