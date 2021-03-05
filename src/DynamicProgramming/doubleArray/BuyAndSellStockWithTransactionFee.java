package DynamicProgramming.doubleArray;

public class BuyAndSellStockWithTransactionFee {
    //做法: 跟之前with cooldown time一样也是用的double array, buy[i]记录到当前i这一位为止最近的一位是买的操作的最大profit, sell[i]记录到i位为止最近的一位是卖的操作的最大profit
    // 1. 建立两个int[] buy, int[] sell, buy[0] = - prices[0];
    // 2. 从第一位开始 buy[i] = Math.max(buy[i-1], sell[i-1] - prices[i]); sell[i] = Math.max(sell[i-1], buy[i-1] + prices[i] - fee);

    // Runtime: O(n), Space: O(n)
    public int maxProfit(int[] prices, int fee) {
        int len = prices.length;
        if(len <= 1) return 0;
        int[] buy = new int[len];
        int[] sell = new int[len];
        buy[0] = -prices[0];
        for(int i = 1; i < len; i++){
            buy[i] = Math.max(buy[i-1], sell[i-1] - prices[i]);
            sell[i] = Math.max(sell[i-1], prices[i] + buy[i-1] - fee);
        }
        return sell[len-1];
    }
}
