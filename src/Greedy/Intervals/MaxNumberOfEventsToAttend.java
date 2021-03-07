package Greedy.Intervals;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class MaxNumberOfEventsToAttend {
    //做法: 主要是用一个pq ends来记录每个任务的结束的日期, 然后按照开始的日期sort整个列表, i记录当前浏览到了哪个event
    // 1. for loop每一天d, d从1到100000天
    //      a. 如果 i == len && ends.isEmpty(); 证明当前已经浏览完所有的任务而且没有待处理的任务了, 直接break
    //      b. 用一个whileloop, while当前 !ends.isEmpty() && ends.peek() < d, 证明里面结束日期在今天直接的任务都可以直接去掉了, 而且没做
    //      c. 从i开始, 如果有任务的起始日期 == d, ends.offer(events[i++][1]);
    //      d. 如果当前pq不是空的, 那么今天可以做一个任务, 把pq里面开始时间最早的任务做了, 而且pq里面的任务肯定是今天可以做的

    // Runtime: O(nlog(n)), 因为i会在len的时候quit, Space: O(n);
    public int maxEvents(int[][] events) {
        int res = 0, len = events.length, i = 0;
        // if(len < 1) return 0;
        Arrays.sort(events, (a, b) -> (a[0] - b[0]));
        Queue<Integer> ends = new PriorityQueue<>((a, b) -> a - b);
        for(int d = 1; d <= 100000; d++){
            if(i == len && ends.isEmpty()){
                break;
            }
            while(!ends.isEmpty() && ends.peek() < d){
                ends.poll(); //先把到今日为止已经没法举办的event全部都poll()掉
            }
            while(i < len && events[i][0] == d){
                ends.offer(events[i++][1]);
            }
            if(!ends.isEmpty()){
                ends.poll(); //今天做掉一个event
                res ++;
            }
        }
        return res;
    }
}
