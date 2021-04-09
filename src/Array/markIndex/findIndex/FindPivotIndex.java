package Array.markIndex.findIndex;

public class FindPivotIndex {
    // 做法: 因为是要求所有的sum所以想到了把每一位都叠加上之前所有数的和 nums[i] += nums[i-1];, 然后一直如果左边的和等于右边的和就return当前的位置, 之前有过类似的做法.
    // Runtime: O(n), Space: O(n)
    public int pivotIndex_reviewed(int[] nums) {
        if(nums.length < 1) return -1;
        if(nums.length == 1) return 0;
        for(int i = 1; i < nums.length; i++){
            nums[i] += nums[i-1];
        }
        int sum = nums[nums.length - 1];
        for(int i = 0; i < nums.length; i++){
            int left = i > 0 ? nums[i-1] : 0;
            if(left == sum - nums[i]){
                return i;
            }
        }
        return -1;
    }





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
