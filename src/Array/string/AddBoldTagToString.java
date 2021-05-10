package Array.string;

import java.util.PriorityQueue;

public class AddBoldTagToString {
    public String addBoldTag(String s, String[] dict) {
        int len = dict.length;
        if(len < 1) return s;
        PriorityQueue<int[]> intervals = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
        for(String d : dict){
            int start = s.indexOf(d), dLen = d.length();
            if(start == -1) continue;
            int end = start + dLen - 1;
            intervals.add(new int[]{start, end});
        }
        StringBuilder res = new StringBuilder();
        int last = 0;
        while(!intervals.isEmpty()){
            int[] cur = intervals.poll();
            int curS = cur[0], curE = cur[1];
            while(!intervals.isEmpty() && curE >= intervals.peek()[0] - 1){
                int[] next = intervals.poll();
                int nextS = next[0], nextE = next[1];
                curS = Math.min(curS, nextS);
                curE = Math.max(curE, nextE);
            }
            if(curS > last){
                res.append(s.substring(last, curS));
            }
            last = curE + 1;
            String combine = s.substring(curS, curE + 1);
            res.append("<b>" + combine + "</b>");
        }
        if(last < s.length()){
            res.append(s.substring(last, s.length()));
        }
        return res.toString();
    }
}
