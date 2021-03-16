package DynamicProgramming.accumulate;

public class MinimumFallingPathSum {
    // 做法: 我用的是一个2d的dp来记录到每一个位置的最短距离, 最后再在dp的最后一个row找最小值即可
    // Runtime: O(n^2) Space: O(n^2)
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int res = Integer.MAX_VALUE;
        int[][] dp = new int[n][n];
        for(int i = 0; i < n; i++){
            dp[0][i] = matrix[0][i];
        }
        for(int i = 1; i < n; i++){
            for(int j = 0; j < n; j++){
                int curNum = matrix[i][j];
                int left = j - 1 >= 0? j - 1 : j;
                int right = j + 1 < n? j + 1 : j;
                dp[i][j] = Math.min(dp[i-1][left], Math.min(dp[i-1][j], dp[i-1][right])) + curNum;
            }
        }

        for(int i = 0; i < n; i++){
            res = Math.min(res, dp[n-1][i]);
        }
        return res;
    }
}
