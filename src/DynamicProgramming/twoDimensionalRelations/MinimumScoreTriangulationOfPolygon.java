package DynamicProgramming.twoDimensionalRelations;

import java.util.Arrays;

public class MinimumScoreTriangulationOfPolygon {
    // 做法: 这题是用了一个divide to subproblem的做法, dp[i][j]代表从i到j的位置的分割成的各个三角形乘积的最小和,
    // 当我们在i和j之间浏览所有可能作为分割点的k时, dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + values[i] * values[j] * values[k])
    // Runtime: O(n^3), Space: O(n^2)
    public int minScoreTriangulation_2dDP(int[] values) {
        int n = values.length;
        int[][] dp = new int[n][n];
        for(int j = 2; j < n; j++){
            for(int i = j - 2; i >= 0; i--){
                dp[i][j] = Integer.MAX_VALUE;
                for(int k = i+1; k < j; k++){
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + values[i] * values[j] * values[k]);
                }
            }
        }
        return dp[0][n-1];
    }



    //第一遍尝试用greedy做的
    public int minScoreTriangulation(int[] values) {
        int n = values.length;
        Arrays.sort(values);
        int res = 0, cnt = 0;
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                for(int k = j + 1; k < n; k++){
                    if(cnt == n-2) break;
                    res += values[i] * values[j] * values[k];
                    cnt ++;
                }
            }
        }
        return res;
    }
}
