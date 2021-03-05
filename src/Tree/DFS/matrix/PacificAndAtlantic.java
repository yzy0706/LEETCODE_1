package Tree.DFS.matrix;

import java.util.ArrayList;
import java.util.List;

public class PacificAndAtlantic {
    int m, n;
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> res = new ArrayList<>();
        int m = matrix.length;
        if(m < 1) return res;
        int n = matrix[0].length;
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];
        for(int i = 0; i < m; i++){
            dfs(matrix, i, 0, pacific);
            dfs(matrix, i, n-1, atlantic);
        }
        for(int j = 0; j < n; j++){
            dfs(matrix, 0, j, pacific);
            dfs(matrix, m-1, j, atlantic);
        }


        for(int i = 0; i < m; i ++){
            for(int j = 0; j < n; j++){
                if(pacific[i][j] && atlantic[i][j]){
                    List<Integer> temp = new ArrayList<>();
                    temp.add(i);
                    temp.add(j);
                    res.add(temp);
                }
            }
        }
        return res;
    }

    private void dfs(int[][] matrix, int i, int j, boolean[][] ocean){
        if(ocean[i][j]) {
            return;
        }
        ocean[i][j] = true;
        if(i+1 < m && matrix[i][j] <= matrix[i+1][j]) dfs(matrix, i+1, j, ocean);
        if(i-1 >= 0 && matrix[i][j] <= matrix[i-1][j]) dfs(matrix, i-1, j, ocean);
        if(j+1 < n && matrix[i][j] <= matrix[i][j+1]) dfs(matrix, i, j+1, ocean);
        if(j-1 >= 0 && matrix[i][j] <= matrix[i][j-1]) dfs(matrix, i, j-1, ocean);
    }



    int[] twoVisited;
    public List<List<Integer>> pacificAtlantic_self(int[][] matrix) {
        List<List<Integer>> res = new ArrayList<>();
        int m = matrix.length;
        if(m < 1) return res;
        int n = matrix[0].length;

        for(int i = 0; i < m; i ++){
            for(int j = 0; j < n; j++){
                twoVisited = new int[2];
                dfs(matrix, i, j, matrix[i][j]);
                if(twoVisited[0] == 1 && twoVisited[1] == 1){
                    List<Integer> temp = new ArrayList<>();
                    temp.add(i);
                    temp.add(j);
                    res.add(temp);
                }
            }
        }
        return res;
    }

    private void dfs(int[][] matrix, int i, int j, int last){
        if(i < 0 || j < 0) {
            twoVisited[0] = 1; //pacific
            return;
        }
        else if(i >= m || j >= n){
            twoVisited[1] = 1; //atlantic
            return;
        }
        int h = matrix[i][j];
        if(h > last){
            return;
        }
        dfs(matrix, i+1, j, h);
        dfs(matrix, i-1, j, h);
        dfs(matrix, i, j+1, h);
        dfs(matrix, i, j-1, h);
    }
}
