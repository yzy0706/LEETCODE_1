package Array.string;

import java.util.HashMap;
import java.util.HashSet;

public class MaxNumOfOccuranceOfASubstring {

    // 做法: 第二种HashMap的解法也是拿HashMap来记录每个符合条件的String的频率, 但只检查minSize长度的substring
    // 1. 建立一个helper, 用HashSet检测一个minSize的长度的substring不同char的个数符不符合条件
    // 2. 从i = 0开始到s.length() - minsize进行forloop, 检查每一个位置的minSize的substring

    // Runtime: O(nk), Space: O(n);



    public int maxFreq_4(String s, int maxLetters, int minSize, int maxSize) {
        int res = 0;
        HashMap<String, Integer> occurance = new HashMap<>();
        for(int i = 0; i < s.length() - minSize + 1; i ++){
            String cur = s.substring(i, i + minSize);
            if(qualified(cur, maxLetters)){
                occurance.putIfAbsent(cur, 0);
                occurance.replace(cur, occurance.get(cur) + 1);
                res = Math.max(res, occurance.get(cur));
            }
        }
        return res;
    }

    public boolean qualified(String cur, int maxLetters){
        HashSet<Character> set = new HashSet<>();
        for(char c : cur.toCharArray()) set.add(c);
        return set.size() <= maxLetters;
    }



    // 做法: 第一种是brute force的forloop做法, 直接记录所有满足条件的substring, 非常慢
    // Runtime: O(N^2), Space: O(26);

    public int maxFreq_3(String s, int maxLetters, int minSize, int maxSize) {
        int res = 0;
        HashMap<String, Integer> occurance = new HashMap<>();
        for(int i = 0; i < s.length(); i ++){
            int cnt = 0;
            int[] freq = new int[26];
            for(int j = i; j < s.length(); j ++){
                Character cur = s.charAt(j);
                if(freq[cur - 'a'] ++ == 0) cnt ++;
                int len = j - i + 1;
                if(cnt <= maxLetters && len >= minSize && len <= maxSize){
                    String curS = s.substring(i, j + 1);
                    occurance.putIfAbsent(curS, 0);
                    int times = occurance.get(curS) + 1;
                    occurance.replace(curS, times);
                    res = Math.max(res, times);
                }
                if(len > maxSize || cnt > maxLetters) break;
            }
        }
        return res;
    }

    // 第二遍看清题目了， 用HashMap做， 但TLE了， 因为是O(n^2)
    public int maxFreq_2(String s, int maxLetters, int minSize, int maxSize) {
        int res = 0;
        HashMap<String, Integer> occurance = new HashMap<>();
        for(int i = 0; i < s.length(); i ++){
            int[] freq = new int[26];
            int cnt = 0;
            for(int j = i; j < s.length(); j ++){
                Character cur = s.charAt(j);
                if(freq[cur - 'a'] ++ == 0) cnt ++;
                int len = j - i + 1;
                if(cnt <= maxLetters && len >= minSize && len <= maxSize){
                    String curS = s.substring(i, j + 1);
                    occurance.putIfAbsent(curS, 0);
                    int times = occurance.get(curS) + 1;
                    occurance.replace(curS, times);
                    res = Math.max(res, times);
                }
            }
        }
        return res;
    }



    // 第一遍想用two pointer， 感觉不太对

    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        int l = 0, r = 0, cnt = 0, res = 0;
        int[] freq = new int[26];
        for( r = 0; r < s.length(); r ++){
            Character cur = s.charAt(r);
            if(freq[cur - 'a']++ == 0) cnt ++;
            if(cnt <= maxLetters){
                res ++;
            }
            else{
            }
        }
        return -1;
    }
}
