package Array.matrix.DFS;

public class TheMaze {
    int[][] dirs;
    int m, n;
    boolean[][] visited;


    // 做法: 这题本来是正常的带转向的dfs, 但因为球碰到了墙就要转向, 所以我们每次都在helper里一次性走到墙, 然后把墙前面这个点当做新的start的点来开始新的dfs
    // Runtime: O(n), Space: O(1)
    public boolean hasPath_2(int[][] maze, int[] start, int[] destination) {
        m = maze.length;
        n = maze[0].length;
        visited = new boolean[m][n];
        dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        return dfs(maze, start, destination);
    }

    private boolean dfs(int[][] maze, int[] start, int[] destination){
        int i = start[0], j = start[1];
        if(visited[i][j]) return false;
        if(i == destination[0] && j == destination[1]) return true;
        visited[i][j] = true;
        for(int[] dir : dirs){
            int r = i, c = j;
            if(dir[0] != 0){
                while(r + dir[0] >= 0 && r + dir[0] < m && maze[r + dir[0]][c] != 1) r += dir[0];
            }
            else{
                while( c + dir[1] >= 0 && c + dir[1] < n && maze[r][c + dir[1]] != 1) c += dir[1]; //一直走到尽头
            }
            if(dfs(maze, new int[]{r, c}, destination)) return true;
        }
        return false;
    }



    //第一遍我是拿dir作为标记的
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        m = maze.length;
        n = maze[0].length;
        visited = new boolean[m][n];
        dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        boolean res = false;
        for(int i = 0; i < 4; i++){
            res = res || dfs(maze, start[0], start[1], i, destination);
        }
        return res;
    }

    private boolean dfs(int[][] maze, int i, int j, int dir, int[] destination){
        if(visited[i][j]) return false;
        if(i < 0 || i >= m || j < 0 || j >= n || maze[i][j] == 1){
            int lastI = i - dirs[dir][0], lastJ = j - dirs[dir][1];
            if(lastI == destination[0] && lastJ == destination[1]) return true;
            boolean other2 = false;
            for(int a = 0; a < 4; a++){
                if(a != dir){
                    if(other2) return true;
                    other2 = dfs(maze, lastI, lastJ, a, destination);
                }
            }
            return false;
        }
        visited[i][j] = true;
        return dfs(maze, i + dirs[dir][0], j + dirs[dir][1], dir, destination);
    }
}
