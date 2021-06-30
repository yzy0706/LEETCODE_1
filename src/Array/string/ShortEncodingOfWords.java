package Array.string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ShortEncodingOfWords {




    // 做法: set的做法, 需要foreach loop三次
    // 1. 用set记录所有的word
    // 2. 把每一个word的substring都从set里面remove掉
    // 3. 剩下的word都是可能包含其它word的最优解, 计算他们的长度和

    // Runtime: O(n), Space: O(n)

    public int minimumLengthEncoding_set(String[] words) {
        int len = words.length;
        if(len < 1) return 0;
        Set<String> set = new HashSet<>();
        for(String word : words){
            set.add(word);
        }
        int res = 0;
        for(String word : words){
            if(!set.contains(word)){
                continue;
            }
            for(int i = 1; i < word.length(); i ++){
                String temp = word.substring(i);
                set.remove(temp);
            }
        }
        for(String word : set){
            res += word.length() + 1;
        }
        return res;
    }


    // 做法: sort的做法, 把words按长到短来排列, 然后排除所有是别的String的结尾的String

    // Runtime: O(nlog(n)), Space: O(n)

    public int minimumLengthEncoding_sort(String[] words) {
        int len = words.length;
        if(len < 1) return 0;
        String res = "";
        Arrays.sort(words, (a, b) -> (b.length() - a.length()));
        for(String word : words){
            String temp = word + "#";
            if(res.contains(temp)) continue;
            else res += temp;
        }
        return res.length();
    }

    //第一遍用double forloop做的， 过不了所有的case
    public int minimumLengthEncoding(String[] words) {
        int len = words.length;
        if(len < 1) return 0;
        StringBuilder sb = new StringBuilder();
        String before = words[0];
        boolean[] visited = new boolean[len];
        for(int i = 0; i < len; i ++){
            if(visited[i]) continue;
            before = words[i];
            visited[i] = true;
            for(int j = 0; j < len; j++){
                if(visited[j]) continue;
                String cur = words[j];
                if((before.lastIndexOf(cur) == before.length() - cur.length() || cur.indexOf(before) != -1)){
                    before = before.length() > cur.length() ? before : cur;
                    visited[j] = true;
                }
            }
            sb.append(before + '#');
        }
        return sb.length();
    }
}
