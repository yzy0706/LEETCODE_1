package Heap.Queue.tailProblems_greedy;

import java.util.PriorityQueue;
import java.util.Queue;

public class furthestBuildingYouCanReach {
    //用pq去承载每一个>0的高度差，从0开始一直比较后面那个跟当前的高度差，大于0就加到pq里， 因为ladders是无敌的， 所以如果pq.size() > ladders
    //那么bricks -= pq.poll(),也就是当前最矮的阶梯用砖头， 如果bricks < 0了， 那就直接返回当前的i， 这个阶梯不用上去了， 否则还是一直循环， 最后return length-1
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
    Queue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i < heights.length - 1; i++){
        int diff = heights[i+1] - heights[i];
        if(diff > 0) pq.offer(diff);
        if(pq.size() > ladders) bricks -= pq.poll();
        if(bricks < 0) return i;
    }
        return heights.length-1;
}








    //第一遍自己写的， 太复杂， 但总体思路差不多
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> {
            if (heights[a + 1] - heights[a] == heights[b + 1] - heights[b]) {
                return a - b;
            }
            return heights[b + 1] - heights[b] - (heights[a + 1] - heights[a]);
        });
        for (int i = 0; i < heights.length - 1; i++) {
            if (heights[i + 1] > heights[i]) pq.offer(i);
        }
        if (pq.isEmpty()) return heights.length;
        int res = 0;
        while (!pq.isEmpty()) {


        }


    }
}