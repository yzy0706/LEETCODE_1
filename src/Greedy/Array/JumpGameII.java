package Greedy.Array;

import java.util.Arrays;
public class JumpGameII {
    //做法: 也是Greedy的做法, 不过用了一个nextJumpRange来记录下一次能跳的最远的地方,
    //因为我们是在看最少能跳几步, 也就是我们要在当前能跳的最远的范围以内更新下一次能跳的最远范围,

    //如果超过了当前能跳的最远范围跳的次数就++, 然后当前能跳的范围就变成了下一次能跳的范围, 下一次能跳的范围就是i + nums[i]的最大值

    //Runtime: O(n), space: O(1)
    public int jump_Greedy(int[] nums) {
        int res = 0;
        int curJumpRange = 0, nextJumpRange = nums[0];
        for(int i = 0; i < nums.length; i++){
            if(i <= curJumpRange){
                nextJumpRange = Math.max(nextJumpRange, i + nums[i]);
            }
            else if(i > curJumpRange){
                res ++;
                curJumpRange = nextJumpRange;
                nextJumpRange = i + nums[i];
            }
        }

        return res;
    }



    //第一遍用dp做的， 思路出了问题， 复杂化了， 其他的其实没错

    public int jump(int[] nums) {
        int res = 0;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        int curJumpRange = 0, nextJumpRange = nums[0];
        for(int i = 1; i < nums.length; i++){
            if(i <= curJumpRange){
                dp[i] = dp[i - 1];
            }
            else{
                dp[i] = dp[i - 1] + 1;
                curJumpRange = nextJumpRange;
                nextJumpRange = 0;
            }
            if(i == nums.length - 1){
                break;
            }
            if(i + nums[i] >= nums.length - 1){
                return dp[i] + 1;
            }
            dp[i + nums[i]] = dp[i] + 1;
            nextJumpRange = Math.max(nextJumpRange, i + nums[i]);
        }

        return dp[nums.length - 1];
    }
}
