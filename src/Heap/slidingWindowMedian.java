package Heap;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class slidingWindowMedian {
    public double[] medianSlidingWindow(int[] nums, int k) {
        Queue<Integer> minHeap = new PriorityQueue<>();
        Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        double[] res = new double[];
        int i = 0, j = k-1;
        for( j = k-1; j < nums.length; j++){
            while(i < j + 1){
                maxHeap.offer(nums[i]);
                minHeap.offer(maxHeap.poll());
                while(minHeap.size() > maxHeap.size() + 1) maxHeap.offer(minHeap.poll());
            }
            double cur = find(maxHeap,minHeap);
            res[j - k + 1] = cur;
            if(cur > (double)nums[j-k+1]) maxHeap.remove(nums[j-k+1]);
            else minHeap.remove(nums[j - k +1]);
        }

        return res;

    }


    public double find (Queue<Integer> max, Queue<Integer> min){
        if(max.size() == 0 && min.size() == 0) return 0;
        if(max.size() == min.size()) return ((double)min.peek() + (double)max.peek())/2;
        else return min.peek();
    }
}
