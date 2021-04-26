package Heap;

import java.util.PriorityQueue;

public class KthLargestElementInAnArray {
    // 主要是queue.size()大于k的时候pq.poll()
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(k);
        for(int i : nums){
            pq.offer(i);
            if(pq.size() > k) pq.poll();

        }
        return pq.poll();

    }
}
