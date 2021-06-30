package Array.Intervals;

import java.util.PriorityQueue;

public class AddBoldTagToString {
    // 做法: 类似于merge interval的做法
    // 1. 根据dict里面的每个字符, 用whileloop和s.indexOf(d, ++ start),
    // 找到每个d出现的所有interval: [start, start + d.length()], 加到PriorityQueue<int[]> intervals里
    // 2. 用一个last = 0, 记录上一次加tag的位置的后一位, 用while(!intervals.isEmpty())合并所有的interval
    //   a. 用pq里面合并interval的办法合并所有能合并的interval
    //   b. 如果合并完了当前curS > last, 证明中间有string没有任何一个dict里面词语能当它的substring, res.append(s.substring(last, curS));
    //   c. res.append(s.substring(curS, curE));
    //   d. last = curE;
    //  3. 最后记得如果last < s.length(), 最后还要加上一段 res.append(s.substring(last, s.length()));
    //  Runtime: O(nlog(n)), Space: O(nlog(n)), n是string的长度

    public String addBoldTag(String s, String[] dict) {
        int len = dict.length;
        if(len < 1) return s;
        PriorityQueue<int[]> intervals = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
        for(String d : dict){
            int start = s.indexOf(d);
            while(start != -1){
                intervals.add(new int[]{start, start + d.length()});
                start = s.indexOf(d, ++start);
            }
        }
        StringBuilder res = new StringBuilder();
        int last = 0;
        while(!intervals.isEmpty()){
            int[] cur = intervals.poll();
            int curS = cur[0], curE = cur[1];
            while(!intervals.isEmpty() && curE >= intervals.peek()[0]){
                int[] next = intervals.poll();
                int nextS = next[0], nextE = next[1];
                curS = Math.min(curS, nextS);
                curE = Math.max(curE, nextE);
            }
            if(curS > last){
                res.append(s.substring(last, curS));
            }
            last = curE;
            String combine = s.substring(curS, curE);
            res.append("<b>" + combine + "</b>");
        }
        if(last < s.length()){
            res.append(s.substring(last, s.length()));
        }
        return res.toString();
    }
}
