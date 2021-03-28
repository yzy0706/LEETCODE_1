package DynamicProgramming.accumulate;

public class KnightDialer {
    //做法: 结合了dp和dfs的做法, 用一个int[][][] dp, dp[i][j][n] 表示当前在i, j这个位置, 当前是倒数第n步, 从当前这个位置开始走一共还有几种走法
    // Runtime: O(12n) = O(n), Space: O(12n) = O(n)

    private static int mod = (int) 1e9+7;
    public int knightDialer_DFS_DP(int n) {
        int[] hori = new int[]{2, 2, -2, -2, 1, 1, -1, -1};
        int[] vert = new int[]{1, -1, 1, -1, 2, -2, 2, 2};
        long[][][] dp = new long[4][3][n+1];
        long res = 0;
        for(int i = 0; i < 4; i ++){
            for(int j = 0; j < 3; j++){
                res += dfs(dp, hori, vert, i, j, n) % mod;
            }
        }
        return (int)res ;
    }



    public long dfs(long[][][] dp, int[] hori, int[] vert, int i, int j, int n){
        if(i < 0 || i >= 4 || j < 0 || j >= 3 || (i == 3 && j != 1)){
            return 0;
        }
        if(n == 1){
            return 1;
        }
        if(dp[i][j][n] > 0) return dp[i][j][n];
        for(int d = 0; d < 8; d ++){
            int nextI = i + vert[d];
            int nextJ = j + hori[d];
            dp[i][j][n] += dfs(dp, hori, vert, nextI, nextJ, n-1) % mod;
        }
        return dp[i][j][n];
    }




    //第一遍想用纯dfs做做崩了
    private int res;
    public int knightDialer(int n) {
        int[] hori = new int[]{2, 2, -2, -2, 1, 1, -1, -1};
        int[] vert = new int[]{1, -1, 1, -1, 2, -2, 2, 2};
        // int[][] dp = new int[4][3];
        // for(int i = 0; i < 4; i ++){
        //     for(int j = 0; j < 3; j++){
        //         if(i == 3 && (j == 0 || j == 2)){
        //             continue;
        //         }
        //         for(int d = 0; d < 8; d ++){
        //             int nextI = i + hori[d];
        //             int nextJ = j + vert[d];
        //             if(nextI < 0 || nextI >= 4 || nextJ < 0 || nextJ >= 3 || (nextJ == 3 && (nextJ == 0 || nextJ == 2))){
        //                 continue;
        //             }
        //             dp[i][j] ++;
        //         }
        //     }
        // }
        res = 0;
        for(int i = 0; i < 4; i ++){
            for(int j = 0; j < 3; j++){
                if(i == 3 && (j == 0 || j == 2)){
                    continue;
                }
                dfs(hori, vert, i, j, n - 1);
            }
        }
        return res % mod;
    }



    public void dfs(int[] hori, int[] vert, int i, int j, int n){
        if(n == 0){
            res++;
            return;
        }
        if(i < 0 || i >= 4 || j < 0 || j >= 3 || (j == 3 && (j == 0 || j == 2))){
            return;
        }
        for(int d = 0; d < 8; d ++){
            int nextI = i + hori[d];
            int nextJ = j + vert[d];
            dfs(hori, vert, nextI, nextJ, n-1);
        }
    }
}
