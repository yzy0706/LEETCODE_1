package Array.Intervals;

import java.util.PriorityQueue;

public class AddBoldTagToString {
    // 做法: 类似于merge interval的做法
    public String addBoldTag(String s, String[] dict) {
        int len = dict.length;
        if(len < 1) return s;
        PriorityQueue<int[]> intervals = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
        for(String d : dict){
            int start = s.indexOf(d);
            while(start != -1){
                intervals.add(new int[]{start, start + d.length()});
                start  ++;
                start = s.indexOf(d, start);
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
