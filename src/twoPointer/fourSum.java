package twoPointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class fourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return result;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) {   //avoid duplicated
                continue;
            }
            for (int l = i + 1; l < nums.length - 2; l++) {
                if (l > i + 1 && nums[l] == nums[l - 1]) {
                    continue;
                }
                int m = l + 1;
                int r = nums.length - 1;
                while (m < r) {
                    int sum = nums[i] + nums[l] + nums[m] + nums[r];
                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[l], nums[m], nums[r]));
                        m++;
                        r--;
                        while (m < r && nums[m - 1] == nums[m]) {
                            m++;
                        }
                        while (m < r && nums[r + 1] == nums[r]) {
                            r--;
                        }
                    }
                    if (sum > target) {
                        r--;
                    }
                    if (sum < target) {
                        m++;
                    }

                }
            }
        }

        return result;


    }
}

