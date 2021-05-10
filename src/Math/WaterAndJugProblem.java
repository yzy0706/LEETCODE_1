package Math;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class WaterAndJugProblem {
    // 做法: bfs的做法, 就是用queue来储存每个时候当前两个杯子的水量之和, 然后用一个set去记录出现过的所有和
    // 1. curSum是当前queue poll()出来的待选的和
    //      a. 如果curSum就等于target, 直接return true
    //      b. 给curSum分别加上jug1和jug2的水量, 看他有没有超过最大水量的和, 并且看有没有出现过, 对应的就是fill的操作
    //      c. 给curSum分别减去jug1和jug2的水量, 看他是不是还大于0, 也在appeared里面检查一遍, 对应的就是empty的操作
    //      d. pour到另外一个杯子的操作不用管, 因为总量还是不变的

    // Runtime: O(m+n), Space: O(m+n);

    public boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {
        if(jug1Capacity + jug2Capacity < targetCapacity || targetCapacity < 0) return false;
        Set<Integer> appeared = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        while(!queue.isEmpty()){
            int curSum = queue.poll();
            if(curSum == targetCapacity) return true;
            if(curSum + jug1Capacity <= jug1Capacity + jug2Capacity && appeared.add(curSum + jug1Capacity)) queue.offer(curSum + jug1Capacity);
            if(curSum + jug2Capacity <= jug1Capacity + jug2Capacity && appeared.add(curSum + jug2Capacity)) queue.offer(curSum + jug2Capacity);
            if(curSum - jug1Capacity > 0 && appeared.add(curSum - jug1Capacity)) queue.offer(curSum - jug1Capacity);
            if(curSum - jug2Capacity > 0 && appeared.add(curSum - jug2Capacity)) queue.offer(curSum - jug2Capacity);
        }
        return false;
    }
}
