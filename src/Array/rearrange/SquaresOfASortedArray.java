package Array.rearrange;

public class SquaresOfASortedArray {
    // 做法 : 做这种sorted的题多想想从右到左的排列方法, 不要老是想从左到右
    // 这题主要就是看左边有没有负数:
    // 1. 有的话就用two pointer比较最左边和最右边的数哪个平方, 并且让两个pointer向中间靠拢一直到res被填满为止
    // 2. 如果没有负数就直接把整个array平方一遍return

    // Runtime: O(n), Space: O(n)

    public int[] sortedSquares(int[] nums) {
        if(nums[0] >= 0){
            for(int i = 0; i < nums.length; i++){
                nums[i] *= nums[i];
            }
            return nums;
        }
        int[] res = new int[nums.length];
        int i = 0, j = nums.length - 1;
        int idx = nums.length - 1;
        while(idx >= 0){
            if(nums[i] * nums[i] > nums[j] * nums[j]){
                res[idx--] = nums[i] * nums[i];
                i++;
            }
            else{
                res[idx--] = nums[j] * nums[j];
                j--;
            }
        }
        return res;
    }
}
