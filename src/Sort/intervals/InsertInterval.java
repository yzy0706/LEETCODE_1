package Sort.intervals;

import java.util.ArrayList;
import java.util.List;

public class InsertInterval {
    //做法: 新建一个List<int[]>来储存浏览intervals后生成的所有interval, 用三个whileloop
    //1. 当insertInterval[0] > intervals[i][1], 代表整个插入interval都比当前interval大, 当前interval直接加入到list里;
    //2. 当intertInterval[1] >= intervals[i][0], 代表插入interval和当前interval有交集, 那么就比较起终点来更新起终点
    //3. 最后剩下的肯定就是完全大于插入区间的区间, 那么就随着最后一个whileloop加到list里, 最后再根据list建立一个int[][]return就好
    //Runtime : O(n), space: O(n)
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();
        int i = 0;

        while(i < intervals.length && newInterval[0] > intervals[i][1]) {//如果插入的interval左边比当前interval右边还大, 直接把当前的interval加到res
            res.add(new int[]{intervals[i][0], intervals[i][1]});
            i++;
        }
        while(i < intervals.length && newInterval[1] >= intervals[i][0]){
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            i++;
        }
        res.add(new int[]{newInterval[0], newInterval[1]});

        while(i < intervals.length){
            res.add(new int[]{intervals[i][0], intervals[i][1]});
            i++;
        }

        int[][] res2 = new int[res.size()][2];
        for(int j = 0; j < res.size(); j++){
            res2[j][0] = res.get(j)[0];
            res2[j][1] = res.get(j)[1];
        }
        return res2;
    }
}
