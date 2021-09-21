package DynamicProgramming.doubleForLoop.longestSubsequence;

public class LongestPalindromicSubseqII {
    // 做法: 用一个三维dp Integer[i][j][x]来代表[i, j]范围内以x字母序号为两端的最长的对称subsequence
    // 1. 建立一个helper, return findSubseq(dp, 0, s.length()-1, 26, s);
    // 2. helper
    // 第一步: 如果dp[i][j][x]已经有值了, return dp[i][j][x];
    // 第二步: 用[i+1, j], [i, j-1]的区间来更新dp[i][j][x]; Math.max(findSubseq(dp, i+1, j, x, s), findSubseq(dp, i, j-1, x, s)); 字母还是当前两端的这个x.
    // 第三步: 如果当前i, j的字母一致而且当前i, j的字母不等于x, 则更新dp[i][j][x] = Math.max(dp[i][j][x], 2 + findSubseq(dp, i+1, j-1, s.charAt(i) - 'a', s));

    //Runtime: O(n^2), Space: O(n^2*27)
    public int longestPalindromeSubseq(String s) {
        Integer[][][] dp = new Integer[s.length()][s.length()][27];
        return findSubseq(dp, 0, s.length()-1, 26, s);
    }

    private int findSubseq(Integer[][][] dp, int i, int j, int x, String s){
        if(dp[i][j][x] != null) return dp[i][j][x];
        if(i >= j) return 0;
        dp[i][j][x] = Math.max(findSubseq(dp, i+1, j, x, s), findSubseq(dp, i, j-1, x, s));
        if(s.charAt(i) == s.charAt(j) && s.charAt(i) - 'a' != x){
            dp[i][j][x] = Math.max(dp[i][j][x], 2 + findSubseq(dp, i+1, j-1, s.charAt(i) - 'a', s));
        }
        return dp[i][j][x];
    }





}
