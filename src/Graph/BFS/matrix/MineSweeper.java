package Graph.BFS.matrix;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MineSweeper {
    //做法: 用的bfs
    // 1. 正常的bfs流程:从start的点开始, 一层层的浏览的所有的邻居, 用boolean[m][n]记录所有被浏览过的点
    // 2. 用List<int[]> 暂时储存所有的可以访问邻居, 这个题只有当一个位置没有一个neighbour是'M'或者'X'的时候, 它的所有neighbour才能加到queue里, 其它情况都不往它的邻居继续扩张, 只改变当前位置的值
    //注意:
    // 1. bfs的题要注意加入了相同的邻居会RTE, 所以最好都用boolean[m][n]来记录每个邻居有没有被访问过
    // 2. (char)(num + '0'); 可以把Integer转换成Character

    //Runtime: O(mn), Space: O(mn)

    int[][] dirs = {{1, 0},  {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    public char[][] updateBoard(char[][] board, int[] click) {
        int m = board.length, n = board[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{click[0], click[1]});
        boolean[][] visited = new boolean[m][n];
        visited[click[0]][click[1]] = true;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                int[] cur = queue.poll();
                int x = cur[0], y = cur[1], num = 0;
                if(board[x][y] == 'M'){
                    board[x][y] = 'X';
                    continue;
                }
                List<int[]> neighbours = new ArrayList<>();
                for(int[] dir : dirs){
                    int nx = x + dir[0], ny = y + dir[1];
                    if(nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                    if(board[nx][ny] == 'M' || board[nx][ny] == 'X') num ++;
                    if(board[nx][ny] == 'E' && !visited[nx][ny]) neighbours.add(new int[]{nx, ny});
                }
                if(num == 0){
                    for(int[] neighbour : neighbours) visited[neighbour[0]][neighbour[1]] = true;
                    queue.addAll(neighbours);
                    board[x][y] = 'B';
                }
                else{
                    board[x][y] = (char)(num + '0');
                }
            }
        }
        return board;
    }
}
