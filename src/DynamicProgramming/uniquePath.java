package DynamicProgramming;

public class uniquePath {
    // solution给的解法， 就是把二维dp所有的位置都浏览一遍，每个位置的数值都先默认为1 每一个位置都是上左两个位置dp[i-1][j] + dp[i][j-1];
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for(int[] arr: dp){
            Arrays.fill(arr, 1);
        }
        for(int i = 1 ; i < m ; i++){
            for(int j = 1; j < n ; j++){
                dp[i][j] = dp[i - 1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }



    //自己照着climbingStairs写的但不太对
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m+1][n+1];
        return expand(m, n, 1, 1, dp);
    }


    public int expand(int m, int n, int i, int j, int[][] dp){
        if(i > m || j > n) return 0;
        if(i == m && j == n) return 1;
        if(dp[i][j] > 0) return dp[i][j];
        dp[i][j] = expand(m, n, i-1, j, dp) + expand(m, n, i, j-1, dp);
        return dp[i][j];
    }
}
