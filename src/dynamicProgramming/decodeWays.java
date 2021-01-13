package dynamicProgramming;

public class decodeWays {
    //dp[i] = dp[i-1] + dp[i-2]; 总结来说就是把0，1位置都设置为1， 前一位不是0对的话加上dp[i-1]， 前两位<26 && >10的话加上dp[i-2]
    public int numDecodings(String s) {
        if(s == null || s.length() == 0) return 0;
        int[] dp  = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;

        for(int i = 2; i < dp.length; i ++){
            if(s.charAt(i-1) != '0' ){
                dp[i] += dp[i-1];
            }

            int lastTwo = Integer.valueOf(s.substring(i-2, i));

            if(lastTwo >= 10 && lastTwo <= 26){
                dp[i] += dp[i-2];
            }

        }

        return dp[s.length()];
    }
}

