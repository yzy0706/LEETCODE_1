package DynamicProgramming.divideToSubproblems;

public class BurstBallons {
    //做法: 建立一个dp[i][j]. 来表示[i, j]区间从谁开始burst所获收益最大, 最开始的区间是 0 到 nums.length-1; 也是拆成以i为界的两个子问题的题
    //1. start > end　直接 return 0;
    //2. dp[start][end] != 0 直接return dp;
    //3. 从start到end forloop, 不断更新 dp[start][end] = findMaxCoins(nums, start, i-1, dp) + findValue(nums, start - 1) (区间左边最近的数) * findValue(nums, i) * findValue(nums, end+1); (区间右边最近的数)

    //Runtime: 每次都要检查每一个i的左右两边, 所以看作二叉树是 O(nlog(n) * n)次forloop, 所以是O(n^2*log(n)), space是O(n^2)

    public int maxCoins(int[] nums) {
        int[][] dp = new int[nums.length][nums.length];
        return findMaxCoins(nums, 0, nums.length-1, dp);
    }

    public int findMaxCoins(int[] nums, int start, int end, int[][] dp){
        if(start > end){
            return 0;
        }

        if(dp[start][end] != 0){
            return dp[start][end];
        }

        int maxThisRange = nums[start];
        for(int i = start; i <= end; i++){
            int iCoins = findMaxCoins(nums, start, i-1, dp) + findValue(nums, start-1) * findValue(nums, i)* findValue(nums, end+1) + findMaxCoins(nums, i+1, end, dp);
            maxThisRange = Math.max(maxThisRange, iCoins);
        }
        dp[start][end] = maxThisRange;
        return dp[start][end];
    }



    public int findValue(int[] nums, int i){
        if(i >= 0 && i < nums.length) return nums[i];
        return 1;
    }
}
