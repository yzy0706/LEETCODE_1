package Temp;

import java.util.Arrays;

public class WaysToSplitStringIntoPrimes {
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

    public boolean checkPrime(String s){
        if(s.length() < 1) return true;
        int num = Integer.parseInt(s);
        for(int i = 2; i*i <= num; i++){
            if(num % i == 0) return false;
        }
        return true;
    }
}
