package DynamicProgramming.accumulate;

public class MatrixBlockSum {
    //做法: 用的dp做法
    // 1. 新建一个dp, 先把第一行和第一列的和求出啦(跟square sum一样), 通过dp[i][j] = mat[i][j] + dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1];减去重合的方式用dp[i][j]记录mat里面0 <= r <= i, 0 <= c <= j的和
    // 2. 浏览每一个位置i, j与k结合起来对应的minI, minJ; maxI, maxJ;
    //     a. 首先默认minI, minJ都是0, 那么res[i][j] = dp[i][j]; 也就是0到i, j的和
    //     b. 如果i > 1 && j > 1, res[i][j] -= dp[minI - 1][maxJ] + dp[maxI][minJ - 1] - dp[minI - 1][minJ - 1]; 记得删掉重合的位置
    //     c. 否则如果i > 1 && j == 0, res[i][j] -= dp[minI - 1][maxJ]; 减去i = [0, minI - 1], j = [0, minJ - 1]的和
    //     d. 同理, 如果j > 1 && i == 0, res[i][j] -= dp[maxI][minJ - 1];

    // Runtime: O(mn), Space: O(mn);


    public int[][] matrixBlockSum(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = mat[0][0];
        for(int i = 1; i < m; i ++) dp[i][0] = mat[i][0] + dp[i-1][0];
        for(int j = 1; j < n; j ++) dp[0][j] = mat[0][j] + dp[0][j-1];
        for(int i = 1; i < m; i ++){
            for(int j = 1; j < n; j ++){
                dp[i][j] = mat[i][j] + dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1]; // cancel the duplicate part
            }
        }
        int[][] res = new int[m][n];
        for(int i = 0; i < m; i ++){
            for(int j = 0; j < n; j ++){
                int minI = Math.max(0, i - k), minJ = Math.max(0, j - k);
                int maxI = Math.min(m - 1, i + k), maxJ = Math.min(n - 1, j + k);
                res[i][j] = dp[maxI][maxJ];
                if(minI > 0 && minJ > 0){
                    res[i][j] -= dp[minI - 1][maxJ] + dp[maxI][minJ - 1] - dp[minI - 1][minJ - 1];
                }
                else if(minI > 0){
                    res[i][j] -= dp[minI - 1][maxJ];
                }
                else if(minJ > 0){
                    res[i][j] -= dp[maxI][minJ - 1];
                }
            }
        }
        return res;
    }
}
