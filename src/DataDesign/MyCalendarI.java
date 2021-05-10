package DataDesign;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class MyCalendarI {
    // 做法: 第三遍用TreeSet做的, 直接用treeset来储存之前所有valid的booking,
    // 1. 如果当前的int[] cur = new int[]{start, end}; 与cur的ceiling和floor有overlap就return false;
    // 2. 否则return true

    // Runtime: O(log(n)), Space: O(nlog(n))

    TreeSet<int[]> ts;
    public void MyCalendar_TreeSet() {
        ts = new TreeSet<>((a, b) -> (a[0] - b[0]));
    }

    public boolean book_TreeSet(int start, int end) {
        int[] cur = new int[]{start, end};
        int[] floor = ts.floor(cur), ceiling = ts.ceiling(cur);
        if((floor != null && floor[1] > start) || (ceiling != null && ceiling[0] < end)) return false;
        ts.add(cur);
        return true;
    }



    //做法： 直接比较start， end和之前存在的每一个booking，
    // 1. foreach loop比较当前的booking和之前所有的booking，如果任意一对比较中， 比较大的那个开始时间小于比较小的那个结束时间， return false
    // 2. 结束的时候还没有return false, 则当前booking可行， 把当前booking加进去并且return true；
    // Runtime: O(n), Space: O(n)

    List<int[]> list;
    public void MyCalendar_list() {
        list = new ArrayList<>();
    }

    public boolean book_list(int start, int end) {
        for(int[] booking : list) if(Math.max(booking[0], start) < Math.min(booking[1], end)) return false;
        list.add(new int[]{start, end});
        return true;
    }







    // 做法: 用pq做的, 速度稍微有点慢, 就是把预定的时间区间都按开始时间排列
    // 1. 如果pq是空的或者加入的时间比第一个时间还要早, 直接加入当前时间并且return true
    // 2. 用一个temp来储存pq里被poll()出去的int[], 当start >= pq.poll()[1]的时候, 一直poll(), 在whileloop的过程中如果pq空了或者当前end <= pq.peek()[1], 直接把temp和new int[]{start, end}加进去
    // 3. whileloop结束了还没return true的话把temp加回去, 并且return false

    // Runtime: O(nlog(n)), Space: O(nlog(n))

    PriorityQueue<int[]> pq;

    public void MyCalendar() {
        pq = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
    }

    public boolean book(int start, int end) {
        if(pq.isEmpty() || pq.peek()[0] >= end){
            pq.offer(new int[]{start, end});
            return true;
        }
        List<int[]> temp = new ArrayList<>();
        while(!pq.isEmpty() && start >= pq.peek()[1]){
            temp.add(pq.poll());
            if(pq.isEmpty() || pq.peek()[0] >= end){
                pq.addAll(temp);
                pq.offer(new int[]{start, end});
                return true;
            }
        }
        pq.addAll(temp);
        return false;
    }
}
