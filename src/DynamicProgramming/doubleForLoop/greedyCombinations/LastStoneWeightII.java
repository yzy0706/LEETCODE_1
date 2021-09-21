package DynamicProgramming.doubleForLoop.greedyCombinations;

public class LastStoneWeightII {
    //做法: 这题主要是用的greedy combination, 和coin combination差不多的解法
    // 1. 用i forloop stones上所有的数
    // 2. 用j forloop 去从sum/2到stones[i]的所有数, dp[j] = Math.max(dp[j], dp[j - stones[i] + stones[i]]); 看j可以被stones上所有的数怎么样相加
    // 3. forloop完了以后dp[sum/2]就是stones上所有的数里面任意组合所有的数最接近sum/2的那个可能, 那么return sum - 2 * dp[sum/2];

    // Runtime: O(n^2), Space: O(n), n是sum的大小
    public int lastStoneWeightII(int[] stones) {
        int sum = 0,  n = stones.length;
        for(int s : stones) sum += s;
        int[] dp = new int[sum/2+1];
        for(int i = 0; i < n; i++){
            for(int j = sum/2; j >= stones[i]; j--){
                dp[j] = Math.max(dp[j], dp[j-stones[i]] + stones[i]);
            }
        }
        return sum - 2 * dp[sum/2] ;
    }
}
