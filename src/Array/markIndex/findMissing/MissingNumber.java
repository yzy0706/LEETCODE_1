package Array.markIndex.findMissing;

public class MissingNumber {
    //做法: 用首项加末项的方式找到整个nums应有的和, 然后减去当前我们浏览过的所有nums上的数的和就是我们要找的缺失了的那个数
    //Runtime: O(n), Space: O(1)
    public int missingNumber(int[] nums) {
        int res = 0;
        int sum = 0;
        for(int i : nums){
            sum += i;
        }
        return nums.length * (nums.length+1)/2 - sum;
    }
}
