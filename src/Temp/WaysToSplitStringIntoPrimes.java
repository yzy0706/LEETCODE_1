package Temp;

import java.util.Arrays;

public class WaysToSplitStringIntoPrimes {

    // 做法： 用accumulate的double forloop的dp来做的， if(checkPrime(sub) && !sub.startsWith("0")) dp[j] += dp[i];
    // 1. 建立一个int[] dp = new int[len + 1], dp[0] = 1;
    // 2. 从1开始forloop j到len， 每次再forloop i从0到j-1， 如果s.substring(i, j)是prime, 且s.substring(i, j)不以"0"开头的话，dp[j] += dp[i];
    // 因为s.substring(i, j)是个prime的话， 那么s.substring(0, j)可以被s.substring(0, i)和s.substring(i, j)分隔开来

    // Runtime: O(n^2), Space: O(n)

    public int waysToSplit_Reviewed(String s){
        int len = s.length();
        if(len < 1) return 0;
        int[] dp = new int[len + 1];
        dp[0] = 1;
        for(int j = 1; j <= len; j++){
            for(int i = 0; i < j; i++){
                String sub = s.substring(i, j);
                if(checkPrime(sub) && !sub.startsWith("0")) dp[j] += dp[i];
            }
        }
        return dp[len];
    }

    public boolean checkPrime(String s){
        if(s.length() < 1) return true;
        int num = Integer.parseInt(s);
        for(int i = 2; i*i <= num; i++){
            if(num % i == 0) return false;
        }
        return true;
    }




    //这个题可以分成两部分， 首先第一部分是第一个forloop检查subString(0, i)是不是一个prime， 如果是prime则dp[i] = 1；
    //第二个部分是在这个i的forloop里forloop一个j == 1， 也就是叠加在i上的， 如果 j <= 6 && i + j <= length, 则我们判断
    //subString(0, i+j)是不是prime？  则我们判断如果dp[i+j] == -1，dp[i+j] = dp[i]+1, 否则是 Math.min(dp[i]+1, dp[i+j])
    // Runtime: O(n^2), space: O(n)
    public int waysToSplit(String s){
        int length = s.length();
        int[] dp = new int[s.length()+1];
        Arrays.fill(dp, -1);
        for(int i  = 1; i < length; i++){
            if(i <= 6 && checkPrime(s.substring(0, i))) dp[i] = 1;
            if(dp[i] != -1) {
                for (int j = 1; j <= 6 && i+j <= length; j++) {
                    if(checkPrime(s.substring(j, i+j))){
                        if(dp[i] == -1) dp[i+j] = 1 + dp[i];
                        else dp[i+j] = Math.min(dp[i]+1, dp[i+j]);
                    }

                }
            }
        }
        return dp[length];
    }
}
