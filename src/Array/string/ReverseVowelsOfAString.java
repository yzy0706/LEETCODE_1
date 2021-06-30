package Array.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class ReverseVowelsOfAString {
    // 做法: 虽然是个easy的题, 但还是分成两步处理
    // 1. 把s里所有是vowel的位置存储到一个叫做vowIndex的list里
    // 2. 用two pointer把vowIndex里的位置都两两swap一下

    //Runtime: O(n), Space: O(n);

    public String reverseVowels(String s) {
        int len = s.length();
        if(len < 1) return "";
        int last = -1;
        char[] cl = s.toCharArray();
        HashSet<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        List<Integer> vowIndex = new ArrayList<>();
        for(int i = 0; i < len; i++){
            char cur = cl[i];
            if(Character.isAlphabetic(cur) && vowels.contains(cur)){
                vowIndex.add(i);
            }
        }
        int l = 0, r = vowIndex.size() - 1;
        while(l < r){
            int left = vowIndex.get(l), right = vowIndex.get(r);
            char temp = cl[left];
            cl[left] = cl[right];
            cl[right] = temp;
            l ++;
            r --;
        }
        StringBuilder sb = new StringBuilder();
        for(char c : cl) sb.append(c);
        return sb.toString();
    }
}
