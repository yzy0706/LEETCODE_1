package DynamicProgramming.buyAndSellStock;

public class BuyAndSellStockII {
    //做法: 首先是用常规double array来做的, runtime有点高
    //Runtime: O(n), Space: O(n);
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if(len <= 1) return 0;
        int[] buy = new int[len], sell = new int[len];
        buy[0] = - prices[0];
        for(int i = 1; i < len; i++){
            buy[i] = Math.max(buy[i-1], sell[i-1] - prices[i]);
            sell[i] = Math.max(sell[i-1], buy[i-1] + prices[i]);
        }
        return sell[len-1];
    }


    //做法: 第二种是用greedy一直跑的, 可能testcase里面runtime低一点吧, space肯定是小一点
    //Runtime: O(n), Space: O(1);
    public int maxProfit_greedy(int[] prices) {
        if(prices.length <= 1) return 0;
        int i = 0;
        int res = 0;
        while(i < prices.length){
            int min = prices[i];
            while(i+1 < prices.length && prices[i+1] <= prices[i]){
                i++;
            }
            min = prices[i++];
            while(i+1 < prices.length && prices[i+1] >= prices[i]){
                i++;
            }
            res += i < prices.length ? prices[i] - min : 0;
            i++;
        }
        return res;
    }
}
