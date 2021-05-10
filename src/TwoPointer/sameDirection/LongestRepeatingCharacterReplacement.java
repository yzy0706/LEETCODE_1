package TwoPointer.sameDirection;

public class LongestRepeatingCharacterReplacement {
    // 想法: 我用的同向双指针的做法, 思路如下
    // a. 我们有一个string, 最多做k次改变, 一开始想是不是要找k个不同的substring(k distinct), 所以想到了同向双指针
    // b. 后来发现只要每一段的substring长度 - 最多的相同的字母个数 <= k 就行, 所以用一个curMost记录每一段最多的相同的字母

    // 做法:
    // 1. 用两个指针i, j = 0作为初始值, 用mostSame = 0记录当前substring最多的相同字母
    // 2. 在j forloop的过程中,
    //  a. 记录j的字母的频率, 更新curMost
    //  b. 用一个while(j - i + 1 - curMost > k)的whileloop, 移动i, 随着i所在位置的字母频率减少, 更新i字母的频率, 并且更新curMost
    //  c. 更新res

    // Runtime: O(n), Space: O(26)


    public int characterReplacement(String s, int k) {
        int i = 0, j = 0, len = s.length(), res = 0, mostSame = 0;
        int[] freq = new int[26];
        for(j = 0; j < len; j++){
            Character cur = s.charAt(j);
            freq[cur - 'A'] ++;
            if(freq[cur - 'A'] > mostSame) mostSame = freq[cur - 'A'];
            while(j - i + 1 - mostSame > k){
                Character left = s.charAt(i);
                freq[left - 'A'] --;
                for(int n = 0; n < 26; n++) if(freq[n] > mostSame) mostSame = freq[n];
                i ++;
            }
            res = Math.max(res, j - i + 1);
        }
        return res;
    }
}
