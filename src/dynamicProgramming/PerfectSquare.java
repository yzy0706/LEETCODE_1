package dynamicProgramming;

import java.util.Arrays;

public class PerfectSquare {
    //做法: 用dp来记录每一个数字能承载的最少的square和的数量, dp[i] = Math.min(dp[i - j*j]+1,dp[i]), 每到一个数i， 则再建立一个forloop
    //这个forloop循环一个j，j从1开始， 如果j*j<=n，则叠加dp[i - j*j] +1, 因为这个j可以无限接近这个数i
    //Runtime: O(n), space:O(n)
    public int numSquare(int n) {
        int[] dp =new int[n + 1];
        dp[0] = 0;
        Arrays.fill(dp, Integer.MAX_VALUE);
        for(int i = 1; i <= n; i++){
            for(int j  = 1; j * j <= i; j++){
                dp[i] = Math.min(dp[i - j*j] + 1, dp[i]);
            }

        }
        return dp[n];
    }
}
