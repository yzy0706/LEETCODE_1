package Graph.DFS.matrix;

import java.util.HashSet;

public class NumberOfDistinctIslands {
    // 做法: 其它都跟numIslands一样, 主要是为了记录相同的形状, 我们用一个额string来记录dfs走过的所有路程的方向, 出界了就append('e');
    // Runtime: O(mn), Space: O(mn)

    int m, n;
    public int numDistinctIslands(int[][] grid) {
        int res = 0;
        m = grid.length;
        n = grid[0].length;
        HashSet<String> shapes = new HashSet<>();
        for(int i = 0; i < m; i ++){
            for(int j = 0; j < n; j ++){
                if(grid[i][j] == 1){
                    StringBuilder path = new StringBuilder();
                    dfs(grid, i, j, path, ' ');
                    if(shapes.add(path.toString())) res ++;
                }
            }
        }
        return res;
    }

    public void dfs(int[][] grid, int i, int j, StringBuilder path, Character dir){
        if(i >= m || i < 0 || j >= n || j < 0 || grid[i][j] == 0){
            path.append('e'); //加一个'e'表示碰到墙了, 可以避免不同形状但相同移动方向的dfs被视为一个答案
            return;
        }
        grid[i][j] = 0;
        path.append(dir);
        dfs(grid, i + 1, j, path, 'd');
        dfs(grid, i - 1, j, path, 'u');
        dfs(grid, i, j + 1, path, 'r');
        dfs(grid, i, j - 1, path, 'l');
    }
}
