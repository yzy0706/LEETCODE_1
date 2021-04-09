package DynamicProgramming.accumulate;

import java.util.Arrays;

public class VideoStitching {
    // 做法: 这题的方法比较新, 是用一个一维dp, dp[i]代表覆盖当前的数至少需要几个interval
    // 1. 建立一个dp = new int[T+1]; 全部初始值为2*T(之前我用Integer.MAX_VALUE不行, 所以随便取一个大于T的数), dp[0] = 0;
    // 2. 对于每一位数i属于[0, T], 只要它在某个interval内, dp[i] = Math.min(dp[i], dp[clip[0]] + 1); , 它需要的interval的数量取决于clip[0].
    //    如果浏览完所有的clip当前的dp[i]还是没有变, 证明它不属于任何interval里面, 所以代表有数字没法被cover, 所以直接return -1;
    // Runtime: O(TN), N是clips的大小, Space: O(T)
    public int videoStitching_1d(int[][] clips, int T) {
        if(T == 0) return 0;
        int[] dp = new int[T+1];
        Arrays.fill(dp, 2*T);
        dp[0] = 0;
        for(int i = 1; i <= T; i++){
            for(int[] clip : clips){
                if(i >= clip[0] && i <= clip[1]){
                    dp[i] = Math.min(dp[i], dp[clip[0]] + 1);
                }
            }
            if(dp[i] == 2*T) return -1;
        }
        return dp[T];
    }




    //第一遍用2d dp写的， 感觉好像不太对
    public int videoStitching(int[][] clips, int T) {
        if(T == 0) return 0;
        int[][] dp = new int[T+1][T+1];
        for(int[] row : dp){
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        int res = Integer.MAX_VALUE;
        for(int[] clip : clips){
            int start = clip[0];
            int end = clip[1];
            for(int i = start; i <= end && i <= T; i++){
                dp[start][i] = 1;
            }
            if(dp[0][start] != Integer.MAX_VALUE){
                dp[0][end] = Math.min(dp[0][end], dp[0][start] + 1);
                if(dp[end][T] != Integer.MAX_VALUE){

                }
            }
        }
        return res;
    }
}
