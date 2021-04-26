package Heap;

import java.util.PriorityQueue;

public class MinimizeError {
    // 做法: 这题其实是在用pq排列当前的值与ceil的误差 - 当前的值与floor的误差
    // 1. 先浏览所有的prices, 如果floor != ceil, 证明有误差, 把(ceil - f) - (f - floor)放到pq里面去, 然后target -= floor, 假设我们现在都先用floor, res += f - floor;
    // 2. 如果当前target < 0证明最小的floor加起来都会超过整个target, 如果target大于pq.size, 因为我们知道pq里面最大的值也不会==1, 所以如果target大于pq.size()的话target也不可能再被pq里面的差一个个减到0了
    // 3. 因为floor与ceil的差永远是1, 当target > 0时, target --, res += pq.poll(); 把ceil与f的误差与对应的f和floor的差最小的值加到res上, 所以 res += pq.poll();
    // 4. return String.format("%.3f", res);, 这里这个“%.3f"与小数点有关
    // Runtime: O(n), Space: O(n), n是prices的个数
    public String minimizeError_pq(String[] prices, int target) {
        PriorityQueue<Double> pq = new PriorityQueue<>();
        double res = 0;
        for(String s : prices){
            float f = Float.valueOf(s);
            double floor = Math.floor(f);
            double ceil = Math.ceil(f);
            if(floor != ceil) pq.offer((ceil - f) - (f - floor)); //pq放入ceil到f的误差 与 f到floor的误差的差, 这样之后res加上的时候就变成ceil到f的误差了
            target -= floor;
            res += f - floor;
        }
        if(target < 0 || target > pq.size()){ //如果当前target < 0证明最小的floor加起来都会超过整个target, 如果target大于pq.size, 因为我们知道pq里面最大的值也不会==1, 所以如果target大于pq.size()的话target也不可能再被pq里面的差一个个减到0了
            return "-1";
        }
        while(target > 0){
            res += pq.poll(); //把误差比他对应的floor小最多的ceil放进来
            target --; //用一个ceil, target就可以多减一, 因为ceil和floor之前的差是1
        }
        return String.format("%.3f", res);
    }



//    public String minimizeError_dp(String[] prices, int target) {
//        double[] dp = new double[target + 1];
//        Arrays.fill(dp, Integer.MAX_VALUE);
//        double[] pricesDouble = new double[prices.length];
//        int[] floor = new int[prices.length];
//        int[] ceiling = new int[prices.length];
//        for(int i = 0; i < prices.length; i++){
//            float f = Float.valueOf();
//            pricesDouble[i] = (double)cur;
//            System.out.println(cur);
//            if(cur % 1 == 0){
//                floor[i] = (int)cur;
//                ceiling[i] = (int)cur;
//            }
//            floor[i] = (int)cur;
//            // }
//            // for(int i = 0; i < prices.length; i++){
//            //     for(int j = 0; j < prices.length; j++){
//            //         if(j != i)
//            //     }
//            // }
//        }
//        return "";
//    }
}
