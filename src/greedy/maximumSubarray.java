package greedy;

public class maximumSubarray {
    // 主要就是用一个for loop比较nums[i], cur, max的最大值的greedy做法
      public int maxSubArray(int[] nums) {
        if(nums.length < 1) return 0;
        if(nums.length == 1) return nums[0];
        int res = nums[0];
        int cur = nums[0];
        for(int i = 1; i < nums.length; i++){
            cur = Math.max(cur + nums[i], nums[i]);
            res = Math.max(cur, res);
        }
        return res;
    }

}
