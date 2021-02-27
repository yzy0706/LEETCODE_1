package TwoPointer.intervals;

import java.util.Arrays;
import java.util.Comparator;

public class MeetingRooms {
    //做法: 拿一个comparator把intervals按照会议开始的时间排列, 如果当前开始时间小于前一个结束的时间, 证明有重合, return false; 否则return true
    //Runtime: O(nlog(n)), space: O(1)
    public boolean canAttendMeetings(int[][] intervals) {
        Comparator<int[]> com = (o1, o2) -> {
            return o1[0] - o2[0];
        };
        Arrays.sort(intervals, com);
        if(intervals.length == 1) return true;
        for(int i = 1; i < intervals.length; i++){
            if(intervals[i][0] < intervals[i-1][1]) return false;
        }
        return true;
    }
}
