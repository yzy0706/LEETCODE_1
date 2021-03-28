package Tree.BFS.searchInMatrix;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathInBinaryMatrix {
// 做法: 用bfs做的, 以后在这种matrix里面进行拓展的题也可以考虑用bfs, dfs很容易TLE
    // 1. 用一个boolean[][] visited来记录当前的位置有没有访问过,  用一个正常的bfs结构的queue来记录当前所有的邻居, 用一个int[][] dirs来forloop表示移动
    // 2. 一层层(每次用queue.size()把queue浏览完)的浏览, 当前每一个点的邻居都用foreach loop dirs求出来, 如果在grid以内, visited[nextI][nextJ] = true; 如果位置还是0, 那么queue.offer(new int[]{nextI, nextJ})

    //Runtime: O(n^2), Space: O(n^2)

    public int shortestPathBinaryMatrix_BFS(int[][] grid) {
        int n = grid.length;
        if(grid[0][0] == 1 || grid[n-1][n-1] == 1) return -1;
        if(n == 1) return 1;
        boolean[][] visited = new boolean[n][n];
        int res = 1;
        visited[0][0] = true;
        int[][] dirs = {new int[]{1, 0}, new int[]{-1, 0}, new int[]{0, 1}, new int[]{0, -1}, new int[]{1, 1}, new int[]{1, -1}, new int[]{-1, 1}, new int[]{-1, -1}};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                int[] curPos = queue.poll();
                if(curPos[0] == n-1 && curPos[1] == n-1) return res;
                for(int[] dir : dirs){
                    int nextI = curPos[0] + dir[0];
                    int nextJ = curPos[1] + dir[1];
                    if(nextI < n && nextI >= 0 && nextJ < n && nextJ >= 0 && !visited[nextI][nextJ]){
                        visited[nextI][nextJ] = true;
                        if(grid[nextI][nextJ] == 0) queue.offer(new int[]{nextI, nextJ});
                    }
                }
            }
            res ++;
        }
        return -1;
    }




    //第一遍用dfs做的， 会TLE
    private int res = Integer.MAX_VALUE;
    private int n;
    public int shortestPathBinaryMatrix_DFS(int[][] grid) {
        n = grid.length;
        if(grid[0][0] == 1 || grid[n-1][n-1] == 1) return -1;
        expand(grid, 0, 0, 1);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    public void expand(int[][] grid, int i, int j, int cnt){
        if(i < 0 || i >= n || j < 0 || j >= n || grid[i][j] == 1){
            return;
        }
        if(i == n-1 && j == n-1) {
            res = Math.min(res, cnt);
            return;
        }
        int[][] dirs = {new int[]{1, 0}, new int[]{-1, 0}, new int[]{0, 1}, new int[]{0, -1}, new int[]{1, 1}, new int[]{1, -1}, new int[]{-1, 1}, new int[]{-1, -1}};
        grid[i][j] = 1;
        for(int[] dir : dirs){
            expand(grid, i + dir[0], j + dir[1], cnt + 1);
        }
        // grid[i][j] = 0;
    }
}
