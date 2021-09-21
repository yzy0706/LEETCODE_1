package DynamicProgramming.doubleForLoop.greedyCombinations;

public class TargetSum {
    //做法: 这题主要也是背包问题, 用的combination, int[] dp = new int[2 * sum + 1];  dp[i]代表和为i在这个等式中一共有几种可能
    // 1. 求出所有nums上的num的和sum, 建立一个dp, dp[sum] = 1;
    // 2. 浏览nums上所有的数, 对于每一个数, 我们要新建一个和dp一样大小的空的int[] next用作combination, 而不是直接用dp
    // 3. forloop j从0到 2*sum+1, 如果当前dp[j] != 0, 代表之前有其他数的和等于j, 则 next[j + num] += dp[j] 种可能, next[j - num] += dp[j];
    // 4. forloop完所有j的可能以后再把当前的结果赋予dp, dp = next
    // 5. return dp[sum + S];

    // Runtime:  nums.length = n; sum = m, 则为 O(mn); Space: O(m);

    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for(int i : nums){
            sum += i;
        }
        if(S > sum || S < -sum) return 0;
        int[] dp = new int[2 * sum + 1];
        dp[sum] = 1;

        for(int num : nums){
            int[] next = new int[2*sum+1];
            for(int j = 0; j < 2 * sum + 1; j++){
                if(dp[j] != 0){
                    next[j + num] += dp[j];
                    next[j - num] += dp[j];
                }
            }
            dp = next;
        }

        return dp[sum + S];
    }





    public int findTargetSumWays_2(int[] nums, int S) {
        int sum = 0;
        for(int i : nums){
            sum += nums[i];
        }
        if(S > sum || S < -sum) return 0;
        int[] dp = new int[2 * sum + 1];
        dp[sum] = 1;

        for(int i : nums){
            int[] next = new int[2 * sum + 1];
            for(int j = 0; j < 2 * sum + 1; j++){
                if(dp[j] != 0){
                    next[j + i] += dp[j];
                    next[j - i] += dp[j];
                }
            }
            dp = next;
        }

        return dp[sum + S];
    }
}
