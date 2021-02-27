package DynamicProgramming.accumulate;

import java.util.Arrays;

public class TwoKeysKeyboard {
    //做法: 数学解法:
    // 1. 先预设dp[i] = i, 因为我们2个的时候需要copy + paste两步
    // 2. 用i从1到n做forloop, j从1到i做forloop, 如果 i % j == 0; 则我们知道从j加j一直加到i需要加 i/j-1次, 然而copy当前的需要1次,
    // 所以dp[i] = Math.min(dp[i], dp[j] + i/j);

    //Runtime: O(n^2), Space: O(n);
    public int minSteps_Math(int n) {
        if(n == 1) return 0;
        int[] dp = new int[n+1];
        for(int i = 1; i <= n; i++){
            dp[i] = i; //到2两步, 到3三步
            for(int j = i-1; j >= 1; j--){
                if(i % j == 0){
                    dp[i] = Math.min(dp[i], dp[j] + i/j);
                    break;
                }
            }
        }
        return dp[n];
    }









    // recursion + dp
    int res;
    public int minSteps(int n) {
        if(n == 1) return 0;
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        res = Integer.MAX_VALUE;
        dp[2] = 2;
        next(dp, 1, 2, n);
        return res;
    }

    public void next(int[] dp, int add, int pos, int n){
        if(pos == n){
            res = Math.min(res, dp[n]);
            return;
        }
        int[] copy = dp;
        int[] paste = dp;
        if(pos + pos <= n){ //copy当前的值
            copy[pos + pos] = Math.min(copy[pos + pos], copy[pos] + 1 + 1);
            next(copy, pos, pos + pos, n);
        }
        if(pos + add <= n){ //继续paste
            paste[pos + add] = Math.min(paste[pos + add], paste[pos] + 1);
            next(paste, add, pos + add, n);
        }
        return;

    }







    // dp
    public int minSteps_1(int n) {
        if(n == 1) return 0;
        int[] copy = new int[n+1];
        int[] paste = new int[n+1];
        Arrays.fill(copy, Integer.MAX_VALUE);
        Arrays.fill(paste, Integer.MAX_VALUE);
        copy[1] = 0;
        paste[1] = 0;

        for(int i = 2; i < n; i++){
            copy[i] = Math.min(copy[i], paste[i-1] + 1);
            if(i + i <= n){
                paste[i + i + 1] = Math.min(paste[i + i + 1], copy[i] + 1);
            }
            paste[i] = Math.min(paste[i], paste[i-1] + 1);
        }

        return paste[n];
    }
}
