package Array.matrix.DFS;

public class MaxAreaOfIsland {
    //做法: 就是用了和NumberOfIslands一样的DFS做法
    //只是要注意一点: 如果我们想用一个void类型的DFS来修改公用参数, 那么我们不要把公用参数当作这个dfs方程的parameter, 因为不是array这种引用参数,
    //把当前island的面积大小当作parameter的修改是没法共享的

    //Runtime: O(mn), space: O(mn);
    public int w;
    public int l;
    public int cnt;
    public int maxAreaOfIsland(int[][] grid) {
        if(grid.length < 1) return 0;
        int res = 0;
        w = grid.length;
        l = grid[0].length;
        boolean[][] visited = new boolean[w][l];

        for(int i = 0; i < w; i++){
            for(int j = 0; j < l; j++){
                cnt = 0;
                search(grid, visited, i, j);
                res = Math.max(res, cnt);
            }
        }
        return res;
    }

    private void search(int[][] grid, boolean[][] visited, int i, int j){
        if(i < 0 || i >= w || j < 0 || j >= l || visited[i][j] || grid[i][j] == 0){
            return;
        }
        cnt ++;
        visited[i][j] = true;
        search(grid, visited, i+1, j);
        search(grid, visited, i-1, j);
        search(grid, visited, i, j+1);
        search(grid, visited, i, j-1);
    }
}
