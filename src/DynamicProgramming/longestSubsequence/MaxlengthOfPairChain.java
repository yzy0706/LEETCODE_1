package DynamicProgramming.longestSubsequence;

import java.util.Arrays;

public class MaxlengthOfPairChain {
    //做法: sort + dp的做法, 根据每一个pairs[j][1] < pairs[i][0] 来更新 dp[i] = Math.max(dp[i], dp[j]+1);
    // 1. 看到interval, 先把pairs全部都根据pair[0] sort一下, 建立一个dp, 所有的数都等于1
    // 2. forloop一个i从1到最后, j从0到i, 比较 pairs[i][0] > pairs[j][1],
    // 如果是大于的那么 dp[i] = Math.max(dp[i], dp[j]+1);  res = Math.max(res, dp[i]);
    // return res;
    // Runtime: O(n^2), Space: O(n)
    public int findLongestChain(int[][] pairs) {
        if(pairs.length < 1) return 0;
        Arrays.sort(pairs, (a, b) -> (a[0] - b[0]));
        int[] dp = new int[pairs.length];
        Arrays.fill(dp, 1);
        int res = 1;
        for(int i = 1; i < pairs.length; i++){
            for(int j = 0; j < i; j++){
                if(pairs[i][0] > pairs[j][1]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                    res = Math.max(res, dp[i]);
                }
            }
        }
        return res;
    }
}
