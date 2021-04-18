package TwoPointer.sameDirection;

import java.util.HashMap;

public class LengthOfLongestSubstringKDistinct {
    // 后来自己做了一遍
    // 做法: 用同向的双指针加上HashMap<Character, Integer> freq来做的, 因为s上的字符不一定是英文字母所以必须得用hashmap而不是int[]来记录频率
    // 1. j先出发, 记录每一个字母的频率, 如果当前char在hashmap里面还没出现过, type++
    // 2. 用一个whileloop, 当i < j && type > k的时候, 把当前i, 也就是最左边的char去掉, 如果它的频率变为0了, 把当前这个char整个从hashmap里面删掉, 字符的种类也--
    // 3. 每次不管进不进入whileloop都最后更新一下res
    // Runtime: O(n^2), Space: O(n)
    public int lengthOfLongestSubstringKDistinct_reviewed(String s, int k) {
        if(k == 0) return 0;
        if(s.length() == 1) return 1;
        int len = s.length(), i = 0, j = 0, type = 0, res = 0;
        HashMap<Character, Integer> freq = new HashMap<>();
        while(j < len){
            Character cur = s.charAt(j);
            if(!freq.containsKey(cur)) type ++;
            freq.putIfAbsent(cur, 0);
            freq.replace(cur, freq.get(cur) + 1);
            while(i < j && type > k){
                Character lc = s.charAt(i);
                freq.replace(lc, freq.get(lc) - 1);
                if(freq.get(lc) == 0){
                    type --;
                    freq.remove(lc);
                }
                i++;
            }
            res = Math.max(res, j - i + 1);
            j++;
        }
        return res;
    }






    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        char[] cl = s.toCharArray();
        int l = 0;
        int r = 0;
        int[] cur = new int[256];
        int C = 0;
        int res = 0;
        int ansr = -1;
        int ansl = -1;
        if(cl.length == 1 &&  k == 1) return 1;

        for( l = 0 ; l < cl.length ; l ++){

            while( r < cl.length && C <= k){
                cur[cl[r]]++;
                if(cur[cl[r]] == 1) C++;
                r++;
            }

            if( r == cl.length && l == 0 && C < k ) return r - l ;

            if( r - l - 1  > ansr - ansl - 1 ) {
                ansl = l;
                ansr = r;
            }

            for(int i = l ; i < r-1 ; i++){
                cur[cl[i]] -- ;
                if (cur[cl[i]] == 0) {
                    C--;
                    l = i + 1;
                }

            }
        }


        if (ansl == -1) return 0;
        else return ansr - ansl - 1;

    }



//第二次看了答案以后
public int lengthOfLongestSubstringKDistinct2(String s, int k) {
    char[] sl = s.toCharArray();
    int[] cur  = new int[256];
    int C = 0;
    int res = 0;

    for(int l = 0 , r = 0 ; r < sl.length ; r ++ ){
        cur[sl[r]] ++;
        if(cur[sl[r]] == 1) C++;

        while(C > k){
            cur[sl[l]] --;
            if(cur[sl[l]] == 0) C--;
            l++;
        }

        res = Math.max(res, r-l+1);
    }

    return res;
}
}