package heap;

public class meetingRoomsII {
    //关键就是把每一个房的开始和终止时间放在了两个表里并排序，如果开始时间一直>=结束时间就一直res -- 再++ ， 等于不变
    public int minMeetingRooms(int[][] intervals) {
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
            if(intervals[i][0] >= intervals[i-1][1] || (intervals[i][0] == intervals[i-1][1] && intervals[i][1] <= ]) res--;

        }

        return res;
    }

}
