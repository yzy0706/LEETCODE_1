package TwoPointer.intervals;

import java.util.ArrayList;
import java.util.List;

public class IntervalListIntersections {
    // 做法: two pointer和interval的经典做法, 对于这种interval的题我们就是要看两个interval是不是有重合
    // 1.  a. startMax = Math.max(first[0], first[0]); 找到两个interval比较大的那个起点
    //     b. endMin = Math.min(first[1], second[1]); 找到两个interval比较小的终点
    // 2.  if(startMax <= endMin) res.add(new int[]{startMax, endMin}); 如果两个interval里比较大的起点小于等于两个里面比较小的终点, 证明有重合, 加入这个重合
    // 3. 比较小的终点是两个interval之中哪个的终点, 这个interval就往后移动一个
    // Runtime: O(m+n), Space: O(m+n)

    public int[][] intervalIntersection_classic(int[][] firstList, int[][] secondList) {
        if(firstList == null || secondList == null || firstList.length < 1 || secondList.length < 1) return new int[0][0];
        List<int[]> res = new ArrayList<>();
        int i = 0, j = 0;
        while(i < firstList.length && j < secondList.length){
            int[] first = firstList[i];
            int[] second = secondList[j];
            int start = Math.max(first[0], second[0]);
            int end = Math.min(first[1], second[1]);
            if(start <= end){ //如果有重合
                res.add(new int[]{start, end});
            }
            if(end == first[1]) i++; //谁的end被加进去了谁就应该往后移一位
            else if(end == second[1]) j++;
        }
        int[][] result = new int[res.size()][2];
        for(int a = 0; a < res.size(); a++){
            result[a] = res.get(a);
        }
        return result;
    }






    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        if(firstList == null || secondList == null || firstList.length < 1 || secondList.length < 1) return null;
        List<int[]> res = new ArrayList<>();
        int i = 0, j = 0;
        while(i < firstList.length && j < secondList.length){
            int[] first = firstList[i];
            int[] second = secondList[j];
            if(first[0] > second[1]) j++;
            else if(second[0] > first[1]) i++; //两边没交集的情况
            else{
                int start = Math.max(first[0], second[0]);
                int end = Math.min(first[1], second[1]);
                res.add(new int[]{start, end});
                if(first[1] < second[1]) i++;
                if(first[1] > second[1]) j++;
                else{
                    i++;
                    j++;
                }
            }
        }
        int[][] result = new int[res.size()][2];
        for(int a = 0; a < res.size(); a++){
            result[a] = res.get(a);
        }
        return result;
    }
}
