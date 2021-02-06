package Greedy;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class meetingRoomsII {
    public int minMeetingRooms(int[][] intervals) {
        int room = 0;
        Queue<Integer> endTime = new PriorityQueue<>();
        Arrays.sort(intervals, (a,b) -> (a[0]-b[0]));
        for(int i = 0; i < intervals.length ; i++){
            if(endTime.isEmpty()){
                room++;
                endTime.offer(intervals[i][1]);
                continue;
            }
            int end = endTime.peek();
            if(end <= intervals[i][0]) endTime.poll();
            else if(room == endTime.size()) room++;
            endTime.add(intervals[i][1]);

        }
        return room;













//        int room = 0;
//        int start=0;
//        int end=0;
//        for(int i=0; i<intervals.length;i++){
//            if(intervals[i][1]>=end){
//                end=int[i][2];
//            }
//            if(intervals[i][2]<=start){
//                start=int[i][1];
//            }
//            if(intervals[i][1]<=start&&intervals[i][2]>start&&intervals[i][2]<=end){
//                room++;
//                start=intervals[i][1];
//            }
//            if(intervals[i][1]<=start&&intervals[i][2]>=end){
//                room++;
//                start=intervals[i][1];
//                end=intervals[i][2];
//            }
//            if(intervals[i][1]>=start&&intervals[i][2]<=end){
//                room++;
//            }
//            if(intervals[i][1]>=start&&intervals[i][2]>=end){
//                room++;
//                end=intervals[i][2];
//            }
//        }
//        return room;


    }
}
