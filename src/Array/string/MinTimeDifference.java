package Array.string;

import java.util.List;

public class MinTimeDifference {
    // 做法: 用的sort + string的split来计算时间
    // 1. 先把所有的string sort一下
    // 2. 从第一个开始按照正常的时间差的计算跟前面的相比, 就是到了最后一个的时候, 要把第0个的小时加上24, 然后把最后一个当做前一个来最后计算一次时间差
    // Runtime: O(nlog(n)), Space: O(1);

    public int findMinDifference_sort_split(List<String> timePoints) {
        timePoints.sort((a, b) -> a.compareTo(b));
        int res = Integer.MAX_VALUE;
        for(int i = 1; i < timePoints.size(); i++){
            String former = timePoints.get(i-1), cur = timePoints.get(i);
            if(cur.equals(former)) return 0;
            String[] formerSplit = former.split(":"), curSplit = cur.split(":");
            int curDiff = (60 - Integer.parseInt(formerSplit[1])) + (Integer.parseInt(curSplit[0]) - (Integer.parseInt(formerSplit[0]) + 1)) * 60 + Integer.parseInt(curSplit[1]);
            if(i == timePoints.size() - 1){
                String first = timePoints.get(0);
                String[] firstSplit = first.split(":");
                int lastDiff = (60 - Integer.parseInt(curSplit[1]) + (Integer.parseInt(firstSplit[0]) + 24 - (Integer.parseInt(curSplit[0]) + 1)) * 60 + Integer.parseInt(firstSplit[1]));
                curDiff = Math.min(curDiff, lastDiff);
            }
            res = Math.min(res, curDiff);
        }
        return res;
    }

    // 做法: 第二种做法是把所有的时间都换算成分钟
        // 1. forloop一遍所有的时间, 求出分钟的最大值和最小值, 并且用一个boolean[] mark记录所有出现过的时间
        // 2. forloop从最小值开始一直到最大值的分钟里面所有被mark过的分钟数,
        //  a. 如果是最小值的话就拿 1440 - (max - min) 和res比较一下
        //  b. 其它时候就拿i - prev和res比较一下

        // Runtime: O(n), Space: O(1440);

        public int findMinDifference_transfer_to_min(List<String> timePoints) {
            timePoints.sort((a, b) -> a.compareTo(b));
            int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
            boolean[] mark = new boolean[1440];
            for(int i = 0; i < timePoints.size(); i++){
                String[] curSplit = timePoints.get(i).split(":");
                int h = Integer.parseInt(curSplit[0]), m = Integer.parseInt(curSplit[1]), total = h * 60 + m;
                min = Math.min(min, total);
                max = Math.max(max, total);
                if(mark[total]) return 0;
                mark[total] = true;
            }
            int res = Integer.MAX_VALUE, prev = 0;
            for(int i = min; i <= max; i++){
                if(!mark[i]) continue;
                if(i == min) res = Math.min(res, 1440 - (max - min));
                else res = Math.min(res, i - prev);
                prev = i;
            }
            return res;
    }


    public int findMinDifference(List<String> timePoints) {
        timePoints.sort((a, b) -> {
            String[] aTwoPart = a.split(":"), bTwoPart = b.split(":");
            if(aTwoPart[0].equals("00")) return 1;
            if(bTwoPart[0].equals("00")) return -1;
            return a.compareTo(b);
        });
        int res = Integer.MAX_VALUE;
        for(int i = 1; i < timePoints.size(); i++){
            String former = timePoints.get(i-1), cur = timePoints.get(i);
            if(cur.equals(former)) return 0;
            String[] formerSplit = former.split(":"), curSplit = cur.split(":");
            if(curSplit[0].equals("00")) curSplit[0] = "24";
            int curDiff = (60 - Integer.parseInt(formerSplit[1])) + (Integer.parseInt(curSplit[0]) - (Integer.parseInt(formerSplit[0]) + 1)) * 60 + Integer.parseInt(curSplit[1]);
            res = Math.min(res, curDiff);
        }
        return res;
    }



    //对于 "00："分别讨论， 但还有"01"等情况
    public int findMinDifference_2(List<String> timePoints) {
        timePoints.sort((a, b) -> {
            String[] aTwoPart = a.split(":"), bTwoPart = b.split(":");
            if(aTwoPart[0].equals("00")) return 1;
            if(bTwoPart[0].equals("00")) return -1;
            return a.compareTo(b);
        });
        int res = Integer.MAX_VALUE;
        for(int i = 1; i < timePoints.size(); i++){
            String former = timePoints.get(i-1), cur = timePoints.get(i);
            if(cur.equals(former)) return 0;
            String[] formerSplit = former.split(":"), curSplit = cur.split(":");
            if(curSplit[0].equals("00")){
                String[] reverseCur = formerSplit, reverseFormer = curSplit;
                int reverseDiff = Math.abs((60 - Integer.parseInt(reverseFormer[1])) + (Integer.parseInt(reverseCur[0]) - (Integer.parseInt(reverseFormer[0]) + 1)) * 60 + Integer.parseInt(reverseCur[1]));
                curSplit[0] = "24";
                int curDiff = Math.abs((60 - Integer.parseInt(formerSplit[1])) + (Integer.parseInt(curSplit[0]) - (Integer.parseInt(formerSplit[0]) + 1)) * 60 + Integer.parseInt(curSplit[1]));
                curDiff = Math.min(curDiff, reverseDiff);
                res = Math.min(res, curDiff);
            }
            else{
                int curDiff = (60 - Integer.parseInt(formerSplit[1])) + (Integer.parseInt(curSplit[0]) - (Integer.parseInt(formerSplit[0]) + 1)) * 60 + Integer.parseInt(curSplit[1]);
                res = Math.min(res, curDiff);
            }
        }
        return res;
    }
}
