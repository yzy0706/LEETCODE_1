package DynamicProgramming.matrix;

import java.util.Arrays;

public class LargestPlusSign {
    //做法:
    //1. 建立一个grid, initialize每个位置的值为一个很大的值, mines的每个位置都是0
    //2. 我们只要检查对角线上的每个点, 所以forloop i到N-1, 然后每次i的运动都用l, r, u, d分别记录四个方向到这个点的最长的距离, 然后更新grid在那个位置的值
    //Runtime: O(N^2), Space: O(N^2)
    public int orderOfLargestPlusSign(int N, int[][] mines) {
        int[][] grid = new int[N][N];
        for(int[] row : grid) Arrays.fill(row, Integer.MAX_VALUE);
        for(int[] mine : mines){
            grid[mine[0]][mine[1]] = 0;
        }

        for(int i = 0; i < N; i++){
            int l = 0, r = 0, u = 0, d = 0;
            for(int j = 0, k = N-1; j < N; j++, k--){
                //从最四个方向的最边界开始更新每一个位置的值, 每浏览一个对角线的位置就会更新一下四个方向, 每个纵轴和竖轴都会更新一次
                grid[i][j] = Math.min(grid[i][j], l = grid[i][j] == 0 ? 0 : l+1);
                grid[i][k] = Math.min(grid[i][k], r = grid[i][k] == 0 ? 0 : r+1);
                grid[j][i] = Math.min(grid[j][i], u = grid[j][i] == 0 ? 0 : u+1);
                grid[k][i] = Math.min(grid[k][i], d = grid[k][i] == 0 ? 0 : d+1);
            }
        }
        int res = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                // 因为碰到0就会停下来, 所以不能只查对角线上的位置
                res = Math.max(res, grid[i][j]);
            }
        }
        return res;
    }
}
