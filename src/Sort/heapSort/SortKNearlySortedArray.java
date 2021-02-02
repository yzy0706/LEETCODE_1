package Sort.heapSort;

import java.util.PriorityQueue;

public class SortKNearlySortedArray {
    //刀给的题， 排序距离nums上每个数k距离的数， 用的是一个size为k的minHeap， 从位置k-1开始一直吸收新的数并分配最小值， 最后再把minHeap
    //剩下的加到int[] res里就行了 （因为minHeap每次放置的都是最前端的那个数， 而heap的大小K+1正好就是包括了他和他之后最远为k的点的集合，
    //前面都已经sort好了）。
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
