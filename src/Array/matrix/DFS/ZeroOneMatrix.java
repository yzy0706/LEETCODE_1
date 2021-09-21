package Array.matrix.DFS;

import java.util.LinkedList;
import java.util.Queue;

public class ZeroOneMatrix {

    //第四遍从每个0开始做bfs
    public int[][] updateMatrix_bfs_from_0(int[][] mat) {
        m = mat.length;
        n = mat[0].length;
        int[][] res = new int[m][n];
        for(int i = 0; i < m; i ++){
            for(int j = 0; j < n; j ++){
                if(mat[i][j] != 0){
                    mat[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        for(int i = 0; i < m; i ++){
            for(int j = 0; j < n; j ++){
                if(mat[i][j] == 0){
                    Queue<int[]> q = new LinkedList<>();
                    q.offer(new int[]{i, j});
                    bfs(mat, res, q);
                }
            }
        }
        return res;
    }


    public void bfs(int[][] mat, int[][] res, Queue<int[]> q){
        int cnt = 0;
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        boolean[][] visited = new boolean[m][n];
        while(!q.isEmpty()){
            int size = q.size();
            for(int a = 0; a < size; a++){
                int[] top = q.poll();
                int i = top[0], j = top[1];
                visited[i][j] = true;
                if(res[i][j] < cnt){
                    res[i][j] = cnt;
                }
                else{
                    continue;
                }
                for(int[] dir : dirs){
                    int nextI = i + dir[0], nextJ = j + dir[1];
                    if(nextI < 0 || nextI >= m || nextJ < 0 || nextJ >= n){
                        continue;
                    }
                    if(visited[nextI][nextJ]){
                        continue;
                    }
                    else{
                        q.offer(new int[]{nextI, nextJ});
                    }
                }
            }
            cnt ++;
        }
    }




    // 第三遍bfs + dp
    int m, n;
    public int[][] updateMatrix_BFS_DP(int[][] mat) {
        m = mat.length;
        n = mat[0].length;
        int[][] res = new int[m][n];
        for(int i = 0; i < m; i ++){
            for(int j = 0; j < n; j ++){
                if(mat[i][j] != 0){
                    Queue<int[]> q = new LinkedList<>();
                    q.offer(new int[]{i, j});
                    bfs(mat, res, q, i, j);
                }
            }
        }
        return res;
    }

    public void bfs(int[][] mat, int[][] res, Queue<int[]> q, int I, int J){
        int cnt = 0;
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        boolean[][] visited = new boolean[m][n];
        visited[I][J] = true;
        while(!q.isEmpty()){
            int size = q.size();
            cnt ++;
            for(int a = 0; a < size; a++){
                int[] top = q.poll();
                int i = top[0], j = top[1], tempMax = Integer.MAX_VALUE;
                for(int[] dir : dirs){
                    int nextI = i + dir[0], nextJ = j + dir[1];
                    if(nextI < 0 || nextI >= m || nextJ < 0 || nextJ >= n){
                        continue;
                    }
                    if(mat[nextI][nextJ] == 0){
                        res[I][J] = cnt;
                        return;
                    }
                    if(res[nextI][nextJ] != 0){
                        tempMax = Math.min(tempMax, cnt + res[nextI][nextJ]);
                    }
                    if(!visited[nextI][nextJ]){
                        q.offer(new int[]{nextI, nextJ});
                        visited[nextI][nextJ] = true;
                    }
                }
                if(tempMax != Integer.MAX_VALUE){
                    res[I][J] = tempMax;
                    return;
                }
            }
        }
    }




    // 第二遍bfs， TLE
    public int[][] updateMatrix_BFS(int[][] mat) {
        m = mat.length;
        n = mat[0].length;
        int[][] res = new int[m][n];
        for(int i = 0; i < m; i ++){
            for(int j = 0; j < n; j ++){
                if(mat[i][j] != 0){
                    Queue<int[]> q = new LinkedList<>();
                    q.offer(new int[]{i, j});
                    bfs(mat, res, q, new boolean[m][n], i, j);
                }
            }
        }
        return res;
    }

    public void bfs(int[][] mat, int[][] res, Queue<int[]> q, boolean[][] visited, int I, int J){
        int cnt = 0;
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        visited[I][J] = true;
        while(!q.isEmpty()){
            int size = q.size();
            cnt ++;
            for(int a = 0; a < size; a++){
                int[] top = q.poll();
                int i = top[0], j = top[1];
                for(int[] dir : dirs){
                    int nextI = i + dir[0], nextJ = j + dir[1];
                    if(nextI < 0 || nextI >= m || nextJ < 0 || nextJ >= n){
                        continue;
                    }
                    if(mat[nextI][nextJ] == 0){
                        res[I][J] = cnt;
                        return;
                    }
                    if(!visited[nextI][nextJ]){
                        q.offer(new int[]{nextI, nextJ});
                        visited[nextI][nextJ] = true;
                    }
                }
            }
        }
    }




    //第一遍用dfs没成功
    public int[][] updateMatrix(int[][] mat) {
        m = mat.length;
        n = mat.length;
        int[][] res = new int[m][n];
        for(int i = 0; i < m; i ++){
            for(int j = 0; j < n; j++){
                if(mat[i][j] == 0){
                    res[i][j] = 0;
                }
                else{
                    dfs(res, mat, i + 1, j, i, j, 0);
                    dfs(res, mat, i - 1, j, i, j, 0);
                    dfs(res, mat, i, j + 1, i, j, 0);
                    dfs(res, mat, i, j - 1, i, j, 0);
                }
            }
        }
        return mat;
    }

    public void dfs(int[][] res, int[][] mat, int i, int j, int oI, int oJ, int cnt){
        if(i >= m || i < 0 || j >= n || j < 0){
            return;
        }
        if(mat[i][j] == 0){
            res[oI][oJ] = Math.min(res[oI][oJ], cnt);
            return;
        }
        res[oI][oJ] = Math.min(res[oI][oJ], cnt + res[i][j]);
        return;
    }
}
