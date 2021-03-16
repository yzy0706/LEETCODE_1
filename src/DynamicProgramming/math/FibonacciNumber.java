package DynamicProgramming.math;

public class FibonacciNumber {
    // 做法: 最简单的dp做法, 就是简单的叠加前面两个位置的和, return dp[n];
    // Runtime: O(n), Space: O(n)
    public int fib(int n) {
        if(n == 0) return 0;
        if(n == 1) return 1;
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        for(int i = 2; i <= n; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    // Recursion
    // 这里就可以明显的看出， 假如我们是要recursion的话， 因为没有储存前面两个的距离，
    // 我们需要每次都一直run到n==0和n==1那一层再慢慢的return回当前的n的位置
    // Runtime: O(n^2), Space:O(1)

    public int fib_recursion(int n) {
        if(n == 0) return 0;
        if(n == 1) return 1;
        return fib_recursion(n-1) + fib_recursion(n-2);
    }


}
