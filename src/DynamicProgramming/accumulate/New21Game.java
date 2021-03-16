package DynamicProgramming.accumulate;

public class New21Game {
    //做法: dp[i]记录要得到和为i的几率
    // 1. 预设dp = new int[N+1], dp[0] = 1, wSum = 1;
    // 2. forloop i从1到N, dp[i] = wSum / W;
    //      a. 如果 i < K, wSum += dp[i]; i >= K, res += dp[i];
    //      b. 如果 i >= W了, 证明必须要用两个以上的数相加了, wSum -= dp[i - W];

    // Runtime: O(N), Space: O(N)
    public double new21Game(int N, int K, int W) {
        if(K == 0 || N >= K + W) return 1;
        double[] dp = new double[N+1];
        dp[0] = 1;
        double probSum = 1;
        double res = 0;
        for(int i = 1; i <= N; i++){
            dp[i] = probSum / W; //几率一直/10
            if(i < K) probSum += dp[i];
            else res += dp[i];
            if(i >= W) probSum -= dp[i - W];
        }
        return res;
    }
}



//    public double new21Game(int N, int K, int W) {
//        int[][][] dp = new int[W][W][K];
//        for(int i = 0; i < W; i++){
//            dp[i][0][0] = i + 1;
//        }
//        for(int i = 0; i < W; i++){
//            for(int j = 1; j < K; j++){
//                for(int k =)
//                    if(dp[i][j-1] < K){
//                    }
//            }
//        }
//    }

