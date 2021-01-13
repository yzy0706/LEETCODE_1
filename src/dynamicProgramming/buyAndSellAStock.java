package dynamicProgramming;

public class buyAndSellAStock {
//    public int maxProfit(int[] prices) {
//        if(prices.length<1) return 0;
//        int min = 0;
//        int max = 0;
//        int res = 0;
//        for(int i = 0; i< prices.length ; i++){
//            while(min < max){
//                if(prices[i]< prices[min]){
//                    min = i;
//                }
//                if(prices[i] > prices[max]){
//                    max= i;
//                }
//            }
//        }
//        res = prices[max] - prices[min];
//        return res;
//
//    }

    public int maxProfit(int[] prices) {
        if(prices.length < 1) return 0;
        int res = 0;
        int minprice = Integer.MAX_VALUE;

        for (int i = 0; i < prices.length ; i ++){

            if(prices[i] < minprice){
                minprice = prices[i];
            }
            else if(prices[i] - minprice  > res){
                res = prices[i] - minprice;
            }
        }
        return res;
    }


    //public int maxProfit(int[] prices) {
    //        int res = 0, l = 0, r = prices.length - 1;
    //        while(l < r){
    //            int temp = prices[r] - prices[l];
    //            if(temp > res) res = temp;
    //
    //            if(prices[r-1] >= prices[r]) r--;
    //            if(prices[l+1] <= prices[l] ) l++;
    //            else{
    //                r--;
    //                l++;
    //            }
    //
    //        }
    //
    //        return res;
    //
    //    }
}
