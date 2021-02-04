package Array;

public class findPivotIndex {
    //这个题就是找到第一个除了自己本身之外左右两边的和都相等的数字，我们先找到所有数字的和，int leftSum就是左边的，
    // sum - leftSum - nums[i]就是右边的， forloop找到第一个相等的就行
    public int pivotIndex(int[] nums) {
        if(nums.length < 1) return -1;
        int sum = 0;
        int leftSum = 0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
        }
        for(int j = 0; j < nums.length; j++){
            if(j > 0) {
                leftSum += nums[j-1];
            }
            if(leftSum == sum - leftSum - nums[j]) return j;
        }
        return -1;
    }
}
