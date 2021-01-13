package heap.Queue.tailProblems_greedy;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class topKFrequentElement {
    //拿一个map去记录所有数的出现频率， 然后用一个size为k的pq由大到小记录各个数的，因为pq永远是尾巴出去， 所以不能是由小到大，这个要注意
    public int[] topKFrequent(int[] nums, int k) {
        if(k == nums.length) return nums;
        Map<Integer, Integer> freq = new HashMap<>();
        for(int i : nums){
            freq.put(i, freq.getOrDefault(i, 0) + 1);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> freq.get(a) - freq.get(b));
        for(int i : freq.keySet()){
            pq.offer(i);
            if(pq.size() > k) pq.poll();
        }
        int[] res = new int[k];
        for(int i = k-1; i >= 0; i--){
            res[i] = pq.poll();
        }

        return res;

    }
}
