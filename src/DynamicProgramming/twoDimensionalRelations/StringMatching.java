package DynamicProgramming.twoDimensionalRelations;

public class StringMatching {
    //做法: 这个题用一个dp[i][j]来表示0到i和0到j的两个subString是不是一样, 所以每个i, j代表 i-1, j-1的character
    // 1. dp[0][0] = true, 并且先forloop一下j把所有 j-1是 '*'的位置都 dp[0][j] = dp[0][j-2];
    // 2. 分别对于i, j双重forloop:
    //   a. 当前j-1的位置是"."或者 j-1和i-1位置一样时: dp[i][j] = dp[i-1][j-1];
    //   b. 当前j-1的位置是'*',
    //     i. 当前可以有没有要复制的数因为i-1的位置是空的: dp[i][j] = dp[i][j-2]
    //    ii. 我们需要 j-2的位置和当前i-1一样: dp[i][j] = p.charAt(j-2) == s.charAt(i-1) && dp[i-1][j]
    //    iii. 或者j-2的位置是一个‘.’可以是任何字符, 所以 dp[i][j] = p.charAt(j-2) == '.' && dp[i-1][j]
    // 所以总的来说 dp[i][j] = dp[i][j-2] || (((p.charAt(j-2) == s.charAt(i-1)) || p.charAt(j-2) == '.') && dp[i-1][j]);

    //Runtime: O(mn) (两个string的长度双重forloop), space: O(mn), 一个二维的matrix


    public boolean isMatch(String s, String p) {
        if(p == null) return s == null;
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for(int j = 2; j <= p.length(); j++){
            dp[0][j] = p.charAt(j-1) == '*' && dp[0][j-2]; //先把p上是"*"的标记成true
        }

        for(int j = 1; j <= p.length(); j++){
            for(int i = 1; i <= s.length(); i++){
                if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.'){ //两个字符相等或者p上是‘.’, 就是任意字符
                    dp[i][j] = dp[i-1][j-1]; //直接递增
                }
                else if(p.charAt(j-1) == '*'){
                    dp[i][j] = dp[i][j-2] || (((p.charAt(j-2) == s.charAt(i-1)) || p.charAt(j-2) == '.') && dp[i-1][j]);
                    //当前p是'*',则
                    //1. 当前i还是0, 就是第一个, 直接'*'当empty用
                    //2. 如果p前一个就跟s相等, 或者p前一个干脆等于'.', 也就是任意数
                }
            }

        }
        return dp[s.length()][p.length()];
    }
}
