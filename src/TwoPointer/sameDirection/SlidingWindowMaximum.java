package TwoPointer.sameDirection;

import java.util.Collections;
import java.util.PriorityQueue;

public class SlidingWindowMaximum {
    // 做法: 这个做法是用PriorityQueue<int[]> pq记录每个位置的index和数值
    // 1. forloop i, 从0到len-1, offer每一个i位置的index和大小
    // 2. 用一个whileloop把pq里最大值的位置 pq.peek()[0] < i - k + 1的位置全部poll()出来
    // 3. 如果 i >= k - 1证明当前i已经forloop到足够长度来形成一个sliding window了, 所以res[pos++] = pq.peek()[1];
    // Runtime: O(nlog(n)), Space: O(nlog(n))

    public int[] maxSlidingWindow_1(int[] nums, int k) {
        int len = nums.length;
        if(len == 1 || k == 1 || len < k) return nums;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        int[] res = new int[len - k + 1];
        int pos = 0;
        for(int i = 0; i < len; i++){
            pq.offer(new int[]{i, nums[i]});
            while(!pq.isEmpty() && pq.peek()[0] < i - k + 1) pq.poll();
            if(i >= k - 1) res[pos++] = pq.peek()[1];
        }
        return res;
    }


    //第一遍想用最简单的pq来做
    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        if(len == 1 || k == 1 || len < k) return nums;
        int[] res = new int[len - k + 1];
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i = 0; i < k; i++) pq.offer(nums[i]);
        int i = 0, j = i + k - 1, pos = 0;
        while(j < len){
            int max = pq.peek();
            res[pos ++] = max;
            pq.remove(nums[i++]);
            if(j == len - 1) break;
            pq.offer(nums[++j]);
        }
        return res;
    }
}
