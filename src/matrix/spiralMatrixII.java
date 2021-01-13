package matrix;

public class spiralMatrixII {
    //第一遍自己写的
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        if(n<1) return res;
        int[] vert = new int[]{0, 1, 0, -1}, hori = new int[]{1, 0, -1, 0};
        boolean[][] visited = new boolean[n][n];
        int i = 0, j = 0, dir = 0;
        for(int a = 0; a < n*n; a++){
            res[i][j] = a;
            visited[i][j] = true;
            int tmpI = i + vert[dir], tmpJ = j + hori[dir];
            //检查有没有到边界
            if(tmpI >= 0 && tmpI < n && tmpJ >= 0 && tmpJ < n && !visited[i][j]){
                i = tmpI;
                j = tmpJ;
            }
            else{
                // 改方向
                dir = (dir+1) % 4;
                i += hori[dir];
                j += vert[dir];
            }
        }
        return res;
    }
}
