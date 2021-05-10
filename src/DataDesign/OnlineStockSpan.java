package DataDesign;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public class OnlineStockSpan {
    // 做法: Discussion里有一个类似于dp的解法:
    // 1. 拿Stack<int[]> 来储存之前的{price, 能走的最长长度}
    // 2. 然后当当前price >= stack.peek()[0]的时候都可以用 res += stack.poll()[1]; 也就是加上小于等于price的这个数能往前走的最长距离, 这就是一种利用之前结果以解决题目的dp逻辑
    // 3. 最后push进去当前的price和往前的最长能走的长度res

    // Runtime: O(n), Space:O(n);

    Stack<int[]> stack;
    public void StockSpanner_dp_stack() {
        stack = new Stack<>();
    }

    public int next_dp_stack(int price) {
        int res = 1;
        while(!stack.isEmpty() && price >= stack.peek()[0]){
            res += stack.pop()[1];
        }
        stack.push(new int[]{price, res});
        return res;
    }





    // 做法: 第一个是用list来储存之前所有出现过的数, 挺慢的
    // Runtime: O(n^2), Space:O(n);

    List<Integer> prevs;
    public void StockSpanner() {
        prevs = new ArrayList<>();
    }

    public int next(int price) {
        int res = 1;
        for(int i = prevs.size() - 1; i >= 0; i--){
            if(price >= prevs.get(i)) res++;
            else break;
        }
        prevs.add(price);
        return res;
    }



    // pq解法RTE
    PriorityQueue<int[]> pq;
    public void StockSpanner_pq() {
        pq = new PriorityQueue<>((a, b) -> (b[0] - a[0]));
    }

    public int next_pq(int price) {
        int index = pq.size(), res = pq.size() + 1;
        PriorityQueue<int[]> temp = new PriorityQueue<>(pq);
        while(!temp.isEmpty()){
            int[] cur = temp.poll();
            if(cur[0] > price) res = Math.min(res, index - cur[1]);
            if(cur[0] <= price || res == 1) break;
        }
        pq.offer(new int[]{price, index});
        return res;
    }
}
