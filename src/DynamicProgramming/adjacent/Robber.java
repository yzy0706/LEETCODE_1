package DynamicProgramming.adjacent;

public class Robber {
    //做法：主要就是去比较dp[i-2]+nums[i]和dp[i-1]谁更大， 然后就选谁， 记住dp[1]也要是dp[0]或者nums[1]的最大值
    //Runtime: O(n), space 也是O(n)
    public int rob(int[] nums) {
        if(nums.length < 1) return 0;
        if(nums.length == 1) return nums[0];
        int[] dp = new  int[nums.length+1];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        if(nums.length == 2) return Math.max(dp[0], dp[1]);
        for(int i = 2; i < nums.length; i++){
            dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1]);
        }
        return dp[nums.length-1];
    }
}
