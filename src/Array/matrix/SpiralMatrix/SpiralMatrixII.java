package Array.matrix.SpiralMatrix;

public class SpiralMatrixII {
    //做法: 这题就主要用到了一个dir来表示当前的方向, 用一个int[] vert, hori来分别表示i, j在不同的dir下的不同增加值
    // 1. 建立一个int[n][n] res, 建立一个vert和一个hori来代表不同方向的增加值 int[] vert = new int[]{0, 1, 0, -1}, hori = new int[]{1, 0, -1, 0};, 建立一个 boolean[n][n]来表示当前的位置有没有被访问过
    // 2. forloop a从1到n^2, 先赋值当前res[i][j] = a;
    //      a. 然后根据vert[dir]和hori[dir]求出下一步tmpI和tmpJ在不在界内并且没有访问过, 满足条件的话就直接进入下一轮
    //      b. 但如果不满足条件, 比如要出这个matrix或者撞到访问过的了就要转方向, dir = (dir + 1) % 4;  i += vert[dir];
    //            j += hori[dir];

    //Runtime: O(n^2), Space: O(n^2)
    public int[][] generateMatrix_fixed(int n) {
        int[][] res = new int[n][n];
        if(n<1) return res;
        int[] vert = new int[]{0, 1, 0, -1}, hori = new int[]{1, 0, -1, 0};
        boolean[][] visited = new boolean[n][n];
        int i = 0, j = 0, dir = 0;
        for(int a = 1; a <= n*n; a++){
            res[i][j] = a;
            visited[i][j] = true;
            int tmpI = i + vert[dir], tmpJ = j + hori[dir];
            //检查有没有到边界
            if(tmpI >= 0 && tmpI < n && tmpJ >= 0 && tmpJ < n && !visited[tmpI][tmpJ]){
                i = tmpI;
                j = tmpJ;
            }
            else{
                // 改方向
                dir = (dir+1) % 4;
                i += vert[dir];
                j += hori[dir];
            }
        }

        return res;
    }




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
