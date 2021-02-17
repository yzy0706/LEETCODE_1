package DynamicProgramming.accumulate;

public class LongestCommonSubseq {
    //做法: 用的主要是dp做法, 思路跟longestPaindromeSubseq有点类似,
    //这种题符合增加的调节就是之前的结果+1;
    //在不符合叠加条件的时候都要善用Math.max(dp[i+1][j], dp[i][j+1]), 也就是包含两端中的一端的两种dp的最大值
    // 1. forloop text1和text2的pointer i和j
    // 2. 如果text1.charAt(i) == text2.charAt(j), dp[i+1][j+1] = dp[i][j] + 1;
    //    如果两个不相等的话, dp[i+1][j+1] = Math.max(dp[i][j+1], dp[i+1][j]);

    //Runtime: O(mn), Space: O(mn);
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length()+1][text2.length()+1];
        for(int i = 0; i < text1.length(); i++){
            for(int j = 0; j < text2.length(); j++){
                if(text1.charAt(i) == text2.charAt(j)){
                    dp[i+1][j+1] = dp[i][j] + 1;
                }
                else{
                    dp[i+1][j+1] = Math.max(dp[i][j+1], dp[i+1][j]);
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }
}
