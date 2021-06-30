package Array.matrix.DFS;

public class DiagonalTraverse {
    //做法: 就是把整个过程在代码上用dfs模拟了一遍, 用up来代表向下还是向上的方向, 对角线到头了就u turn, 如果cnt == m * n就直接return
    // Runtime: O(mn), Space: O(mn);


    int cnt = 0;
    int m, n;
    public int[] findDiagonalOrder(int[][] mat) {
        m = mat.length;
        n = mat[0].length;
        int[] res = new int[m * n];
        dfs(mat, 0, 0, res, true);
        return res;

    }

    public void dfs(int[][] mat, int i, int j, int[] res, boolean up){
        if(cnt == m * n) return;
        res[cnt ++] = mat[i][j];
        if(up && (i - 1 < 0 || j + 1 >= n)){
            if(j < n - 1) dfs(mat, i, j + 1, res, !up);
            else dfs(mat, i + 1, j, res, !up);
        }
        else if(!up && (i + 1 >= m || j - 1 < 0)){
            if(i + 1 <= m - 1) dfs(mat, i + 1, j, res, !up);
            else dfs(mat, i, j + 1, res, !up);
        }
        else{
            if(up) dfs(mat, i - 1, j + 1, res, up);
            else dfs(mat, i + 1, j - 1, res, up);
        }

    }
}
