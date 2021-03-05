package DynamicProgramming.longestSubsequence;

import java.util.Arrays;

public class MinASCIIDeleteSumForTwoStrings {
    // 做法: 2d的dp做法, 跟LongestCommonSequence很像, dp[i+1][j+1]代表到i-1, j-1这个index之前最少删除了的ASCII值的总和
    // 1. forloop所有i和j等于0的可能, 也就是当i == 0的时候证明只有s2, 就是当前s2到第j位的所有字母的ascii值的和; 同理当j==0的时候证明只有s1, 就是到第i位的所有字母的ascii总和
    // 2. 然后再进行dp:
    //      1. 如果当前i位和j位的字母是一样的, dp[i+1][j+1] = dp[i][j]; 不用删除任何数
    //      2. 如果i和j字母不一样, dp[i+1][j+1] = Math.min(dp[i+1][j](只删j位) + (int)s2.charAt(j), dp[i][j+1](只删i位) + (int)s1.charAt(i));
    //Runtime: O(mn), Space: O(mn)
    public int minimumDeleteSum(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        if(len1 == 0 && len2 == 0) return 0;
        int[][] dp = new int[len1 + 1][len2 + 1];
        for(int i = 0; i < len1; i++) dp[i+1][0] = dp[i][0] + (int)s1.charAt(i);
        for(int j = 0; j < len2; j++) dp[0][j+1] = dp[0][j] + (int)s2.charAt(j);

        for(int i = 0; i < len1; i++){
            for(int j = 0; j < len2; j++){
                if(s1.charAt(i) == s2.charAt(j)){
                    dp[i+1][j+1] = dp[i][j];
                }
                else{
                    dp[i+1][j+1] = Math.min(dp[i][j+1] + (int)s1.charAt(i), dp[i+1][j] + (int)s2.charAt(j));
                }
            }
        }
        return dp[len1][len2];
    }




    public int minimumDeleteSum_2(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        if(len1 == 0 && len2 == 0) return 0;
        int[][] dp = new int[len1 + 1][len2 + 1];
        int s2Sum = 0, s1Sum = 0;
        for(int i = 0; i < s1.length(); i++) s1Sum += (int)s1.charAt(i);
        for(int i = 0; i < s2.length(); i++) s2Sum += (int)s2.charAt(i);
        Arrays.fill(dp[0], s2Sum);

        for(int i = 0; i < len1; i++){
            for(int j = 0; j < len2; j++){
                if(j == 0){
                    dp[i][j] = s1Sum;
                }
                else if(s1.charAt(i) == s2.charAt(j)){
                    dp[i+1][j+1] = dp[i][j];
                }
                else{
                    dp[i+1][j+1] = Math.min(dp[i][j+1] + (int)s1.charAt(i), dp[i+1][j] + (int)s2.charAt(j));
                    dp[i+1][j+1] = Math.min(dp[i][j], dp[i][j] + (int)s1.charAt(i) + (int)s2.charAt(j));
                }
            }
        }
        return dp[len1][len2];
    }
}
