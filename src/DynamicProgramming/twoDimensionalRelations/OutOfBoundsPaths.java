package DynamicProgramming.twoDimensionalRelations;

public class OutOfBoundsPaths {

    //做法: 用一个2d的int[][] count来代表每个位置上的能出边界的路径的可能的个数
    // 1. forloop从0到N-1一共N步, 每次建立一个临时的int[][] temp = new int[m][n], 承接上一步的数据
    // 2. forloop row和column, 并且根据matrix的移动坐标建立下一个坐标nextR, nextC
    //      a. 如果nextR, nextC出边界了, res = (res + count[r][c]) % MOD;
    //      b. 如果还没有出边界, temp[nextR][nextC] = (temp[nextR][nextC] + count[r][c]) % MOD;
    //      c. 原来的2d dp count = temp
    // 3. return res;
    //  Runtime: O(mnN), Space: O(mn);

    public int findPaths_sol(int m, int n, int N, int i, int j) {
        if(N <= 0) return 0;
        int res = 0;
        final int MOD = 1000000007;
        int[][] count = new int[m][n];
        int[][] dir = {{0,1}, {0, -1}, {1, 0}, {-1, 0}};
        count[i][j] = 1;
        for(int step = 0; step < N; step ++){
            int[][] temp = new int[m][n];
            for(int r = 0; r < m; r++){
                for(int c = 0; c < n; c++){
                    for(int[] d : dir){
                        int nextR = r + d[0];
                        int nextC = c + d[1];
                        if(nextR < 0 || nextR >= m || nextC < 0 || nextC >= n){
                            res = (res + count[r][c]) % MOD;
                        }
                        else{
                            temp[nextR][nextC] = (temp[nextR][nextC] + count[r][c]) % MOD;
                        }
                    }
                }
            }
            count = temp;
        }
        return res;
    }





    public int findPaths(int m, int n, int N, int i, int j) {
        int res = 0;
        for(int r = Math.max(0, i - N); r < Math.min(m, i + N); r++){
            for(int c = Math.max(0, j - N); c < Math.min(n, j + N); c++){
                if(Math.abs(r - i) + Math.abs(c - j) <= N){
                    if(r == 0 || r == m - 1 || c == 0 || c == n - 1){
                        res++;
                    }
                }
            }
        }
        return res;
    }
}
