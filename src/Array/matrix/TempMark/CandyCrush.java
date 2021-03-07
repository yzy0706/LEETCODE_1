package Array.matrix.TempMark;

public class CandyCrush {
    //做法: 这题也是拿一个-val去暂时标记将要被清为0的位置的标记状态题, 跟那个用0, 1, 2记录的题很像;
    //1. 在当前还没有进入下一步之前, 继续利用-val的绝对值, 向右和向下去传染之后其他的位置, 如果能传染当前就不是stable, stable = false;
    //2. 特别要注意如果当前这个位置已经是0了就直接跳过不然会进入死循环
    //3. 如果当前不是stable, 证明有位置需要掉到bottom, 然后我们再根据每一个column, 用bot标记底端, 从最下面开始碰到有不是0的位置就board[bot--][j] = board[i][j], 然后全部都掉下来了以后从bot开始到0的所有位置都是0
    //Runtime: O(mn), Space: O(mn)
    public int[][] candyCrush(int[][] board) {
        int m = board.length;
        if(m < 1) return board;
        int n = board[0].length;
        boolean stable = false;
        while(!stable){
            stable = true;
            for(int i = 0; i < m; i++){
                for(int j = 0; j < n; j++){
                    int val = Math.abs(board[i][j]);
                    if(val == 0) continue;
                    if(i < m - 2 && Math.abs(board[i+1][j]) == val && Math.abs(board[i+2][j]) == val){
                        stable = false;
                        board[i][j] = board[i+1][j] = board[i+2][j] = -val; //先变成-val好延伸到整个board, 然后全都消除完以后再把<0的变成0
                    }
                    if(j < n - 2 && Math.abs(board[i][j+1]) == val && Math.abs(board[i][j+2]) == val){
                        stable = false;
                        board[i][j] = board[i][j+1] = board[i][j+2] = -val;
                    }
                }
            }
            //移动整个board
            if(!stable){
                for(int j = 0; j < n; j++){
                    int bot = m - 1;
                    for(int i = m-1; i >= 0; i--){
                        if(board[i][j] > 0){
                            board[bot--][j] = board[i][j];
                        }
                    }
                    for(int i = bot; i >= 0; i--){
                        board[i][j] = 0;
                    }
                }
            }
        }
        return board;
    }
}
