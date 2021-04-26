package TwoPointer.diffrentDirection;
public class TwoSumII {
    // 做法: 这是个two pointer的题, 不是binary search
    // Runtime: O(n), Space: O(1)
    public int[] twoSum_reviewed(int[] numbers, int target) {
        int l = 0, r = numbers.length - 1;
        while(l < r){
            int sum = numbers[l] + numbers[r];
            if(sum == target) return new int[]{l+1, r+1};
            if(sum < target) l++;
            if(sum > target) r--;
        }
        return new int[]{0, 0};
    }


    int target;
    public int[] twoSum(int[] nums,int target){
        int[] res = new int[2];
        int a  = 1;
        int b = nums.length;
        while (b > a) {
            int sum = nums[a-1] + nums[b-1];
            if (sum > target) b--;
            else if (sum < target) a++;
            else {
                res[0] = a;
                res[1] = b;
            }

        }
        return res;
    }
}
