package DivideAndConquer;

public class MaxSubArray {
    // DivideAndConquer解法中心就是把这个int[]拆成左右两部分，分別找左、右和左右共用的的最大sum，
    // 然后再继续分下去直到 left == right
    public int maxSubArray(int[] nums){
        return divide(nums, 0, nums.length - 1);
    }

    public int divide(int[] nums, int l, int r){
        if(l == r) return nums[l];
        int m = (l + r)/2;

        int leftSum = divide(nums, l, m);
        int rightSum = divide(nums, m+1, r); //这里记得要写m+1不然会overflow， 就跟二分法的原理一樣
        int commonSum = commonSum(nums, l, r, m);

        return Math.max(commonSum, Math.max(leftSum, rightSum));
    }

    public int commonSum(int[] nums, int l, int r, int m){
        if(l == r) return nums[l];

        int cur  = 0;
        int leftPart = Integer.MIN_VALUE;
        for(int i = m; i > l - 1; i --){
            cur += nums[i];
            leftPart = Math.max(leftPart, cur);
        }

        cur  = 0;
        int rightPart = Integer.MIN_VALUE;
        for(int i = m+1; i < r + 1; i ++){
            cur += nums[i];
            rightPart = Math.max(rightPart, cur);
        }

        return leftPart + rightPart;
    }
}
