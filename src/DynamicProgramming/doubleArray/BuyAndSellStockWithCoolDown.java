package DynamicProgramming.doubleArray;

public class BuyAndSellStockWithCoolDown {
    //做法： 用一个int[] buy来代表到i之前最后一个transaction是buy的话的最大利润,  用int[] sell来表示i之前是sell的最大利润;
    // 主要利用了每个transaction是不重复的。

    //在buy[i]上， 我们应该比较sell[i-2] - prices[i](在i-2或者之前就卖了， 现在买了i）,或者buy[i-1]， 也就是不买i；
    // buy[i] = Math.max(buy[i-1], sell[i-2] - prices[i])

    //在sell[i]上， 我们可以用 buy[i-1]， 也就是之前买了， 现在要卖； 或者 sell[i-1], 也就是之前卖了， 现在不卖
    // sell[i] = Math.max(sell[i-1], buy[i-1] + prices[i]);

    public int maxProfit(int[] prices) {
        if(prices.length < 2) return 0;
        int[] buy = new int[prices.length];
        buy[0] = -prices[0];
        buy[1] = Math.max(-prices[0], -prices[1]);
        int[] sell = new int[prices.length];
        sell[0] = 0;
        sell[1] = Math.max(0, buy[0] + prices[1]);
        for(int i = 2 ; i < prices.length; i++){
            buy[i] = Math.max(buy[i-1], sell[i-2] - prices[i]);
            sell[i] = Math.max(sell[i-1], buy[i-1] + prices[i]);
        }
        return sell[prices.length - 1];
    }
}
