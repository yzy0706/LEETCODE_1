package Greedy.Array;

public class MaximumSubarray {
    //做法：用一个sum来记录之前所有的连续数字的和
    // 1. 如果sum < 0的话连续subarray的开始就变成当前的i， sum = nums[i]；
    // 2. 如果sum >= 0的话， sum += nums[i], end也变成了当前的i

    //Runtime: O(n), space: O(1)

    public int maxSubArray(int[] nums) {
        if(nums.length < 1) return 0;
        int res = nums[0];
        int sum = nums[0];
        int start = 0, end = 0, curStart = 0, curEnd = 0;

        for(int i = 1; i < nums.length ; i++) {
            if (sum < 0) {
                curStart = i;
                curEnd = i;
                sum = nums[i];
            } else {
                curEnd = i;
                sum += nums[i];
            }
            if (sum >= res) {
                start = curStart;
                end = curEnd;
                res = sum;
            }

        }

        return res;
    }
}
