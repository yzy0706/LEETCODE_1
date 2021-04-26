package Array.string;

public class LongestPalindromicString {
    // 做法: 这一遍是用two pointer做的,
    // 1. forloop整个s, 从每个i位置开始, 先把左右跟当前字符一样的位置全部用l和r移动一下
    // 2. 然后l和r已经是和i位置的字符不一样的字符的位置了, 如果l和r位置的字符一样, 继续向左右延伸, 最后再把l和r返回来一个移动的位置, 因为最后的时刻l和r的字符应该是不相等的

    // Runtime: O(n^2), Space: O(1)

    public String longestPalindrome_twoPointer(String s) {
        if(s.length() == 1) return s;
        String res = s.substring(0, 1);
        for(int i = 0; i < s.length(); i++){
            Character cur = s.charAt(i);
            int l = i, r = i;
            while(l >= 0 && s.charAt(l) == cur) l --;
            while(r < s.length() && s.charAt(r) == cur) r++;
            while(l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)){
                r++;
                l--;
            }
            r--;
            l++;
            if(r - l + 1 > res.length()) res = s.substring(l, r + 1);
        }
        return res;
    }



    // 做法: 这一遍用dp做的, 就正常forloop j, 再往前forloop i, 然后看dp[i+1][j-1]能不能叠加给dp[i][j], 如果dp[i+1][j-1]是0的话就没办法叠加
    // Runtime: O(n^2), Space: O(n^2)

    public String longestPalindrome_dp(String s) {
        if(s.length() == 1) return s;
        int[][] dp = new int[s.length()][s.length()];
        String res = s.substring(0, 1);
        for(int j = 0; j < s.length(); j++){
            dp[j][j] = 1;
            for(int i = j - 1; i >= 0; i--){
                if(s.charAt(i) == s.charAt(j)){
                    if(i+1 <= j-1 && dp[i+1][j-1] == 0){
                        continue;
                    }
                    if(i + 1 <= j - 1){
                        dp[i][j] = dp[i+1][j-1] + 2;
                    }
                    else{
                        dp[i][j] = 2;
                    }
                }
                if(dp[i][j] > res.length()){
                    res = s.substring(i, j + 1);
                }
            }
        }
        return res;
    }




    public String longestPalindrome(String s) {
        String res = "";
        for(int i = 0; i < s.length(); i++){
            int l = i, r = i;
            while(l >= 0 && r < s.length()){
                if(s.charAt(l) == s.charAt(r)){
                    if(r - l + 1 > res.length()) res = s.substring(l, r + 1);
                    l --;
                    r ++;
                }
            }
        }
        return res;
    }
}
