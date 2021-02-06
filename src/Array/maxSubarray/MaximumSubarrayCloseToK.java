package Array.maxSubarray;

import java.util.TreeSet;

public class MaximumSubarrayCloseToK {
    //做法： 用treeSet记录到每一位的sum，
    // 1. 在treeSet里找到比sum - k大但最接近的那个值， Integer ceiling = set.ceiling(sum - k);
    // 2. 把res更新为sum - ceiling如果他更大的话， 也就是用到sum的长度减去了刚好大于sum - k的前面一段数字的长度

    //Runtime: O(log(n)), space: O(nlog(n)), treeSet的大小

    public int maxSubarrayCloseToK(int[] nums, int k){
        int sum = 0, res = Integer.MIN_VALUE;
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);

        for (int i = 0; i < nums.length; i++){
            sum += nums[i];
            Integer ceiling = set.ceiling(sum - k);
            if(ceiling != null){
                res = Math.max(res, sum - ceiling); //sum - 与(sum - k)最接近的且大于它值就是sum里与k最接近的值
            }
            set.add(sum);
        }

        return res;

    }
}
