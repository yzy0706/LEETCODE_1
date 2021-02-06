package Array.matrix;

import java.util.LinkedList;
import java.util.Queue;

public class rottenOrange_mock {
    //答案拿bfs写的,主要就是按bfs的格式用一个queue去承载所有的rotten oranges，特别注意bfs的格式，在poll queue里面的东西之前
    //一定要拿一个int去确定好在这次清理queue之前queue的size，然后再根据每一个poll出来的rottenorange进入一个新循环感染别人
    public class pair{
        int x ,y, val;
        public pair(int x, int y, int[][] grid){
            this.x = x;
            this.y = y;
            this.val = grid[x][y];
        }

    }

    int time = 0;

    public int orangesRotting1(int[][] grid) {
        int len = grid.length, wid = grid[0].length, rotten = 0 , fresh = 0;
        if(len < 1 || wid < 1) return -1;
        Queue<pair> queueRotten = new LinkedList<pair>();
        // boolean[][] visited = new boolean[len][wid];

        for(int x = 0 ; x < len ; x++){
            for(int y = 0 ; y < wid ; y++){
                int value = grid[x][y];
                if(value == 2){
                    rotten++;
                    queueRotten.offer(new pair(x,y,grid));
                }

                else if(value == 1) fresh++;
            }
        }

        if(fresh == 0) return 0;
        else if(rotten == 0) return -1;




        while(!queueRotten.isEmpty()){
            time++;
            for(int i=0; i< queueRotten.size(); i++){
                pair cur = queueRotten.poll();
                if(expand(cur.x+1,cur.y,grid,queueRotten)) fresh--;
                if(expand(cur.x-1,cur.y,grid,queueRotten)) fresh--;
                if(expand(cur.x,cur.y+1,grid,queueRotten)) fresh--;
                if(expand(cur.x,cur.y-1,grid,queueRotten)) fresh--;
            }
        }



        return fresh == 0? time : -1;
    }


    public boolean expand(int x, int y, int[][] grid, Queue<pair> queueRotten){
        int len = grid.length, wid = grid[0].length;
        if(x < 0 || y < 0 || x >= len || y >= wid || grid[x][y] != 1) return false;
        grid[x][y] = 2;
        queueRotten.offer(new pair(x,y,grid));
        return true;

    }







    //自己用recursion写的

    int res = 0, pointer = 0;
    public int orangesRotting(int[][] grid) {
        int len = grid.length, wid = grid[0].length;
        if(len < 1 || wid < 1) return -1;
        boolean[][] visited = new boolean[len][wid];
        for(int x = 0 ; x < wid ; x++){
            for(int y = 0 ; y < len ; y++){
                // int cnt = 0;
                int value = grid[x][y];
                if(value == 1){
                    expand(x,y,grid,0);
                    if(pointer == 0) return -1;
                    if(pointer >= res) res = pointer;
                    pointer = 0;
                }
            }
        }
        return res;
    }

    public void expand(int x, int y, int[][] grid, int cnt){
        int len = grid.length, wid = grid[0].length;
        if(grid[x][y] == 2) pointer = cnt;
        if(x+1 >= wid || y+1 >= len)
        if(grid[x+1][y] != 2 && grid[x-1][y] != 2 && grid[x][y+1] != 2 && grid[x][y-1] != 2) pointer = 0;
        expand(x+1,y,grid,cnt+1);
        expand(x-1,y,grid,cnt+1);
        expand(x,y+1,grid,cnt+1);
        expand(x,y-1,grid,cnt+1);

    }
}



    //第三次dfs的解法
    int res = -1;
    public int orangesRotting(int[][] grid) {
        int w = grid.length;
        if(w < 1) return -1;
        int l = grid[0].length;
        for(int i = 0; i < w; i++){
            for(int j = 0; j < l; j++){
                if(grid[i][j] == 2){
                    expand(grid, i+1, j, 0, w, l);
                    expand(grid, i-1, j, 0, w, l);
                    expand(grid, i, j+1, 0, w, l);
                    expand(grid, i, j-1, 0, w, l);;
                }
            }
        }
        return res;
    }

    public void expand(int[][] grid, int i, int j, int curMin, int w, int l){
        if(i >= 0 && i < w && j >= 0 && j < w && grid[i][j] == 1){
            grid[i][j] = 2;
            curMin++;
            if(curMin > res) res = curMin;
            expand(grid, i+1, j, curMin, w, l);
            expand(grid, i-1, j, curMin, w, l);
            expand(grid, i, j+1, curMin, w, l);
            expand(grid, i, j-1, curMin, w, l);

        }
        else{
            return;
        }

    }








class pair{
    int i;
    int j;
    int val;
    public pair(int i, int j, int[][] grid){
        this.i = i;
        this.j = j;
        this.val = grid[i][j];
    }
}

class pair{
    int i;
    int j;
    int val;
    public pair(int i, int j, int[][] grid){
        this.i = i;
        this.j = j;
        this.val = grid[i][j];
    }
}

    public int orangesRotting(int[][] grid) {
        int w = grid.length;
        if(w < 1) return -1;
        int l = grid[0].length;
        Queue<pair> rottenPairs = new LinkedList<pair>();
        int fresh = 0;
        int rotten = 0;
        int res = 0;

        for(int i = 0; i < w; i++){
            for(int j = 0; j < l; j++){
                if(grid[i][j] == 2) {
                    rottenPairs.offer(new pair(i, j, grid));
                    rotten++;
                }
                else if(grid[i][j] == 1){
                    fresh++;
                }
            }
        }

        if(rotten == 0) return -1;
        if(fresh == 0) return 0;

        while(!rottenPairs.isEmpty()){
            res++;

            for(int a = 0; a < rottenPairs.size(); a++){
                pair cur = rottenPairs.poll();
                int i = cur.i;
                int j = cur.j;
                if(expand(grid, i+1, j, w, l, rottenPairs)) fresh--;
                if(expand(grid, i-1, j, w, l, rottenPairs)) fresh--;
                if(expand(grid, i, j+1, w, l, rottenPairs)) fresh--;
                if(expand(grid, i, j-1, w, l, rottenPairs)) fresh--;
            }
        }


        return fresh == 0? res : -1;

    }


    public boolean expand(int[][] grid, int i, int j, int w, int l, Queue<pair> rottenPairs){
        if(i < 0 || i >= w || j < 0 || j >= l || grid[i][j] != 1){
            return false;
        }
        grid[i][j] = 2;
        rottenPairs.offer(new pair(i, j, grid));
        return true;
    }





