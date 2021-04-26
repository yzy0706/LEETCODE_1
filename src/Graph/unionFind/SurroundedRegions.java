package Graph.unionFind;

import java.util.HashMap;
import java.util.HashSet;

public class SurroundedRegions {
    //做法: 第二种做法是UNF, 每个位置的ID都是 i * n + j, 所有在边界上的'O'
    // 1. 建立一个UNF unf, 所有'O'的位置parents[ID] = ID, parents[m*n] = m*n;
    // 2. forloop一遍所有的位置, 如果当前是'O':
    //      a. 如果当前在边界上, 设定所有的边界上的'O'的祖宗都是boardID = m * n;
    //      b. 如果不在边界上, 根据dirs的foreach loop检查四周的邻居的方法, 在unf里把当前位置作为是'O'的邻居的儿子 : unf.merge(nextID, ID)
    // 3. 连接完了以后, 再forloop一遍每个位置, 如果当前的位置的ID, unf.find(ID) != unf.find(borderID); 那么当前的位置可以变成'X'
    // 注意: 最后一步要我们要检查的是unf.find(ID)和unf.find(borderID)而不是直接比较unf.find(ID)和borderID, 因为
    // Runtime: O(mn), Space: O(mn)

    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    int m, n;
    public void solve_UNF(char[][] board) {
        m = board.length;
        n = board[0].length;
        if(m < 3 || n < 3) return;
        UNF unf = new UNF(board);
        int borderID = m*n;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j] == 'O'){
                    int ID = i * n + j;
                    if(i == 0 || i == m - 1 || j == 0 || j == n - 1){ // merge every border position to borderID
                        unf.merge(borderID, ID); // parents[find(ID)] = borderID;
                    }
                    else{
                        for(int[] dir : dirs){ // connect neigbour
                            int nextI = i + dir[0], nextJ = j + dir[1];
                            if(nextI >= 0 && nextI < m && nextJ >= 0 && nextJ < n && board[nextI][nextJ] == 'O'){
                                int nextID = nextI * n + nextJ;
                                unf.merge(nextID, ID);
                            }
                        }
                    }
                }
            }
        }

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j] == 'O'){
                    int ID = i * n + j;
                    if(unf.find(ID) != unf.find(borderID)){
                        board[i][j] = 'X';
                    }
                }
            }
        }


    }


    class UNF{
        int[] parents;
        public UNF(char[][] board){
            parents = new int[m*n + 1];
            parents[m*n] = m*n;
            for(int i = 0; i < m; i++){
                for(int j = 0; j < n; j++){
                    if(board[i][j] == 'O'){
                        int id = i * n + j;
                        parents[id] = id;
                    }
                }
            }
        }

        public int find(int id){
            while(parents[id] != id){
                parents[id] = parents[parents[id]];
                id = parents[id];
            }
            return id;
        }

        public void merge(int a, int b){
            int fa = find(a), fb = find(b);
            if(fa != fb){
                parents[fb] = parents[fa];
            }
        }
    }







    //做法: 第一天用的简单的dfs解法, 感觉有点时间过慢了, 用一个stable = new boolean[m][n]记录所有不能被改变的'O',
    // 1. 从边界的地方开始把所有的跟边界相连的'O'的位置都标记为true
    // 2. 再forloop一遍所有的位置, 把每一个stable[i][j] == false的'O'的位置, 检查四周, 如果出现不能改变的'O'这里就不能变为'X', 否则就变为'X'
    // Runtime: O(mn), Space: O(mn)
