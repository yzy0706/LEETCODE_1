package Greedy.Array;

public class JumpGame {
    //做法: 用了一个类似于greedy的做法,  用一个jumpRange来记录当前能跳的最远距离
    //如果当前最远距离到了最后一位了, 或者i到最后一位了, 直接return true
    //否则一直更新 jumpRange = Math.max(jumpRange, i + nums[i]);

    //Runtime: O(n), space: O(1);
    public boolean canJump(int[] nums) {
        if(nums.length == 1) return true;
        if(nums[0] == 0) return false;
        int jumpRange = nums[0];

        for(int i = 1; i < nums.length; i++){
            jumpRange = Math.max(jumpRange, i + nums[i]);
            if(jumpRange >= nums.length - 1) return true;
            if(jumpRange == i) break;
        }

        return false;
    }
}
