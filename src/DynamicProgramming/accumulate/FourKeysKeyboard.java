package DynamicProgramming.accumulate;

public class FourKeysKeyboard {
    // 做法: 用的dp和greedy的结合, dp[i]记录的是当前按了i次键盘以后的最大结果
    // 1. 首先, 如果一共按键盘的次数 N <= 6, 那直接return N就好了, 因为我们肯定要Ctrl-A, Ctrl-C, Ctrl-V按三次才能至少复制一次之前的, 如果 <= 6的话我们复制得到的就是 3 * (6-3-2) = 3, 加上本身的3就是6
    // 2. 首先dp[N+1]上每个数都初始设置为i, 因为如果只是单纯的复制A的话在第i次按键就得到i个A
    // 3. forloop i从3到N-2, 在这个区间里, dp[i]作为被复制的个数才有可能更新最大的结果res, 从而使res > N; 一直更新res, 也就是dp[N]
    // 4. forloop j从3到i-2, 跟上面一样, j也是>=3才会作为被复制的个数, 有能改变dp[i]的能力, 从而使dp[i] > i; 这样j到i就相当于一个N = i的subproblem, 从而更新dp[i]

    // Runtime: O(N^2), Space: O(N)

    public int maxA_dp(int N) {
        int[] dp = new int[N+1];
        for(int i = 0; i < N; i++){
            dp[i] = i;
        }
        if(N <= 6) return N;
        int res = N;
        for(int i = 3; i < N - 2; i++){
            for(int j = 3; j < i - 2; j++){
                dp[i] = Math.max(dp[i], dp[j] * (i - j - 1));
            }
            dp[N] = Math.max(dp[N], dp[i] * (N - i - 1));
        }
        return dp[N];
    }








    // 用recursion来记录到每个位置i之前最多能复制多少个， 也就是把第i位的结果用maxA(i+1)求出来
    public int maxA(int N) {
        int[] dp = new int[N];
        if(N <= 6) return N;
        int res = N;
        for(int i = 2; i < N - 3; i++){
            res = Math.max(res, maxA(i+1) * (N - i - 2));
        }
        return res;
    }
}
