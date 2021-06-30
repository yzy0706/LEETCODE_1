package Array.string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class MostCommonWords {
    // 做法: 直接two pointer, 只是要注意r == len的时候的各种corner case
    // Runtime: O(n), Space: O(n);

    public String mostCommonWord(String paragraph, String[] banned) {
        int len = paragraph.length();
        int l = 0;
        HashSet<String> banStrings = new HashSet<>(Arrays.asList(banned));
        HashMap<String, Integer> freq = new HashMap<>();
        String res = "";
        for(int r = 0; r <= len; r ++){
            if(r == len){
                if(l >= len || !Character.isAlphabetic(paragraph.charAt(l))){
                    break;
                }
                String cur = paragraph.substring(l, r).toLowerCase();
                if(banStrings.contains(cur)){
                    break;
                }
                freq.putIfAbsent(cur, 0);
                freq.put(cur, freq.get(cur) + 1);
                if(freq.get(cur) > freq.getOrDefault(res, 0)){
                    res = cur;
                }
                l = r + 1;
            }
            else if(!Character.isAlphabetic(paragraph.charAt(r))){
                if(!Character.isAlphabetic(paragraph.charAt(l))){
                    l = r + 1;
                    continue;
                }
                String cur = paragraph.substring(l, r).toLowerCase();
                if(banStrings.contains(cur)){
                    l = r + 1;
                    continue;
                }
                freq.putIfAbsent(cur, 0);
                freq.put(cur, freq.get(cur) + 1);
                if(freq.get(cur) > freq.getOrDefault(res, 0)){
                    res = cur;
                }
                l = r + 1;
            }
        }
        return res;
    }
}
