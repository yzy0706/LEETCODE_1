package Array.matrix.DFS;

public class NumberOfIslands {
    // 做法: 经典题, 用的dfs, 访问过的地方都改成'0'就不用boolean[][]
    // Runtime: O(mn), Space: O(1);

    int res, m, n;
    public int numIslands_reviewed(char[][] grid) {
        m = grid.length;
        if(m < 1) return 0;
        n = grid[0].length;
        // boolean[][] visited = new boolean[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == '1'){
                    expand(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    public void expand(char[][] grid, int i, int j){
        if( i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == '0') return;
        grid[i][j] = '0';
        expand(grid,i+1,j);
        expand(grid,i-1,j);
        expand(grid,i,j+1);
        expand(grid,i,j-1);
    }




    //做法： DFS做法， 碰到1的位置就变成0， 省去了一个boolean[][], 每一次forloop到一个'1'dfs一遍， res++
    //Runtime: O(n),
    int length;
    int width;
    public int numIslands(char[][] grid){
        length = grid.length;
        if(length < 1 ) return 0;
        width = grid[0].length;
        if(width < 1) return  0;

        int res = 0;

        for(int i = 0 ; i < length ; i++){
            for(int j = 0; j < width ; j++){
                if(grid[i][j] == '1'){
                    search(grid,i,j);
                    res++;
                }
            }
        }

        return res;

    }


    public void search(char[][] grid, int i , int j){
        if(i < 0 || j < 0 || i >= length || j > width || grid[i][j] == '0') return;
        grid[i][j] = '0';
        search(grid,i+1,j);
        search(grid,i-1,j);
        search(grid,i,j+1);
        search(grid,i,j-1);
    }

}
