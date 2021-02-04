package TwoPointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class threeSum {
    //双指针
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int middle = i + 1;
            int right = nums.length - 1;
            while (right > middle) {
                int sum = nums[i] + nums[middle] + nums[right];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[middle], nums[right]));
                    middle++;
                    right--;
                    while (middle < right && nums[middle - 1] == nums[middle]) middle++;
                    while (middle < right && nums[right + 1] == nums[right]) right--;
                }
                else if (sum < 0) {
                    middle++;
                }
                else if (sum > 0) {
                    right--;
                }
            }

        }

        return result;

    }
}


