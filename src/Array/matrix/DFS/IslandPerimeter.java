package Array.matrix.DFS;

public class IslandPerimeter {
    //做法: 这题基本跟NumberOfIslands一样, 但因为我们计算的是一共有多少条边长, 所以我们不是在碰到1的时候结果++,
    //而是在从1到0或者出界的时候, 正好跨过一条边, 所以cnt++;
    //而且返回访问过的1的那一条边是不算边界的, 所以我们不能把访问过的1设置为0, 而是应该设置为-1, 不然下一个1返回访问他的时候边长会+1是错误的
    //Runtime: O(mn), Space: O(1)
    private int w;
    private int l;
    private int cnt;
    public int islandPerimeter(int[][] grid) {
        if(grid.length < 1) return 0;
        w = grid.length;
        l = grid[0].length;
        for(int i = 0; i < w; i++){
            for(int j = 0; j < l; j++){
                if(grid[i][j] == 1){
                    search(grid, i, j);
                }
            }
        }
        return cnt;
    }

    private void search(int[][] grid, int i, int j){
        if(i < 0 || i >= w || j < 0 || j >= l || grid[i][j] == 0){
            cnt++;
            return;
        }
        if(grid[i][j] == -1){
            return;
        }
        grid[i][j] = -1;
        search(grid, i + 1, j);
        search(grid, i - 1, j);
        search(grid, i, j + 1);
        search(grid, i, j - 1);

    }
}
