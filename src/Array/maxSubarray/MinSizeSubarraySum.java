package Array.maxSubarray;

import java.util.HashMap;

public class MinSizeSubarraySum {
    // 做法: 用的滑动窗口做的, 记得滑动窗口的基础格式, 我的习惯是j一般在whileloop结束以后再移动以避免混乱;
    // Runtime: O(N), Space: O(1);

    public int minSubArrayLen_slidingWindow(int target, int[] nums) {
        if(nums.length < 1) return 0;
        int i = 0, j = 0;
        int sum = 0, res = Integer.MAX_VALUE;

        while(j < nums.length){
            sum += nums[j]; //这是加了当前的这个j的数字, j还没有++

            while(sum >= target){
                res = Math.min(res, j - i + 1);
                sum -= nums[i];
                i++;
            }

            j++;
        }


        return res == Integer.MAX_VALUE ?  0 : res;
    }





    public int minSubArrayLen(int target, int[] nums) {
        for(int i = 1; i < nums.length; i++){
            nums[i] += nums[i-1];
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        int res = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(nums[i] - target)){
                res = Math.min(res, i - map.get(nums[i] - target));
            }
            else{
                if(nums[i] >= target) res = Math.min(res, i + 1);
                for(int prevSum : map.keySet()){
                    if(nums[i] - prevSum >= target){
                        res = Math.min(res, i - map.get(prevSum));
                    }
                }
            }
            map.put(nums[i], i);
        }

        return res == Integer.MAX_VALUE ?  0 : res;
    }
}
