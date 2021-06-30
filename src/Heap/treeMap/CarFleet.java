package Heap.treeMap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class CarFleet {

    // 做法: 思路是没错, 就是拿一个pq或者treeMap, 按照他们开始的位置去排列所有的车, 但我一直在比较当前车能不能被上一辆车赶上, 其实可以直接记录每一个carFleet的时间, 然后看当前这辆车能不能赶上上一个carfleet
    // 1. 用一个pq储存所有的index, pq的comparator按照他们的position从大到小排列
    // 2. 对pq做whileloop, 初始值一个double lastFleetTime = - 1.0, 记录每一个carFleet的最大完成时间(也就是最靠前的那辆车的完成时间),     //     a. 如果当前pq.poll()出来的cur完成时间大于lastFleetTime, 那么证明当前赶不上上一个fleet了, 就新开一个fleet, lastFleetTime = finishTime; res ++;

    // Runtime: O(nlog(n)), Space: O(nlog(n))

    public int carFleet_3(int target, int[] position, int[] speed) {
        int n = position.length;
        if(n < 1) return 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> position[b] - position[a]);
        for(int i = 0; i < n; i ++) pq.offer(i);
        int res = 0;
        double lastFleetTime = - 1.0;
        while(!pq.isEmpty()){
            int cur = pq.poll();
            double finishTime = (target - position[cur])/ (double)speed[cur];
            if(finishTime > lastFleetTime){
                res ++;
                lastFleetTime = finishTime;
            }
        }
        return res;
    }



    // 还是去比较这个和上一个追赶的时间，还是不太对
    public int carFleet_2(int target, int[] position, int[] speed) {
        int n = position.length;
        if(n < 1) return 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> position[b] - position[a]);
        for(int i = 0; i < n; i ++) pq.offer(i);
        int res = 0;
        while(!pq.isEmpty()){
            int cur = pq.poll();
            res ++;
            while(!pq.isEmpty() && chase(cur, pq.peek(), target, position, speed)){
                pq.poll();
            }
        }
        return res;
    }

    public boolean chase(int cur, int prev, int target, int[] position, int[] speed){
        int curS = speed[cur], curPos = position[cur], prevS = speed[prev], prevPos = position[prev];
        if(curS >= prevS) return false;
        double chaseTime = (curPos - prevPos) /(prevS - curS);
        return curPos + chaseTime * curS <= target;
    }





    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        if(n < 1) return 0;
        int res = 0;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int i = 0; i < n; i ++) map.put(position[i], speed[i]);
        List<Integer> sortedPos = new ArrayList<>(map.keySet());
        boolean[] merged = new boolean[n];
        for(int i = 0; i < n; i ++){
            if(!merged[i]) res ++;
            if(i == n - 1) break;
            int pos = sortedPos.get(i);
            int curSpeed = map.get(pos);
            int ahead = map.higherKey(pos), aSpeed = map.get(ahead);
            if(aSpeed >= curSpeed) continue;
            int catchTime = (ahead - pos) / (curSpeed - aSpeed);
            if(pos + catchTime * curSpeed > target) continue;
            else merged[i + 1] = true;
        }
        return res;
    }
}
