package greedy;

import java.util.Arrays;

public class mergeIntervals{
       //主要就是建一个linkedList， getLast() 去比较前一个interval是不是overlap，比较前一个[1]和最后一个[0]就好
        public int[][] merge(int[][] intervals) {
            Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));
            LinkedList<int[]> res = new LinkedList<>();

            for(int[] interval : intervals){
                if(res.isEmpty() || res.getLast()[1] < interval[0]) {
                    res.add(interval);
                }

                else{
                    res.getLast()[1] = Math.max(res.getLast()[1], interval[1]);
                }
            }

            return res.toArray(new int[res.size()][]);

        }{
}
