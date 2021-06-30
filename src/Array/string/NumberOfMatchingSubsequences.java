package Array.string;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

public class NumberOfMatchingSubsequences {
    // 做法: 第二遍用hashmap写的
    // 1.建立一个hashmap, 从'a'到'z'每一个char都对应一个LinkedList<String>
    // 2.根据words里面每一个word的第一个char, 把当前word加到对应的LinkedList里
    // 3.foreach loop s里面的所有char, 把对应的deque拿出来, 只浏览当前deque里面原来就有的size个string, 不考虑新加入的. 因为是按照string上的char的顺序来浏览的:
    // a. 如果当前char有对应的word的长度是1, 那么res ++, 证明浏览到最后了
    // b. 否则map.get(topWord.charAt(1)).addLast(topWord.substring(1));

    // Runtime: O(n^2), Space: O(n)

    public int numMatchingSubseq_2(String s, String[] words) {
        int res = 0;
        HashMap<Character, LinkedList<String>> map = new HashMap<>();
        for(char c = 'a'; c <= 'z'; c++){
            map.put(c, new LinkedList<>());
        }
        for(String word : words){
            map.get(word.charAt(0)).addLast(word);
        }
        for(char c : s.toCharArray()){
            Deque<String> cur = map.get(c);
            int size = cur.size();
            for(int i = 0; i < size; i++){ // only empty the original queue, ignore the newly added Strings
                String topWord = cur.pollFirst();
                if(topWord.length() == 1){
                    res ++;
                }
                else{
                    map.get(topWord.charAt(1)).addLast(topWord.substring(1));
                }
            }
        }
        return res;
    }



    // 做法: 第一遍用brute force写的, 直接用的s.indexOf(char c, int fromIndex), 时间太长
    // Runtime: O(n^2), Space: O(1);

    public int numMatchingSubseq(String s, String[] words) {
        int res = 0;
        for(String word : words){
            int first = s.indexOf(word.charAt(0));
            if(first == -1) continue;
            int curIndex = first;
            for(int i = 1; i < word.length(); i ++){
                Character cur = word.charAt(i);
                int firstAppear = s.indexOf(cur, curIndex + 1);
                if(firstAppear == -1){
                    curIndex = -1;
                    break;
                }
                curIndex = firstAppear;
            }
            if(curIndex != -1) res ++;
        }
        return res;
    }
}
