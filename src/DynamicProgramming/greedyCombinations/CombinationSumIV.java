package DynamicProgramming.greedyCombinations;

public class CombinationSumIV {
    //Hint: 碰到有target求不同组合的问题, 一定要利用好target和每一个元素之间的差, 去容纳其他组合


    //做法: 设立一个从0到target的int[] dp, dp[0] = 1; 因为0只能被0组成
    //1. 从1 forloop到 targe
    //2. 每次都forloop一遍所有的nums, 如果i - nums[j] >= 0, 证明这个数能被nums[j]和其他数组合而成, dp[i] += dp[i - nums[j]];
    //3. 最后return dp[target]; 就是所有组成target的可能

    //Runtime: target大小为n, nums长度为m, O(mn), space: O(n)
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target+1];
        dp[0] = 1;
        for(int i = 1; i <= target; i++){
            for(int j = 0; j < nums.length; j++){
                if(i - nums[j] >= 0){
                    dp[i] += dp[i - nums[j]];
                }
            }
        }
        return dp[target];
    }
}
