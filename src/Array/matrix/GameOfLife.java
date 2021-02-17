package Array.matrix;

public class GameOfLife {
    //做法: 这题用了几个数字来代表nextstatus :
    // 1. 如果当前是0, 有三个neighbors代表可以复活, 则当前位置转换为3
    // 2. 如果当前是1, 有小于两个或者大于三个neighbors代表他要死了, 所以就变成2; 不死就维持原状
    // 最后再把matrix上所有的位置都 %= 2;

    // Runtime: O(mn), Space: O(1)

    public void gameOfLife_twoStatus(int[][] board) {
        if(board.length < 1) return;
        int w = board.length, l = board[0].length;
        for(int i = 0; i < w; i++){
            for(int j = 0; j < l; j++){
                int neighbour = 0;
                for(int x = Math.max(i-1, 0); x < Math.min(i+2, w); x ++){
                    for(int y = Math.max(j-1, 0); y < Math.min(j+2, l); y ++){
                        if(board[x][y] == 1 || board[x][y] == 2) neighbour ++;
                    }
                }
                neighbour -= board[i][j];
                if(board[i][j] == 1 && (neighbour < 2 || neighbour > 3)) board[i][j] = 2;  // = 2就死了, 但上一个状态还是活着
                if(board[i][j] == 0 && neighbour == 3) board[i][j] = 3; // = 3就复活过来了
            }
        }

        for(int i = 0; i < w; i++){
            for(int j = 0; j < l; j++){
                board[i][j] %= 2;
            }
        }
    }








    //第一遍用比较蠢的新建一个matrix来做
    public void gameOfLife(int[][] board) {
        int w = board.length, l = board[0].length;
        int[][] res = new int[w][l];
        for(int i = 0; i < w; i++){
            for(int j = 0; j < l; j++){
                res[i][j] = next(board, i, j);
            }
        }
        board = res;
    }

    private int next(int[][] board, int i, int j){
        int cnt = 0;
        int[] dx = new int[]{1, -1, 0};
        int[] dy = new int[]{1, -1, 0};

        for(int di = 0; di < dx.length; di++){
            for(int dj = 0; dj < dy.length; dj++){
                if(dx[di] == 0 && dx[di] == 0) continue;
                int nextI = i + dx[di];
                int nextJ = j + dy[dj];
                if(nextI < 0 || nextI >= board.length || nextJ < 0 || nextJ >= board[0].length) continue;
                else if(board[nextI][nextJ] == 1) cnt ++;
            }
        }

        if(board[i][j] == 1){
            if(cnt < 2 || cnt > 3) return 0;
            else return 1;
        }

        return cnt == 3 ? 1 : 0;
    }
}
