package Graph.BFS.matrix;

import java.util.LinkedList;
import java.util.Queue;

public class WallAndGates {
    // 做法: 第一遍用的bfs, bfs是从所有是门的位置开始的(在rooms上的位置为0), 用一个Queue<int[]>来储存所有的位置
    // 1. 先把所有是0的位置都放到queue里, 并且将初始的距离也放到
    // 2. 然后用bfs浏览更新当前的位置, 并且加入邻居的方法一步步往外扩展, 当碰到下一个的位置出界了, 或者下一个位置值是0或者-1, 或者下一个邻居的值比当前位置还要小, 就不用加到queue里了

    //这一遍在加入邻居的时候就已经判断并且修改完邻居了, 这样快了很多, 避免同一层有不同的点的相同的邻居被加到queue里了

    // Runtime: O(mn), Space: O(mn)

    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public void wallsAndGates_reviewed_faster(int[][] rooms) {
        int m = rooms.length, n = rooms[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for(int i = 0; i < m; i ++){
            for(int j = 0; j < n; j++){
                if(rooms[i][j] == 0) queue.offer(new int[]{i, j});
            }
        }
        int step = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            step ++;
            for(int i = 0; i < size; i++){
                int[] cur = queue.poll();
                int x = cur[0], y = cur[1];
                for(int[] dir : dirs){
                    int nextX = x + dir[0], nextY = y + dir[1];
                    if(nextX < 0 || nextX >= m || nextY < 0 || nextY >= n || rooms[nextX][nextY] != Integer.MAX_VALUE) continue;
                    rooms[nextX][nextY] = step;
                    queue.offer(new int[]{nextX, nextY});
                }
            }
        }
    }



    //稍微修改了一下邻居的加入条件
    public void wallsAndGates_reviewed_bfs(int[][] rooms) {
        int m = rooms.length, n = rooms[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for(int i = 0; i < m; i ++){
            for(int j = 0; j < n; j++){
                if(rooms[i][j] == 0) queue.offer(new int[]{i, j, 0});
            }
        }
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                int[] cur = queue.poll();
                int x = cur[0], y = cur[1], dist = cur[2];
                if(dist < rooms[x][y]) rooms[x][y] = dist;
                for(int[] dir : dirs){
                    int nextX = x + dir[0], nextY = y + dir[1];
                    if(nextX < 0 || nextX >= m || nextY < 0 || nextY >= n || rooms[nextX][nextY] != Integer.MAX_VALUE) continue;
                    queue.offer(new int[]{nextX, nextY, dist + 1});
                }
            }
        }
    }



    // 做法: 第一遍用的bfs, bfs是从所有是门的位置开始的(在rooms上的位置为0), 用一个Queue<int[]>来储存所有的位置
    // 1. 先把所有是0的位置都放到queue里, 并且将初始的距离也放到
    // 2. 然后用bfs浏览更新当前的位置, 并且加入邻居的方法一步步往外扩展, 当碰到下一个的位置出界了, 或者下一个位置值是0或者-1, 或者下一个邻居的值比当前位置还要小, 就不用加到queue里了

    // Runtime: O(mn), Space: O(mn)
    public void wallsAndGates_bfs(int[][] rooms) {
        int m = rooms.length, n = rooms[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for(int i = 0; i < m; i ++){
            for(int j = 0; j < n; j++){
                if(rooms[i][j] == 0) queue.offer(new int[]{i, j, 0});
            }
        }
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                int[] cur = queue.poll();
                int x = cur[0], y = cur[1], dist = cur[2];
                if(dist < rooms[x][y]) rooms[x][y] = dist;
                for(int[] dir : dirs){
                    int nextX = x + dir[0], nextY = y + dir[1];
                    if(nextX < 0 || nextX >= m || nextY < 0 || nextY >= n || rooms[nextX][nextY] == -1 || rooms[nextX][nextY] == 0) continue;
                    if(rooms[nextX][nextY] < dist + 1) continue;
                    queue.offer(new int[]{nextX, nextY, dist + 1});
                }
            }
        }
    }
}
