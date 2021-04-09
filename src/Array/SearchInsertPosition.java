package Array;

public class SearchInsertPosition {
    // 做法: 基础的二分法, return target所在的位置或者最后一个l
    // Runtime: O(log(n)), Space: O(1);

    public int searchInsert(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] > target) r = mid - 1;
            else l = mid + 1;
        }
        return l;
    }
}

