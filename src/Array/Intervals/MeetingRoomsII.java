package Array.Intervals;

import java.util.*;

public class MeetingRoomsII {
    // 做法: 后面我自己用pq做的, 我根据所有会议结束的时间在pq里排列所有的会议, 让当前的interval尽量和结束时间跟自己开始时间最接近的interval合并, 如果pq里根本就没有结束时间比当前会议开始时间早的会议, 那么肯定就要新开一个会议, 所以res++;
    // Runtime: O(n^2), Space: O(n)

    public int minMeetingRooms_Reviewed(int[][] intervals) {
        int len = intervals.length;
        if(len == 1) return 1;
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[1] - b[1]));
        pq.offer(intervals[0]);
        int res = 1;
        for(int i = 1; i < len; i++){
            int[] cur = intervals[i];
            List<int[]> temp = new ArrayList<>();
            while(!pq.isEmpty() && cur[0] >= pq.peek()[1]){
                temp.add(pq.poll());
            }
            if(temp.size() == 0){
                res ++;
                pq.offer(cur);
                continue;
            }
            int[] closest = temp.remove(temp.size() - 1);
            closest[1] = cur[1];
            temp.add(closest);
            pq.addAll(temp);
        }
        return res;
    }



    //关键就是把每一个房的开始和终止时间放在了两个表里并排序，如果开始时间一直>=结束时间就一直res--再++ ， 等于不变

    public int minMeetingRooms_2(int[][] intervals) {
        if(intervals.length < 1) return 0;
        int res = 0;
        int[] start = new int[intervals.length], end  = new int[intervals.length];
        for(int i = 0; i < intervals.length ; i++){
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }
        Arrays.sort(start);
        Arrays.sort(end);
        int sPointer = 0, ePointer  = 0;
        while(sPointer < intervals.length){
            if(start[sPointer] >= end[ePointer]){
                res --;
                ePointer ++;
            }
            res ++;
            sPointer ++;
        }

        return res;
    }





    //第一遍自己写的
    class com implements Comparator<int[]> {
        public int compare(int[] a, int[] b){
            return a[0] - b[0];
        }
    }

    public int minMeetingRooms(int[][] intervals) {
        if (intervals.length < 1) return 0;
        int res = intervals.length;
        Arrays.sort(intervals, new com());
        for(int i = 1; i < intervals.length ; i ++){
            if(intervals[i][0] >= intervals[i-1][1] || (intervals[i][0] == intervals[i-1][1] && intervals[i][1] <= intervals[i+1][0])) res--;
        }

        return res;
    }

}
