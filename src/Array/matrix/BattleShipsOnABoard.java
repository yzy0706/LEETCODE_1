package Array.matrix;

public class BattleShipsOnABoard {
    // 做法: 因为我们在matrix上浏览每一个位置是从左上角浏览每一行一直到右下角, 所以每到一个位置, 它的左边和上面是已经被浏览过的
    //  -> 所以我就每次通过检查每个'X'的左边或者上面是不是'X', 就知道当前位置是不是一个新战舰的开头 -> 所以通过这个方法我达到了O(1) Space and no editing的要求
    // Runtime: O(mn), Space: O(1)

    int res;
    public int countBattleships(char[][] board) {
        int m = board.length, n = board[0].length;
        res = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j] == 'X'){
                    if(i - 1 >= 0 && board[i-1][j] == 'X' || (j - 1 >= 0 && board[i][j-1] == 'X')) continue;
                    else res ++; //only count start of battle ship
                }
            }
        }
        return res;
    }
}
