package DynamicProgramming.math;

public class DominoAndTrominoTiling {
    //做法: 这题变成了一个数学题, dp[i] = (2 * dp[i-1] + dp[i-3]) % mod; 画图确实也能用数学归纳法总结出来
    // 错误点: 1 * 10^9 = 1E9 必须要用double来identify
    //Runtime : O(n), Space: O(n)
    public int numTilings(int N) {
        double mod = 1E9 + 7 ;
        long[] dp = new long[N+1];
        if(N < 4) return N < 3 ? N : 5;
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 5;

        for(int i = 4; i <= N; i++){
            dp[i] = (2 * dp[i-1] + dp[i-3]) % (long)mod;
        }
        return (int)dp[N];
    }
}
