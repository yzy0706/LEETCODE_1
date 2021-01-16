package Sort.HeapSort;

import java.util.PriorityQueue;

public class SortWithKRange {
    //刀给的题， 排序距离nums上每个数k距离的数， 用的是一个size为k的minHeap， 从位置k-1开始一直吸收新的数并分配最小值， 最后再把minHeap
    //剩下的加到int[] res里就行了
    //Runtime: O(nlog(k)), minHeap的大小最大为k， 而且要吐出来最小值n次， space：O(klog(k)), space就是k个log(k)的值


    public int[] sortWithKRange(int[] nums, int k){
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for(int i = 0; i < k+1; i++){
            minHeap.offer(nums[i]);
        }
        int cnt = 0;
        int[] res = new int[nums.length];
        for(int i = k+1; i < nums.length; i++){
            res[cnt++] = minHeap.poll();
            minHeap.offer(nums[i]);
        }
        while(!minHeap.isEmpty()){
            res[cnt++] = minHeap.poll();
        }
        return res;
    }
}
