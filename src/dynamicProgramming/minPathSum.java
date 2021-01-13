package dynamicProgramming;

public class minPathSum {
    //第二遍写的，用的是取左边和上面比较小的那个dp[][]的方法，还是建新的dp
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i == 0 && j == 0){
                    dp[i][j] = grid[i][j];
                }
                else if(i == 0){
                    dp[i][j] = dp[i][j-1] + grid[i][j];
                }
                else if(j == 0){
                    dp[i][j] = dp[i-1][j] + grid[i][j];
                }
                else{
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
                }
            }
        }

        return dp[m-1][n-1];

    }


    //第一遍自己写的
    public int res = Integer.MAX_VALUE;
    public int minPathSum_2(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        return res;

    }

    public void findMin(int[][] grid, int i, int j, int m, int n, int[][] dp){
        if(i == m-1 && j == n-1){
            if(grid[i][j] < res ) res = dp[i][j];
            return;
        }
        dp[i][j] = dp[i-1][j] + grid[i][j];
        findMin(grid, i+1, j, m, n, dp);
        findMin(grid, i, j+1, m, n, dp);
        dp[i][j] -= grid[i-1][j];
        dp[i][j] = dp[i][j-1] + grid[i][j];
        findMin(grid, i+1, j, m, n, dp);
        findMin(grid, i, j+1, m, n, dp);
    }


}
