package Array.matrix;

public class spiralMatrix_mock {
    //leetcode给的解法：dir = (dir+1) % 4;
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if(matrix.length < 1) return res;
        int w = matrix.length, l = matrix[0].length;
        int[] vert = new int[]{0, 1, 0, -1}, hori = new int[]{1, 0, -1, 0};
        boolean[][] visited = new boolean[w][l];
        int i = 0, j = 0, dir = 0;

        for(int a = 0; a < w*l; a++){
            res.add(matrix[i][j]);
            visited[i][j] = true;
            int tmpI = i + vert[dir], tmpJ = j + hori[dir];
            if(tmpI >= 0 && tmpI < w && tmpJ >= 0 && tmpJ < l && !visited[tmpI][tmpJ]){
                i = tmpI;
                j = tmpJ;
            }
            else{
                dir = (dir + 1) % 4;
                i += vert[dir];
                j += hori[dir];
            }
        }

        return res;
    }






    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        boolean[][] visited = new boolean[n][n];
        int cnt = 1, x = 0 , y = 0, edge = n;
        while(cnt <= n*n){
            matrix[x][y] = cnt;
            visited[x][y] = true;
            cnt++;
            if(x+1< n && !visited[x+1][y]) x++;
            else{
                if(y+1 < n && !visited[x][y+1]) y++;
                else{
                    if(x-1 >= 0 && !visited[x-1][y]) x--;
                    else{
                        if(y-1 > 0 && !visited[x][y-1]) y--;
                    }
                }
            }

        }
        return matrix;
    }

}
