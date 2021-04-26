package Greedy.Intervals;

import java.util.*;

public class MergeIntervals {
    // 做法: Merge Interval的题我都喜欢用pq来做
    // 1. 把所有的interval放到pq里面, pq以interval的start来排列
    // 2. 然后再根据是去合并重合(merge interval)的还是不重合的(meeting room)来一个个poll出来pq里的int[], 比较top[1]和next[0]
    // Runtime: O(nlog(n)), Space: O(nlog(n))

    public int[][] merge(int[][] intervals) {
        if(intervals.length == 1) return intervals;
        List<int[]> res = new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for(int[] interval : intervals) pq.offer(interval);
        while(!pq.isEmpty()){
            int[] top = pq.poll();
            while(!pq.isEmpty() && top[1] >= pq.peek()[0]){
                int[] next = pq.poll();
                top[0] = Math.min(top[0], next[0]);
                top[1] = Math.max(top[1], next[1]);
            }
            res.add(top);
        }
        int[][] ans = new int[res.size()][2];
        for(int i = 0; i < res.size(); i++) ans[i] = res.get(i);
        return ans;
    }


    // sort + LinkedList的做法
    public int[][] merge_2(int[][] intervals) {
        int len = intervals.length;
        if(len == 1) return intervals;
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        LinkedList<int[]> res = new LinkedList<>();
        res.add(intervals[0]);
        for(int i = 1; i < len; i++){
            int[] cur = intervals[i];
            if(res.getLast()[1] < cur[0]) res.addLast(cur);
            else res.getLast()[1] = Math.max(res.getLast()[1], cur[1]);
        }
        // int[][] ans = new int[res.size()][2];
        // int pos = 0;
        // for(int[] a : res) ans[pos++] = a;
        return res.toArray(new int[res.size()][2]);
    }
}
