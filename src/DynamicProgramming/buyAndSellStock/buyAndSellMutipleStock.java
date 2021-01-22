package DynamicProgramming.buyAndSellStock;

public class buyAndSellMutipleStock {
    public int maxProfit(int[] prices) {
        int res = 0;
        int base = prices[0];
        for(int i = 0 ; i < prices.length ; i++){
            if(prices[i]>base) {
                res += prices[i] - base;
                base = prices[i];
            }
        }
        return res;
    }
//        if(prices.length < 1) return 0;
//        int res = 0;
//        int temp = 0;
//        int lowestPrice = prices[0];
//        for(int i = 0 ; i < prices.length; i++){
//            if(prices[i] < lowestPrice){
//                lowestPrice = prices[i];
//            }
//            else if(prices[i] - lowestPrice> temp){
//                temp = prices[i] - lowestPrice;
//                res += temp;
//                temp = 0;
//                lowestPrice = prices[i];
//
//            }
//        }
//        return res;
//    }
}
