package Backtrack;

public class WordSearch {
    // 做法: 这题融合了dfs和backtrack, 重点是怎么样去避免从一个位置延伸以后又返回到这个位置来
    // 方法是:
    // 1. 当dfs的helper每次浏览到一个位置时, 把当前这个位置设置成‘#’, 然后进行下一步的dfs拓展, 这样因为当前的位置是'#'肯定不属于word里面的字符所以不可能再回来
    // 2. dfs完四个方向以后再把当前的位置恢复到board[i][j] = word.charAt(pos); 因为char[][]是引用参数

    //Runtime: O(mn), Space: O(mn)

    int m, n;
    public boolean exist_dfs_backtrack(char[][] board, String word) {
        m = board.length; n = board[0].length;
        for(int i = 0; i < m; i ++){
            for(int j = 0; j < n; j++){
                if(dfs(board, word, 0, i, j)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean dfs(char[][] board, String word, int pos, int i, int j){
        if(pos >= word.length()){
            return true;
        }
        int[] curPos = new int[]{i, j};
        if(i < 0 || i >= m || j < 0 || j >= n || board[i][j] != word.charAt(pos)){
            return false;
        }
        board[i][j] = '#';
        int[] rowOffsets = {0, 1, 0, -1};
        int[] colOffsets = {1, 0, -1, 0};
        for(int a = 0; a < rowOffsets.length; a++){
            if(dfs(board, word, pos+1, i + rowOffsets[a], j + colOffsets[a])){
                return true;
            }
        }
        // boolean res = dfs(board, word, pos+1, i+1, j) || dfs(board, word, pos+1, i-1, j) || dfs(board, word, pos+1, i, j+1) || dfs(board, word, pos+1, i, j-1);
        board[i][j] = word.charAt(pos);
        return false;
    }







    //看了答案以后修改的，主要是删了一个boolean[][]，如果浏览过了就改成'#',其他没太大区别
    public boolean exist_discussion2(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        char[] l = word.toCharArray();
        boolean[][] visited = new boolean[m][n];
        int pos = 0;

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(expand(board, l, 0, i, j, m, n, visited)) return true;
            }
        }
        return false;
    }

    public boolean expand(char[][] board, char[] l, int pos, int i, int j, int m, int n, boolean[][] visited){
        if(pos>= l.length) {
            return true;
        }
        if(i < 0 || i == m || j < 0 || j == n || board[i][j] != l[pos]){
            return false;
        }
        // visited[i][j] = true;
        boolean res = false;
        board[i][j] ='#';
        int[] vertical = {1, 0, -1, 0};
        int[] horizontal = {0, 1, 0, -1};

        for(int a = 0; a < 4; a++){
            res = (expand(board, l, pos+1, i+vertical[a], j+horizontal[a], m, n, visited));
            if(res) break;
        }
        board[i][j] = l[pos];
        return res;
    }














    //leetcode给的答案
    private char[][] board;
    private int ROWS;
    private int COLS;

    public boolean exist_discussion1(char[][] board, String word) {
        this.board = board;
        this.ROWS = board.length;
        this.COLS = board[0].length;

        for (int row = 0; row < this.ROWS; ++row)
            for (int col = 0; col < this.COLS; ++col)
                if (this.backtrack(row, col, word, 0))
                    return true;
        return false;
    }

    protected boolean backtrack(int row, int col, String word, int index) {
        /* Step 1). check the bottom case. */
        if (index >= word.length())
            return true;

        /* Step 2). Check the boundaries. */
        if (row < 0 || row == this.ROWS || col < 0 || col == this.COLS
                || this.board[row][col] != word.charAt(index))
            return false;

        /* Step 3). explore the neighbors in Tree.DFS */
        boolean ret = false;
        // mark the path before the next exploration
        this.board[row][col] = '#';

        int[] rowOffsets = {0, 1, 0, -1};
        int[] colOffsets = {1, 0, -1, 0};
        for (int d = 0; d < 4; ++d) {
            ret = this.backtrack(row + rowOffsets[d], col + colOffsets[d], word, index + 1);
            if (ret)
                break;
        }

        /* Step 4). clean up and return the result. */
        this.board[row][col] = word.charAt(index);
        return ret;
    }







    //第一遍自己用dfs写的
    public boolean exist_firstTry(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        char[] l = word.toCharArray();
        boolean[][] visited = new boolean[m][n];
        int pos = 0;

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(expand(board, l, 0, i, j, visited)) return true;
            }
        }
        return false;
    }

    public boolean expand(char[][] board, char[] l, int pos, int i, int j, boolean[][] visited){
        if(pos == l.length - 1) {
            visited[i][j] = false;
            return true;
        }
        if(!visited[i][j] &&  board[i][j] == l[pos] ){
            visited[i][j] = true;
            return expand(board, l, pos+1, i-1, j, visited) && expand(board, l, pos+1, i+1, j, visited) && expand(board, l, pos+1, i, j+1, visited) && expand(board, l, pos+1, i, j-1, visited);
        }
        return false;
    }
}
