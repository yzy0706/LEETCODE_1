package Graph.DFS.matrix;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestBridge {
    // 做法:
    // 1. 先用dfs把一个岛的位置全部标记成2
    // 2. 再用bfs和boolean[][], 从所有的2的位置开始, 一层层向对方的岛浏览, 每浏览一层res++
    // 注意： 平常涉及到要计算步数或者要用boolean[][]记录访问过的位置的题最好还是用bfs， 因为dfs在recursion的过程中很容易因为引用传导而提前修改boolean[][] visited

    //Runtime: O(mn), Space: O(mn);

    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int res, m, n;
    public int shortestBridge_dfs_bfs(int[][] A) {
        m = A.length;
        n = A[0].length;
        res = Integer.MAX_VALUE;
        boolean marked = false;
        for(int i = 0; i < m; i ++){
            if(marked) break;
            for(int j = 0; j < n; j++){
                if(!marked && A[i][j] == 1){
                    mark(A, i, j);
                    marked = true;
                }
            }
        }
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(A[i][j] == 2){
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }
        int res = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                int[] cur = queue.poll();
                for(int[] dir : dirs){
                    int nextI = cur[0] + dir[0], nextJ = cur[1] + dir[1];
                    if(nextI < 0 || nextI >= m || nextJ < 0 || nextJ >= n || visited[nextI][nextJ]) continue;
                    if(A[nextI][nextJ] == 1) return res;
                    visited[nextI][nextJ] = true;
                    queue.offer(new int[]{nextI, nextJ});
                }
            }
            res ++;
        }
        return res;
    }

    public void mark_bfs_dfs(int[][] A, int i, int j){
        if(i < 0 || i >= m || j < 0 || j >= n || A[i][j] == 0 || A[i][j] == 2) return;
        A[i][j] = 2;
        for(int[] dir : dirs){
            mark(A, i + dir[0], j + dir[1]);
        }
    }





    //第一遍只用了dfs
    public int shortestBridge(int[][] A) {
        m = A.length;
        n = A[0].length;
        res = Integer.MAX_VALUE;
        boolean marked = false;
        for(int i = 0; i < m; i ++){
            if(marked) break;
            for(int j = 0; j < n; j++){
                if(!marked && A[i][j] == 1){
                    mark(A, i, j);
                    marked = true;
                }
            }
        }
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(A[i][j] == 2){
                    findDist(A, i, j, 0, true, new boolean[m][n]);
                    // if(i == 1 && j == 1) System.out.println(1 + " " + res);
                }
            }
        }
        return res;
    }

    public void mark(int[][] A, int i, int j){
        if(i < 0 || i >= m || j < 0 || j >= n || A[i][j] == 0 || A[i][j] == 2) return;
        A[i][j] = 2;
        for(int[] dir : dirs){
            mark(A, i + dir[0], j + dir[1]);
        }
    }

    public void findDist(int[][] A, int i, int j, int dist, boolean start, boolean[][] visited) {
        if (i < 0 || i >= m || j < 0 || j >= n || (!start && A[i][j] == 2) || visited[i][j]) {
            return;
        }
        if (A[i][j] == 1) {
            res = Math.min(res, dist - 1);
            return;
        }
        visited[i][j] = true;
        for (int[] dir : dirs) {
            boolean[][] next = new boolean[m][n];
            next = visited;
            findDist(A, i + dir[0], j + dir[1], dist + 1, false, visited);
        }
    }
}
