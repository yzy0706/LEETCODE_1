package binarySearch;

public class rotatedSortedArray {
    public int search(int[] nums, int target){
        return find(nums,target, 0 ,nums.length-1);
    }

    public int find (int[] nums, int target, int start, int end){
        if(start > end) return -1;
        int mid = (start + end) /2;
        if(nums[mid] == target) return mid;
        if(nums[mid] >= nums[start]){
            if(target >= nums[start] && target <= nums[mid]) return find(nums,target,start,mid-1);
            else return find(nums,target,mid+1,end);
        }
        if(target >= nums[mid] && target <= nums[end]) return find(nums,target,mid+1,end);
        else return find(nums,target,start,mid-1);  //注意这里最好都写>=或者<=， 避免出现bug
    }





//看不懂的解法
public int search_2(int[] nums, int target) {

}

    if (nums == null | nums.length == 0) {
        return -1;
    }

    if (nums.length == 1) {
        if(nums[0] == target) {
            return 0;
        } else {
            return -1;
        }
    }

    int index = findPivot(nums);

    if (index == 0) {
        return binarySearch(nums, 0, nums.length - 1, target);
    }

    int result = binarySearch(nums, 0, index - 1, target);
    if (result == -1) {
        return binarySearch(nums, index, nums.length - 1, target);
    } else {
        return result;
    }

}

    private int findPivot(int [] nums) {

        if (nums[0] < nums[nums.length - 1]) {
            return 0;
        }

        int start = 0; int end = nums.length - 1;

        while(start <= end) {

            int mid = start + (end - start)/2;

            if (mid < nums.length - 1 && nums [mid] > nums[mid + 1]){
                return (mid + 1);
            } else if (nums[start] <= nums[mid]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }

        }

        return 0;

    }

    private int binarySearch(int [] nums, int start, int end, int target) {

        if (start > end) {
            return -1;
        }

        if (start == end) {
            if (nums[start] != target) {
                return -1;
            } else {
                return start;
            }
        }

        int mid = start + (end - start) / 2;

        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] > target) {
            return binarySearch(nums, start, mid - 1, target);
        } else {
            return binarySearch(nums, mid + 1, end, target);
        }

    }

    //leetcode给的解法

        int [] nums;
        int target;

        public int find_rotate_index(int left, int right) {
            if (nums[left] < nums[right])
                return 0;

            while (left <= right) {
                int pivot = (left + right) / 2;
                if (nums[pivot] > nums[pivot + 1])
                    return pivot + 1;
                else {
                    if (nums[pivot] < nums[left])
                        right = pivot - 1;
                    else
                        left = pivot + 1;
                }
            }
            return 0;
        }

        public int search(int left, int right) {
    /*
    Binary search
    */
            while (left <= right) {
                int pivot = (left + right) / 2;
                if (nums[pivot] == target)
                    return pivot;
                else {
                    if (target < nums[pivot])
                        right = pivot - 1;
                    else
                        left = pivot + 1;
                }
            }
            return -1;
        }

        public int search(int[] nums, int target) {
            this.nums = nums;
            this.target = target;

            int n = nums.length;

            if (n == 0)
                return -1;
            if (n == 1)
                return this.nums[0] == target ? 0 : -1;

            int rotate_index = find_rotate_index(0, n - 1);

            // if target is the smallest element
            if (nums[rotate_index] == target)
                return rotate_index;
            // if array is not rotated, search in the entire array
            if (rotate_index == 0)
                return search(0, n - 1);
            if (target < nums[0])
                // search in the right side
                return search(rotate_index, n - 1);
            // search in the left side
            return search(0, rotate_index);
        }



