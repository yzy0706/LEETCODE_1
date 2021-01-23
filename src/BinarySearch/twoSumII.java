package BinarySearch;
public class twoSumII {
    int target;
    public int twoSum(int[] nums,int target){
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
