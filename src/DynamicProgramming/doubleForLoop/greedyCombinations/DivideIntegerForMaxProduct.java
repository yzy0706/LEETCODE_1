package DynamicProgramming.doubleForLoop.greedyCombinations;

public class DivideIntegerForMaxProduct {
    //做法: 这题主要是在把从i到n的数的最大乘积算出来, 然而从i到n的数又可以拆分为j和i-j来引出之前算出来的j和i-j的最大乘积,
    // 但唯一要注意的是我们不能在每个i之内j的forloop里直接乘 dp[j], dp[i-j], 而是应该比较一下j, dp[j] 还有 i-j, dp[i-j] 这两对,
    // 因为有时候他自己本身比他分成两个数的最大乘积还大
    //Runtime: 最坏的情况就是 O(n!), space: O(n)
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], Math.max(j, dp[j]) * Math.max(dp[i - j], i - j));
            }
        }
        return dp[n];
    }
}
