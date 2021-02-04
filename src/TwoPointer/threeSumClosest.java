package TwoPointer;

import java.util.Arrays;

public class threeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 2; i++) {
            int right = nums.length - 1;
            int left = i+1;
            while (right > left) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > target) {
                    right--;
                }
                else{
                    left++;
                }
                while (Math.abs(sum- target) < Math.abs(result-target)) {
                    result = sum;
                }

            }
        }
        return result;
    }
}
