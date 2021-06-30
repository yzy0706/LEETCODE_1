package Greedy.Intervals;

import java.util.HashMap;
import java.util.HashSet;

public class CinemaSeatAllocation {
    // 做法: 这里我用的是HashMap + greedy, 把每个row可能的三种组合标记为1, 2, 3, 最多是2*n种, 看情况减少
    // 1. 先记录每个row有哪些形式被reservedSeat破坏了:
    //   a. col在[2, 3]以内, 1号组合被破坏
    //   b. col在[4, 5]以内, 1, 2号组合被破坏
    //   c. col在[6, 7]以内, 2, 3号组合被破坏
    //   d. col在[8, 9]以内, 3号组合被破坏
    // 2. 检查HashMap里面的每个HashSet
    //   a. 如果HashSet.size() == 3, res -= 2;
    //   b. 如果HashSet.size() < 3且 > 0, 则不可能存在还有两个组合并存(因为就算一种组合被破坏也会重合), res --;

    // Runtime: O(n), Space: O(n);

    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        HashMap<Integer, HashSet<Integer>> invalid = new HashMap<>();
        for(int[] seat : reservedSeats){
            int row = seat[0], col = seat[1];
            invalid.putIfAbsent(row, new HashSet<>());
            HashSet<Integer> set = invalid.get(row);
            if(col >= 2 && col <= 3){
                set.add(1);
            }
            else if(col >= 4 && col <= 5){
                set.add(1);
                set.add(2);
            }
            else if(col >= 6 && col <= 7){
                set.add(2);
                set.add(3);
            }
            else if(col >= 8 && col <= 9){
                set.add(3);
            }
        }
        int res = 2 * n;
        for(int row : invalid.keySet()){
            HashSet<Integer> invalid4Pos = invalid.get(row);
            int size = invalid4Pos.size();
            if(size == 3){
                res -= 2;
            }
            else if(size > 0){
                res --;
            }
        }
        return res;
    }
}
