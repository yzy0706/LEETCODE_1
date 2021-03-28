package DynamicProgramming.matrix;

public class CountSquareSubmatricesWithAllOnes {
    // 做法: 这题我用的是跟当初那个MaximalSquare一样的做法, dp[i][j] = Math.min(dp[i-1][j]. Math.min(dp[i][j-1], dp[i-1][j-1])) + 1;
    // 看到这种跟matrix上的正方形有关的题都可以考虑这种根据左、 左上、 上各自的计算结果的最小值+1的的方法
    // 1. 先把dp上第一行和第一列是1的位置都变为1, 方便后面叠加
    // 2. 从 i = 1, j = 1开始叠加每一个位置的左、 左上、 上各自的计算结果的最小值+1 dp[i][j] = Math.min(left, Math.min(up, leftCorner)) + 1;
    // Runtime: O(mn), Space: O(mn)

    public int countSquares(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; i ++) dp[i][0] = matrix[i][0] == 1? 1 : 0;
        for(int j = 0; j < n; j ++) dp[0][j] = matrix[0][j] == 1? 1 : 0;

        for(int i = 1; i < m; i ++){
            for(int j = 1; j < n; j++){
                if(matrix[i][j] == 1){
                    dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1])) + 1;
                }
            }
        }

        int res = 0;
        for(int i = 0; i < m; i ++){
            for(int j = 0; j < n; j++){
                res += dp[i][j];
            }
        }

        return res;
    }
}
