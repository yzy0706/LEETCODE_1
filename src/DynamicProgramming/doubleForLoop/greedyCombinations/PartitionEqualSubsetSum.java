package DynamicProgramming.doubleForLoop.greedyCombinations;

public class PartitionEqualSubsetSum {
    //做法: 这题是个背包问题(greedy combinations),
    // 1. 先求出所有nums的和 sum
    // 2. 如果sum不能整除2, 那代表没法平分, 直接return false; 可以整除的话, sum /= 2;
    // 3. 建立一个boolean[] dp = new boolean[sum], dp[0] = true, 也就是总的和的一半的大小;
    // 4. 这时候就是正常的combnations的解法, 因为是按顺序排列, 我们关注从nums[i]开始的数能不能被nums[i]和别的数组合
    // 先forloop所有的nums[i], 再forloop 从sum开始到nums[i], 如果 dp[j - nums[i]] = true的话, 证明nums[i]可以和前面已知的一个数一起组合成j, 所以dp[j] = true;
    // 因为同一个数只能用一次所以这个题j只能从sum到nums[i]从右到左, 不能像一般的一样从左到右
    //Runtime: O(n^2), n = sum/2, space: O(n);

    public boolean canPartition_solutionFixed(int[] nums) {
        int sum = 0;
        for(int i : nums) sum += i;
        if(sum % 2 != 0) return false;
        sum /= 2;
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;

        for(int i = 0; i < nums.length; i++){
            for(int j = sum; j >= nums[i]; j--){
                if(dp[j - nums[i]] == true) dp[j] = true;
            }
        }

        return dp[sum];
    }









    // discussion的解法， 不统一
    //做法: 这题是个背包问题(greedy combinations),
    // 1. 先求出所有nums的和 sum
    // 2. 如果sum不能整除2, 那代表没法平分, 直接return false; 可以整除的话, sum /= 2;
    // 3. 建立一个boolean[] dp = new boolean[sum], dp[0] = true, 也就是总的和的一半的大小; forloop 浏览每一个数nums[i], 这其中再forloop浏览从sum到nums[i]的每一个数, dp[j] = dp[j] || dp[j - nums[i]];
    // 主要是在看nums[]里有没有几个数的和可以等于sum的一半, 其他的我就不用看了


    //Runtime: O(n^2), n = sum/2, space: O(n);
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int i : nums) sum += i;
        if(sum % 2 != 0) return false;
        sum /= 2;
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;

        for(int i = 0; i < nums.length; i++){
            for(int j = sum; j >= nums[i]; j--){
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }

        return dp[sum];
    }
}
