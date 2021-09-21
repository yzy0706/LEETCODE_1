package Array.Intervals;

import java.util.*;

public class MeetingScheduler {
    //做法: 最后一遍用sort + two pointer做的, 速度最快
    // 1. 把两个slots都sort一下, 两个pointer: p1 = 0, p2 = 0;
    // 2. 比较两个pointer指向的int[]:
    //      a. 运用常规的取得两个int[]的重合的方法, 看他们重合的部分符不符合duration的条件, 如果符合直接return Arrays.asList(s, s + duration);
    //      b. 如果不符合, 比较两个int[]的终点, 那个interval[1]比较小就移动哪个
    //Runtime: O(nlog(n)), Space: O(1)

    public List<Integer> minAvailableDuration_two_pointer(int[][] slots1, int[][] slots2, int duration) {
        Arrays.sort(slots1, Comparator.comparing(a -> a[0]));
        Arrays.sort(slots2, Comparator.comparing(a -> a[0]));
        int p1 = 0,  p2 = 0, len1 = slots1.length, len2 = slots2.length;
        while(p1 < len1 && p2 < len2){
            int[] i1 = slots1[p1], i2 = slots2[p2];
            if(i1[1] >= i2[0]){
                int s = Math.max(i1[0],i2[0]), e =  Math.min(i1[1], i2[1]);
                if(e - s >= duration){
                    return Arrays.asList(s, s + duration);
                }
            }
            if(i1[1] < i2[1]){
                p1 ++;
            }
            else{
                p2 ++;
            }
        }
        return new ArrayList<>();
    }




    //做法: 第一遍直接用sort + double forloop做的, 可以过但是比较慢
    //Runtime: O(n^2), Space: O(n)

    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        List<Integer> res = new ArrayList<>();
        int len1 = slots1.length, len2 = slots2.length;
        Arrays.sort(slots1, (a, b) -> a[0] - b[0]);
        Arrays.sort(slots2, (a, b) -> a[0] - b[0]);
        int minS = Integer.MAX_VALUE;
        for(int i = 0; i < len1; i++){
            int[] int1 = slots1[i];
            for(int j = 0; j < len2; j++){
                int[] int2 = slots2[j];
                if(int1[1] >= int2[0]){
                    int s = Math.max(int1[0], int2[0]), e = Math.min(int1[1], int2[1]);
                    if(e - s >= duration){
                        minS = Math.min(minS, s);
                        break;
                    }
                }
            }
        }
        if(minS == Integer.MAX_VALUE){
            return res;
        }
        else{
            res.add(minS);
            res.add(minS + duration);
        }
        return res;
    }


    // 第二遍想用two pointer来做，但是不成功
    public List<Integer> minAvailableDuration_twoPointer(int[][] slots1, int[][] slots2, int duration) {
        List<Integer> res = new ArrayList<>();
        Arrays.sort(slots1, (a, b) -> a[0] - b[0]);
        Arrays.sort(slots2, (a, b) -> a[0] - b[0]);
        int minS = Integer.MAX_VALUE, len1 = slots1.length, len2 = slots2.length, p1 = 0, p2 = 0;
        while(p1 < len1 && p2 < len2){
            while(p2 < len2 && slots2[p2][1] < slots1[p1][0]){
                p2 ++;
            }
            if(p2 == len2){
                break;
            }
            while(p1 < len1 && slots1[p1][1] < slots2[p2][0]){
                p1 ++;
            }
            if(p1 == len1){
                break;
            }
            int s = Math.max(slots1[p1][0], slots2[p2][0]), e = Math.min(slots1[p1][1], slots2[p2][1]);
            if(e - s >= duration){
                minS = Math.min(minS, s);
                break;
            }
            if(p1 + 1 < len1 && slots1[p1 + 1][1] >= slots2[p2][0]){
                p1 ++;
            }
            else{
                p2 ++;
            }
        }
        if(minS == Integer.MAX_VALUE){
            return res;
        }
        else{
            res.add(minS);
            res.add(minS + duration);
        }
        return res;
    }



    //做法： 第三遍用forloop和whileloop， 其实跟第二遍的two pointer没区别

    public List<Integer> minAvailableDuration_3(int[][] slots1, int[][] slots2, int duration) {
        List<Integer> res = new ArrayList<>();
        Arrays.sort(slots1, (a, b) -> a[0] - b[0]);
        Arrays.sort(slots2, (a, b) -> a[0] - b[0]);
        int minS = Integer.MAX_VALUE, len1 = slots1.length, len2 = slots2.length, j = 0;
        for(int i = 0; i < len1; i++){
            while(j < len2 && slots2[j][1] < slots1[i][0]){
                j ++;
            }
            if(j == len2){
                break;
            }
            int s = Math.max(slots1[i][0], slots2[j][0]), e = Math.min(slots1[i][1], slots2[j][1]);
            if(e - s >= duration){
                minS = s;
                break;
            }
        }
        if(minS == Integer.MAX_VALUE){
            return res;
        }
        else{
            res.add(minS);
            res.add(minS + duration);
        }
        return res;
    }



    // 第四遍用两个pq来做， 其实跟sort没有本质区别， 甚至还麻烦一点， 重点是什么时候浏览slots1或者slots2的下一个

    public List<Integer> minAvailableDuration_heap(int[][] slots1, int[][] slots2, int duration) {
        List<Integer> res = new ArrayList<>();
        int minS = Integer.MAX_VALUE;
        PriorityQueue<int[]> pq1 = new PriorityQueue<>((a, b) -> a[0] - b[0]), pq2 = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq1.addAll(Arrays.asList(slots1));
        pq2.addAll(Arrays.asList(slots2));
        while(!pq1.isEmpty()){
            int[] top1 = pq1.peek();
            int s1 = top1[0], e1 = top1[1];
            while(!pq2.isEmpty() && (pq2.peek()[1] < s1 || pq2.peek()[0] > e1)){
                pq2.poll();
            }
            if(pq2.isEmpty()){
                break;
            }
            int[] top2 = pq2.peek();
            int s2 = top2[0], e2 = top2[1], s = Math.max(s1, s2), e = Math.min(e1, e2);
            if(e - s >= duration){
                minS = s;
                break;
            }
            else{

            }
        }
        if(minS == Integer.MAX_VALUE){
            return res;
        }
        else{
            res.add(minS);
            res.add(minS + duration);
        }
        return res;
    }

}
