package Array.maxSubarray;

import java.util.HashMap;
import java.util.Map;

public class MaximumSizeSubarraySumK {
    //做法:
    //1. nums[i]叠加之前的所有数的和
    //2. 用一个map来装载所有的和nums[i]对应的i
    //3. forloop改变以后的nums,
    //如果当前超过了k, 且map.containsKey(nums[i] - k), 则 i - map.get(nums[i] - k)是当前最长值
    //如果当前map里面没有nums[i]这个key, put(nums[i], i)

    //Runtime: O(n), space: O(n)

    public int maxSubArrayLen(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        for(int i = 1; i < nums.length; i++){
            nums[i] += nums[i-1];
        }
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int max = 0;
        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(nums[i] - k)){
                res = Math.max(i - map.get(nums[i] - k), res);
            }
            if (!map.containsKey(nums[i])){
                map.put(nums[i], i);
            }
        }

        return res;
    }
}
