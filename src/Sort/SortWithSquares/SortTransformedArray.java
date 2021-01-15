package Sort.SortWithSquares;

public class SortTransformedArray {
    //做法: 看了答案的, 建立一个int[] res, 如果 a >= 0, 比较i, j计算后大小从右边开始放，因为左边最小的数可能到最右边去; 否则从左边开始放
    //Runtime: O(N), space: O(N);
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        if(nums.length == 1) return nums;
        int i = 0, j = nums.length - 1;
        int startIndex = a >= 0? j : i;
        int[] res = new int[nums.length];
        while(i <= j){
            if(a >= 0) res[startIndex--] = calculate(nums[i], a, b, c) >= calculate(nums[j], a, b, c)?  calculate(nums[i++], a, b, c) : calculate(nums[j--], a, b, c);
            else res[startIndex++] = calculate(nums[i], a, b, c) < calculate(nums[j], a, b, c)?  calculate(nums[i++], a, b, c) : calculate(nums[j--], a, b, c);
        }
        return res;
    }


    public int calculate(int i, int a, int b, int c){
        return a*i*i + b*i + c;
    }
}
