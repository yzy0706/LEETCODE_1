package DynamicProgramming.doubleArray;

import java.util.Arrays;

public class FindNumberOfLongestIncreasingSubsequence {
//做法: dp[i]表示到i的最长的increasing subsequence的长度, cnt[i]表示到i的subsequence的可能
    // 1. forloop j 从0到nums.length-1, forloop i从0到j-1
    // 2. 当nums[j] > nums[i]的情况下
    //    a. 如果dp[i] + 1 = dp[j] 那代表i正好就是j之前的那个, cnt[j] += cnt[i]; j的数量加上i的数量
    //    b. 如果dp[i] + 1 > dp[j] 证明j这里找到更长的连续增长的subsequence了, dp[j] = dp[i] + 1; cnt[j] = cnt[i]
    // 3. forloop 完了以后更新maxLen,
    // a. if(dp[j] == maxLen) res += cnt[j];
    // b. if(dp[j] > maxLen) maxLen = dp[j]; res = cnt[j];

    // Runtime: O(n^2), Space: O(n)
    public int findNumberOfLIS_doubleArray(int[] nums) {
        int maxLen = 1;
        int res = 0;
        int[] dp = new int[nums.length];
        int[] cnt = new int[nums.length];
        Arrays.fill(dp, 1);
        Arrays.fill(cnt, 1);
        for(int j = 0; j < nums.length; j++){
            for(int i = 0; i < j; i++){
                if(nums[j] > nums[i]){
                    if(dp[j] == dp[i] + 1){
                        cnt[j] += cnt[i]; //如果正好是dp[i] + 1就是找到连续的sequence了
                    }
                    if(dp[j] < dp[i] + 1){
                        dp[j] = dp[i] + 1;
                        cnt[j] = cnt[i];
                    }
                }
            }
            if(maxLen == dp[j]){
                res += cnt[j];
            }
            if(dp[j] > maxLen){
                maxLen = dp[j];
                res = cnt[j];
            }
        }
        return res;
    }




    //第一遍自己写的
    public int findNumberOfLIS(int[] nums) {
        int maxLen = 1;
        int[] dp = new int[nums.length];
        int[] cnt = new int[nums.length];
        Arrays.fill(dp, 1);
        for(int i = 0; i < nums.length; i++){
            // int last = i;
            for(int j = i + 1; j < nums.length; j++){
                if(nums[j] > nums[i]){
                    if(dp[i] + 1 > maxLen){

                    }
                    dp[j] = Math.max(dp[j], dp[i] + 1);
                    maxLen = Math.max(maxLen, dp[j]);
                }
            }
        }
        // System.out.println(maxLen);
        int res = 0;

        return res;
    }
}
