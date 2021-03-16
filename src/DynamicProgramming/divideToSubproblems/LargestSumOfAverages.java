package DynamicProgramming.divideToSubproblems;

public class LargestSumOfAverages {
    // 做法: 这题稍微有点难理解, 总的来说就是dp[i][j]代表当前把int[] A上i+1的长度分成j份
    // 1. 我们首先把从0开始dp[i+1][1]的位置都赋值为当前A上面i+1的平均数
    // 2. 我们建立一个helper divide(len, k, A, dp), 建立一个cur = 0, 然后forloop i从len-1开始到0, cur += A[i] 更新当前的dp[len][k] = Math.max(dp[len][k], cur/(len-i) + divide(i, k-1, A, dp));
    // 其实就是用两段(1, i-1], [i, len-1]的平均数的和来更新 dp[len][k], (之前那段dp[len][1]已经求出来了), 相当于把当前的长度len分成了两段的subproblem来解决
    // Runtime: O(n^2), Space: O(n^2);
    public double largestSumOfAverages(int[] A, int K) {
        if(A.length < 1) return 0;
        int len = A.length;
        if(K > len) return 0;
        double[][] dp = new double[len+1][len+1];
        double cur = 0;
        for(int i = 0; i < len; i++){
            cur += A[i];
            dp[i+1][1] = cur /(i+1);
        }
        return  divide(len, K, A, dp);
    }

    private double divide(int len, int k, int[] A, double[][] dp){
        if(dp[len][k] > 0) return dp[len][k];
        double cur = 0;
        for(int i = len - 1; i > 0; i--){
            cur += A[i];
            dp[len][k] = Math.max(dp[len][k], cur/(len-i) + divide(i, k-1, A, dp)); //k最小到1
        }
        return dp[len][k];
    }



    //自己准备用double array写
    public double largestSumOfAverages_self(int[] A, int K) {
        if (A.length < 1) return 0;
        double res = 0, curSum = 0;
        int curNum = 0;
        double[] cut = new double[K], unCut = new double[K];
        for (int i = 0; i < A.length; i++) {

        }
        return 0;
    }





}
