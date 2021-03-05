package DynamicProgramming.accumulate;

public class MinCostClimbingStairs {
    // 做法: 就直接拿前面的两位的状态accumulate, dp[i] = Math.min(dp[i-1] + cost[i-1], dp[i-2] + cost[i-2]), 这题是一种基础的之前的状态的叠加
    // Runtime: O(n), Space: O(n);
    public int minCostClimbingStairs(int[] cost) {
        if(cost.length <= 2) return 0;
        int[] dp = new int[cost.length + 1];
        dp[0] = 0;
        dp[1] = 0;
        for(int i = 2; i <= cost.length; i++){
            dp[i] = Math.min(dp[i-1] + cost[i-1], dp[i-2] + cost[i-2]);
        }
        return dp[cost.length];

    }
}
