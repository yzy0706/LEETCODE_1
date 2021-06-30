package Heap.treeMap;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class MinDifferenceBetweenLargestAndSmallestVal {
    // 做法: 第二种pq做法：
    // 1. 用两个size为4且排列顺序相反的pq来一直poll(), 得到最小的四个数和最大的四个数
    // 2. 然后比较这8个数的 Math.min(res, arr[arr.length - (4 - i)] - arr[i]); 得到最多去除三个数的情况下最小的差
    // Runtime: O(n), Space: O(n);


    public int minDifference_2(int[] nums) {
        int len = nums.length;
        if(len <= 4){
            return 0;
        }
        if(len <= 8){
            Arrays.sort(nums);
            return calDif(nums);
        }
        PriorityQueue<Integer> max4 = new PriorityQueue<>();
        PriorityQueue<Integer> min4 = new PriorityQueue<>((a, b) -> b - a);
        for(int n : nums){
            max4.offer(n);
            min4.offer(n);
            if(max4.size() > 4) max4.poll();
            if(min4.size() > 4) min4.poll();
        }
        int[] a = new int[8]; // 最小的四个数和最大的四个数, 因为最多只能去掉三个数
        for(int l = 3, r = 4; l >= 0 && r < 8; l --, r ++){
            a[l] = min4.poll();
            a[r] = max4.poll();
        }
        return calDif(a);
    }

    public int calDif(int[] a){
        int res = Integer.MAX_VALUE;
        for(int i = 0; i < 4; i ++){
            res = Math.min(res, a[a.length - (4 - i)] - a[i]);
        }
        return res;
    }




    // 做法: 第一遍用的类似于backtrack和TreeMap的结合, 但速度非常慢
    // 1. 记录所有数字的频率, 放到TreeMap里
    // 2. 建立一个dfs的helper, 一共就只有3次move, 每改变一个数字就是一个move
    //      a. 如果去掉当前最大或者最小的数让move < 0, 直接复制当前的map, move变成0
    //      b. 如果下一个dfs里面move >= 0, 当前map先删掉最大或者最小值, 然后进入新的dfs, 然后再放回来
    // Runtime: O(nlog(n)), Space: O(nlog(n))
    
    int res;
    public int minDifference(int[] nums) {
        int len = nums.length;
        if(len <= 4) return 0;
        res = Integer.MAX_VALUE;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int n : nums){
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        dfs(map, 3);
        return res;
    }

    public void dfs(TreeMap<Integer, Integer> map, int move){
        if(move <= 0){
            res = Math.min(res, map.lastKey() - map.firstKey());
            return;
        }
        int max = map.firstKey(), maxFreq = map.get(max), min = map.lastKey(), minFreq = map.get(min);
        if(maxFreq > move){
            dfs(new TreeMap<>(map), 0);
        }
        else{
            map.remove(max);
            dfs(new TreeMap<>(map), move - maxFreq);
            map.put(max, maxFreq);
        }
        if(minFreq > move){
            dfs(new TreeMap<>(map), 0);
        }
        else{
            map.remove(min);
            dfs(new TreeMap<>(map), move - minFreq);
            map.put(min, minFreq);
        }
    }
}
