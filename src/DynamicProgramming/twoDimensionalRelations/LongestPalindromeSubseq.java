package DynamicProgramming.twoDimensionalRelations;

public class LongestPalindromeSubseq {
// 做法: 用二维的dp去解决longestSubsequence的题, 但这题没有像LargestDivisibleSubset一样额外用一个res来记录最长的subseq,
// 所以我们并不是在跳跃着去找最长的subseq, 而是通过两个forloop一步步更新两个pointer之间各个分段的最长palindrome sequence
    // 1. 建立一个forloop右边端点r, 一直从s.length() - 1到0; dp[l][l] = 1;
    // 2.再建立一个左端点l从r + 1到length();
    // 如果左右端点一样, 则dp[l][r] = dp[l+1][r-1] + 2;
    // 如果左右端点不一样, 则dp[l][r] = Math.max(dp[l][r-1], dp[l+1][r]);
    // 3. return dp[0][s.length()-1];

    // Runtime: O(n^2), Space: O(n^2);
    public int longestPalindromeSubseq_2(String s) {
        if(s.length() < 1) return 0;
        int[][] dp = new int[s.length()][s.length()];
        for(int l = s.length() - 1; l >= 0; l--){
            dp[l][l] = 1;
            for(int r = l + 1; r < s.length(); r++){
                if(s.charAt(l) == s.charAt(r)){
                    dp[l][r] = dp[l+1][r-1] + 2;
                }
                else{
                    dp[l][r] = Math.max(dp[l+1][r], dp[l][r-1]);
                }
            }
        }
        return dp[0][s.length() - 1];
    }








    //第一遍自己写的， 大致思路其实是对的但是我们一般在用dp的时候， 判断条件呢就应该尽可能的少， 无论dp[i][j]是不是完美的堆成我们都应该将这一段最大的palindrome
    //记录下来， 而不是去单单记录是palindrome的地方
    public int longestPalindromeSubseq(String s) {
        if(s.length() < 1) return 0;
        int[][] dp = new int[s.length()][s.length()];
        dp[0][0] = 1;
        int res = 0;

        for(int r = 1; r < s.length(); r++){ //右边先动
            Character tail = s.charAt(r);
            for(int l = 0; l < r; l++){
                if(s.charAt(l) == tail){ //确定好当前subseq的头和尾了
                    for(int i = l + 1; i < r-1; i++){
                        for(int j = i + 1; j < r; j++){
                            dp[l][r] = Math.max(dp[i][j] + 2, dp[l][r]);
                        }
                    }
                    if(dp[l][r] == 2){
                        dp[l][r] ++;
                    }
                    res = Math.max(dp[l][r], res);
                }
            }
        }

        return res;
    }
}
