package Graph.DFS.matrix;

public class NumberOfIslands {
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
