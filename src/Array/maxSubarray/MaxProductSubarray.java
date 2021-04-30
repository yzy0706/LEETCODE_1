package Array.maxSubarray;

public class MaxProductSubarray {
    //做法: double array, 一个记录每个位置的min, 一个记录没给值的max, 从位置1开始
    // 1. 如果nums[i] < 0:
    //      max[i] = Math.max(min[i-1] * nums[i], nums[i]);
    //      min[i] = Math.min(max[i-1] * nums[i], nums[i]);
    // 注意: 记得比较nums[i], 因为nums[i]有可能是0
    // 2. 如果nums[i] >= 0
    //      max[i] = Math.max(max[i-1] * nums[i], nums[i]);
    //      min[i] = Math.min(min[i-1] * nums[i], nums[i]);

    // 随时比较res和max[i];

    // Runtime: O(n), Space: O(n)

    public int maxProduct_reviewed(int[] nums) {
        if(nums.length < 1) return 0;
        int[] max = new int[nums.length];
        int[] min = new int[nums.length];
        max[0] = nums[0];
        min[0] = nums[0];
        int res = nums[0];
        for(int i = 1; i < nums.length; i++){
            if(nums[i] < 0){
                max[i] = Math.max(min[i-1] * nums[i], nums[i]);
                min[i] = Math.min(max[i-1] * nums[i], nums[i]);
            }
            else{
                max[i] = Math.max(max[i-1] * nums[i], nums[i]);
                min[i] = Math.min(min[i-1] * nums[i], nums[i]);
            }
            res = Math.max(res, max[i]);
        }
        return res;
    }



    //因为每一个数字都有正负之分， 所以我们需要用一个maxdp和一个mindp来记录当前的乘积， 如果当前的数是<0, maxdp[i]就是当前的数乘以min[i-1]和nums[i]的比较
    //反之mindp就是当前的数乘以mindp[i-1]和nums[i]的比较， 最后再找出maxdp里面最大的值return
    //Runtime: 所有的都浏览一遍就是O(n), space有两个int[]就是O(2n)也就是O(n)
    public int maxProduct(int[] nums) {
        if (nums.length < 1) return 0;
        int[] max = new int[nums.length + 1];
        int[] min = new int[nums.length + 1];
        max[0] = nums[0];
        min[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                max[i] = Math.max(min[i - 1] * nums[i], nums[i]);
                min[i] = Math.min(max[i - 1] * nums[i], nums[i]);
            } else {
                max[i] = Math.max(max[i - 1] * nums[i], nums[i]);
                min[i] = Math.min(min[i - 1] * nums[i], nums[i]);
            }
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (max[i] > res) res = max[i];
        }
        return res;
    }
}
