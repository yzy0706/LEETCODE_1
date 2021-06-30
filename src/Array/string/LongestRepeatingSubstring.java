package Array.string;

import java.util.HashMap;

public class LongestRepeatingSubstring {

    // 做法: dp做法: double forloop, 如果i-1位置和j-1位置的char一样, dp[i][j] = dp[i - 1][j - 1] + 1;, 然后随时更新res
    // Runtime: O(n^2), Space: O(1)

    public int longestRepeatingSubstring_dp(String S) {
        int len = S.length();
        if(len <= 1) return 0;
        int[][] dp = new int[len + 1][len + 1];
        int res = 0;
        for(int i = 1; i <= len; i ++){
            for(int j = i + 1; j <= len; j++){
                if(S.charAt(i - 1) == S.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    res = Math.max(res, dp[i][j]);
                }
            }
        }
        return res;
    }



    // 做法: 直接拿indexOf(cur, i+1);来做, 过了但时间太长了
    // Runtime: O(n^2), Space: O(1)
    public int longestRepeatingSubstring_2(String S) {
        int len = S.length();
        if(len < 1) return 0;
        int res = 0;
        for(int j = 0; j < len; j ++){
            for(int i = 0; i <= j; i++){
                if(j - i + 1 <= res) break;
                String cur = S.substring(i, j + 1);
                if(S.indexOf(cur, i + 1) != -1) res = j - i + 1;
            }
        }
        return res;
    }


    //拿hashmap做, 超过memory limit了
    public int longestRepeatingSubstring(String S) {
        int len = S.length();
        if(len < 1) return 0;
        int res = 0;
        HashMap<String, Integer> map = new HashMap<>();
        for(int j = 0; j < len; j ++){
            for(int i = 0; i <= j; i++){
                if(j - i + 1 <= res) break;
                String cur = S.substring(i, j + 1);
                map.put(cur, map.getOrDefault(cur, 0) + 1);
                if(map.get(cur) == 2) res = j - i + 1;
            }
        }
        return res;
    }
}
