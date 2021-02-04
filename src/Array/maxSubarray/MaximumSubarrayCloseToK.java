package Array.maxSubarray;

import java.util.TreeSet;

public class MaximumSubarrayCloseToK {
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
