package Graph.BFS.Flood;

import java.util.LinkedList;
import java.util.Queue;

public class FloodFill {
    // 做法: dfs要更快一点, 因为如果当前的位置不是startColor就直接return了, 时间是startColor的个数
    // Runtime: O(K), Space: O(1), k是startColor的个数

    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int m, n;
    public int[][] floodFill_dfs(int[][] image, int sr, int sc, int newColor) {
        int startC = image[sr][sc];
        m = image.length;
        n = image[0].length;
        if(startC == newColor) return image;
        dfs(image, sr, sc, newColor, startC);
        return image;
    }

    private void dfs(int[][] image, int i, int j, int newC, int startC){
        if(i < 0 || i >= m || j < 0 || j >= n || image[i][j] != startC) return;
        image[i][j] = newC;
        for(int[] dir : dirs){
            dfs(image, i + dir[0], j + dir[1], newC, startC);
        }
    }




    // 做法: 用的是简单的bfs, 每次都把周围的邻居变一下, 但好像有更好的办法
    // Runtime: O(n), Space: O(n)

    public int[][] floodFill_bfs(int[][] image, int sr, int sc, int newColor) {
        int m = image.length, n = image[0].length, startC = image[sr][sc];
        if(startC == newColor) return image;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sr, sc});
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                int[] cur = queue.poll();
                image[cur[0]][cur[1]] = newColor;
                for(int[] dir : dirs){
                    int nextI = cur[0] + dir[0];
                    int nextJ = cur[1] + dir[1];
                    if(nextI < m && nextI >= 0 && nextJ < n && nextJ >= 0 && image[nextI][nextJ] == startC) queue.offer(new int[]{nextI, nextJ});
                }
            }
        }
        return image;
    }
}
