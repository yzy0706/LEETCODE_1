package DynamicProgramming.accumulate;

public class PalindromeSubstrings {
    //做法: palindromeSubstring题型:
    //这题跟longestPalindromeSubstring类似, 但不能继续用dp[l][r]来记录一段substring里面有多少个palindrome subtring, 这种结果是没法叠加给下一次利用的, 在这里用我们用的是boolean[][], 如果dp[l][r] == true, 代表这一段是完整的palindrome,
    //所以在substring题型里dp也不一定是直接储存个数
    // 1. 正常进行substring的循环, l从s.length() - 1开始--, r从l+1开始++
    // 2. 如果s.charAt(l) == s.charAt(r), 则如果r-l+1 <= 2或者他们包裹的这部分dp[l+1][r-1] = true, 代表当前l到r就是palindrome, 则res++, 最后return res;
    public int countSubstrings(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        int res = 0;
        for(int l = s.length() - 1; l >= 0; l--){
            for(int r = l; r < s.length(); r ++){
                if(s.charAt(l) == s.charAt(r)){
                    if(r - l + 1 < 3 || dp[l+1][r-1]){
                        dp[l][r] = true;
                        //如果l到r长度小于等于2或者他们中间的这部分也是对称的就多了一个palindrome
                        res ++;
                    }
                }
            }
        }
        return res;
    }
}
