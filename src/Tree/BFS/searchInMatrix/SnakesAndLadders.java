package Tree.BFS.searchInMatrix;

import java.util.LinkedList;
import java.util.Queue;

public class SnakesAndLadders {
    //做法: 这题就是每一个num能move最多六步, 然后如果当前移动到的位置des不是-1就把des添加到queue里面进行bfs
    // 1. 建立一个forloop, move从0开始一直到queue变成empty
    // 2. 建立一个forloop, (int size = queue.size(); size > 0; size--), 因为这里不是要像levelorder一样一次性浏览完所有的queue里面的位置再去添加新的位置所以我们不用先把queue的大小计算出来
    // 3. forloop 当前 num移动以后所有可能, 并计算他的位置上的数看是不是-1, 不是的话就加到queue里

    //Runtime: O(n^2), Space: O(n^2)


    public int snakesAndLadders(int[][] board) {
        int len = board.length;
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[len * len + 1];
        queue.offer(1);
        for(int move = 0; !queue.isEmpty(); move++){
            for(int size = queue.size(); size > 0; size--){
                int num = queue.poll();
                if(visited[num]) continue;
                if(num == len * len) return move;
                visited[num] = true;
                for(int jump = 1; jump <= 6 && num + jump <= len * len; jump++){
                    int des = num + jump;
                    int val = getPos(board, des);
//                    if(val != -1 && !visited[val]) queue.offer(val);
                    if(val != -1) des = val;
                    //如果跳跃到的位置不是-1的话才把des变为val, 表示可以跳跃, 不然的话des就是原来jump的数, 移动过去以后再继续一格一格移动
                    if(!visited[des]) queue.offer(des);
                }
            }
        }
        return -1;
    }

    private int getPos(int[][] board, int des){
        int len = board.length;
        int rowBelow = (des - 1)/len; //计算出当前的数跟matrix的最底端相隔几个row
        int row = len - 1 - rowBelow; //当前这个des应该在board里的位置
        int col = rowBelow % 2 == 0? des - rowBelow * len -  1 : (rowBelow + 1) * len - des; //根据当前的行数不同分别讨论怎么拿des求现在的col
        return board[row][col];
    }
}
