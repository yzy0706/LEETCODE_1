package Array;

public class RunningSumOf1DArray {
    //做法: 就用简单的叠加
    //Runtime: O(n), Space: O(1)

    public int[] runningSum(int[] nums) {
        if(nums.length <= 1) return nums;
        for(int i = 1; i < nums.length; i++){
            nums[i] += nums[i-1];
        }
        return nums;
    }
}
