package TwoPointer.sameDirection.FloydCycle;

import java.util.Arrays;

public class FindDuplicateNumber {
    //做法: 用Floyd Cycle Detection Algorithm
    // 1. 设置一个slow = nums[0], fast = nums[nums[0]];
    // 2. 当第一轮fast != slow的时候, slow = nums[slow]; fast = nums[nums[slow]];
    // 3. fast归为0, 现在fast和slow一个更新速率: slow = nums[slow]; fast = nums[slow]; 看slow和fast什么时候一致
    // Runtime: O(n), Space: O(1);


    public int findDuplicate_Floyd(int[] nums) {
        if(nums.length > 1){
            int slow = nums[0];
            int fast = nums[nums[0]];
            while(slow != fast){
                slow = nums[slow];
                fast = nums[nums[fast]];
            }
            fast = 0;
            while(fast != slow){
                slow = nums[slow];
                fast = nums[fast];
            }
            return slow;

        }
        return -1;
    }




    //做法: 直接sort看有哪个位置跟他上一个位置一样就是我们要找的重复的数
    // Runtime: O(nlog(n)), Space: O(1);
    public int findDuplicate(int[] nums) {
        Arrays.sort(nums);
        for(int i = 1; i < nums.length; i++){
            if(nums[i] <= nums[i-1]){
                return nums[i];
            }
        }
        return -1;
    }
}