//    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
//    int m, n;
    public void solve_dfs(char[][] board) {
        m = board.length;
        n = board[0].length;
        if(m < 3 || n < 3) return;
        boolean[][] stable = new boolean[m][n];
        for(int i = 0; i < m; i++){
            if(board[i][0] == 'O'){
                dfs(board, i, 0, stable, true);
                stable[i][0] = true;
            }
            if(board[i][n-1] == 'O'){
                stable[i][n-1] = true;
                dfs(board, i, n-1, stable, true);
            }
        }
        for(int j = 0; j < n; j ++){
            if(board[0][j] == 'O'){
                stable[0][j] = true;
                dfs(board, 0, j, stable, true);
            }
            if(board[m-1][j] == 'O'){
                dfs(board, m-1, j, stable, true);
                stable[m-1][j] = true;
            }
        }
        for(int i = 1; i < m - 1; i++){
            for(int j = 1; j < n - 1; j++){
                if(board[i][j] == 'O'){
                    if(!stable[i][j]){
                        boolean change = true;
                        for(int[] dir : dirs){
                            int nextI = i + dir[0], nextJ = j + dir[1];
                            if(board[nextI][nextJ] == 'O' && stable[nextI][nextJ]){
                                change = false;
                            }
                        }
                        if(change) board[i][j] = 'X';
                    }
                }
            }
        }
    }


    public void dfs(char[][] board, int i, int j, boolean[][] stable, boolean start){
        if(!start) if(i <= 0 || i >= m-1 || j <= 0 || j >= n-1) return;
        if(start) start = false;
        stable[i][j] = true;
        if(board[i][j] == 'O'){
            for(int[] dir : dirs){
                int nextI = i + dir[0];
                int nextJ = j + dir[1];
                if(nextI >= 0 && nextI < m && nextJ >= 0 && nextJ < n) if(stable[nextI][nextJ]) continue;
                dfs(board, nextI, nextJ, stable, false);
            }
        }
    }

    // 第二遍拿hashset记录所有跟边界上的'O'相连的位置, 其实就是一种
    public void solve(char[][] board) {
        int m = board.length, n = board[0].length;
        if (m < 3 || n < 3) return;
        HashSet<int[]> connected = new HashSet<>();
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') connected.add(new int[]{i, 0});
            if (board[i][n - 1] == 'O') connected.add(new int[]{i, n - 1});
        }
        // System.out.println(connected.contains(new int[]{0, 0}));
        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O') connected.add(new int[]{0, j});
            if (board[m - 1][j] == 'O') connected.add(new int[]{m - 1, j});
        }
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (board[i][j] == 'O') {
                    for (int[] dir : dirs) {
                        int nextI = i + dir[0], nextJ = j + dir[1];
                        if (board[nextI][nextJ] == 'O') {
                            if (connected.contains(new int[]{nextI, nextJ})) {
                                connected.add(new int[]{i, j});
                            }
                        }
                    }
                }
            }
        }
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (board[i][j] == 'O' && !connected.contains(new int[]{i, j})) {
                    boolean surrounded = true;
                    for (int[] dir : dirs) {
                        int nextI = i + dir[0], nextJ = j + dir[1];
                        if (board[nextI][nextJ] == 'O' && connected.contains(new int[]{nextI, nextJ}))
                            surrounded = false;
                    }
                    if (surrounded) board[i][j] = 'X';
                }
            }
        }
    }




    // HashMap

    public void solve_1(char[][] board) {
        int m = board.length, n = board[0].length;
        if(m < 3 || n < 3) return;
        HashMap<int[], int[]> parents = new HashMap<>();
        for(int i = 0; i < m; i++){
            if(board[i][0] == 'O') parents.put(new int[]{i, 0}, new int[]{i, 0});
            if(board[i][n-1] == 'O') parents.put(new int[]{i, n-1}, new int[]{i, n-1});
        }
        for(int j = 0; j < n; j ++){
            if(board[0][j] == 'O') parents.put(new int[]{0, j}, new int[]{0, j});
            if(board[m-1][j] == 'O') parents.put(new int[]{m-1, j}, new int[]{m-1, j});
        }
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for(int i = 1; i < m - 1; i++){
            for(int j = 1; j < n - 1; j++){
                if(board[i][j] == 'O'){
                    for(int[] dir : dirs){
                        int nextI = i + dir[0], nextJ = j + dir[1];
                        if(board[nextI][nextJ] == 'O'){
                            if(parents.containsKey(new int[]{nextI, nextJ})){
                                parents.put(new int[]{i, j}, parents.get(new int[]{nextI, nextJ}));
                            }
                        }
                    }
                }
            }
        }
        for(int i = 1; i < m-1; i++){
            for(int j = 1; j < n-1; j++){
                if(board[i][j] == 'O' && parents.containsKey(new int[]{i, j})) board[i][j] = 'X';
            }
        }
    }
}
