package DynamicProgramming.accumulate;

public class NthFibonacciNumber {
    // 做法: 就是一个简单的堆积前面三个数的和的dp
    // Runtime: O(n), Space: O(n)
    public int tribonacci(int n) {
        if(n < 3) return  n == 0 ? 0 : 1;
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;
        for(int i = 3; i <= n; i++){
            dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
        }
        return dp[n];
    }
}
