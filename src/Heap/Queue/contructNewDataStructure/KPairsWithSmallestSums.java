package Heap.Queue.contructNewDataStructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class KPairsWithSmallestSums {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0] + a[1] - b[0] - b[1]);
        for(int i = 0; i < nums1.length; i++){
            pq.offer(new int[]{nums1[i], nums2[0], 0});
        }
        List<List<Integer>> res = new ArrayList<>();
        while(!pq.isEmpty() && k > 0){ // 必须得是k-- > 0, 我也不知道为什么
            int[] cur = pq.poll();
            res.add(Arrays.asList(cur[0], cur[1]));
            if(cur[2] == nums2.length - 1) continue;
            pq.offer(new int[]{cur[0], nums2[cur[2]+1], cur[2]+1});
        }
        return res;
    }


    //题目给的solution

    public List<List<Integer>> kSmallestPairs_origin(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums1.length < 1 || nums2.length < 1 || k == 0) return res;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] + a[1] - b[0] - b[1]);

        //先放进去nums1的前k个元素和nums[2]的第一个元素
        for(int i = 0; i < nums1.length && i < k; i++){
            pq.offer(new int[]{nums1[i], nums2[0], 0});
        }
        //如果nums2还有元素的话就一个个添加到pq的各个list里面来, 添加k次
        while(k-- > 0 && !pq.isEmpty()){
            int[] cur = pq.poll();
            res.add(Arrays.asList(cur[0], cur[1]));
            if(cur[2] == nums2.length - 1) continue;
            pq.offer(new int[]{cur[0], nums2[cur[2]+1], cur[2]+1});
        }
        return res;
    }
}
