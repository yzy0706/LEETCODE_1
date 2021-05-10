package Graph.BFS.matrix;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class MinimumKnightMoves {
    //做法: 每个点的八个方向后的一步都加到bfs里, 并且要没有访问过, 如果bfs poll()出来到{x, y}了就return cnt;, 否则就一层层加上后一步并递推
    // Runtime: O(8^n), space: O(8^n)
    public int minKnightMoves(int x, int y) {
        x = Math.abs(x);
        y = Math.abs(y);

        if(x == 0 && y == 0) return 0;

        Queue<int[]> bfs = new LinkedList<>();
        bfs.offer(new int[]{0, 0});

        Set<String> visited = new HashSet<>();
        visited.add("0,0");

        int[] hori = new int[]{1, 2, 1, 2, -1, -2, -1, -2};
        int[] vert = new int[]{2, 1, -2, -1, -2, -1, 2, 1};

        int cnt = 0;

        while(!bfs.isEmpty()){
            int size = bfs.size();

            for(int i = 0; i < size; i++){
                int[] cur = bfs.poll();
                if(cur[0] == x && cur[1] == y){
                    return cnt;
                }

                for(int j = 0; j < 8; j++){ //每一个点的下面每一步
                    int tempX = cur[0] + hori[j];
                    int tempY = cur[1] + vert[j];
                    int[] next = new int[]{tempX, tempY};

                    if(tempX >= -1 && tempY >= -1 && !visited.contains(tempX + "," + tempY)){
                        bfs.offer(next);
                        visited.add(tempX + "," + tempY);
                    }
                }
            }

            cnt++ ;
        }

        return -1;
    }
    
}
