package DynamicProgramming.accumulate;

public class UniqueSubstring {
    //做法: 中心就是用到了一个定律: 无论这个String p里面有多少条以某个字母'a'为结尾的substring(这里以'a'代替所有字母), 所有能在s上连续的以‘a'为结尾的不同substring的个数和 = 找到的最长的以'a'为结尾的substring的长度;
    // 1. 先建立一个记录以所有字母为结尾的unique substring个数的int[] dp = new int[26];  建立一个参数maxLenEndWithChar记录当前以cl[i]结尾的最长字符串长度
    // 2. forloop p上所有的字符, 如果跟前面是连续的字母, maxLenEndWithChar ++; 否则重置maxLenEndWithChar = 1,
    // 然后更新一下dp[cl[i] - 'a'] = Math.max(dp[cl[i] - 'a'], maxLenEndWithChar);
    // 3. 把dp上所有的数加起来, 也就是以所有可能的字母为结尾的不同字符串的个数的和

    //Runtime : O(n), Space: O(26)
    public int findSubstringInWraproundString(String p) {
        int[] dp = new int[26];
        char[] cl = p.toCharArray();
        int maxLenEndWithChar = 0;
        for(int i = 0; i < p.length(); i++){
            if(i > 0 && (cl[i] - cl[i-1] == 1 || cl[i] - cl[i-1] == -25)){
                maxLenEndWithChar ++;
            }
            else{
                maxLenEndWithChar = 1;
            }
            int alphabetIdx = cl[i] - 'a';
            dp[alphabetIdx] = Math.max(dp[alphabetIdx], maxLenEndWithChar);
            // 更新一下dp[i], 看看当前找到的这个以cl[i]结尾的连续的最长的substring是不是比之前找到的dp[i]还长
        }

        int res = 0;
        for(int i : dp){
            res += i;
        }

        return res;
    }
}
