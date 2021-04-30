package Greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class TwoCitiesScheduling {
    // 做法: 这题我用pq做的, 相当于是greedy的做法, 先把去B城市比去A城市贵最多的n个人送去A城市, 然后剩下的去B城市
    // Runtime: O(nlog(n)), Space: O(nlog(n));

    public int twoCitySchedCost(int[][] costs) {
        int len = costs.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>(len/2, (a, b) -> (a[1] - a[0]) - (b[1] - b[0]));
        List<int[]> toBs = new ArrayList<>();
        for(int[] cost : costs){
            pq.offer(cost);
            if(pq.size() > len/2) toBs.add(pq.poll());
        }
        int res = 0;
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            res += cur[0];
        }
        for(int[] toB : toBs) res += toB[1];
        return res;
    }

    // 做法: 还有一个办法也是greedy,
    //1. 先假设都去A城市, 然后用一个int[] toBRefund保存去B城市和去A城市的差, 用res存储当前都去A的价格
    //2. 然后把toBRefund按照从小到大的顺序sort一下, 然后res += 前n个去B城市的refund
    // Runtime:O(nlog(n)), Space: O(2n), 2n = len;

    public int twoCitySchedCost_refund(int[][] costs) {
        int len = costs.length;
        int[] toBRefund = new int[len];
        int res = 0;
        for(int i = 0; i < len; i++){
            int[] cost = costs[i];
            res += cost[0];
            toBRefund[i] = cost[1] - cost[0];
        }
        Arrays.sort(toBRefund);
        for(int i = 0; i < len/2; i++){
            res += toBRefund[i];
        }
        return res;
    }
}
