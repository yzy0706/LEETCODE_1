package hashTable查重;

import java.util.HashSet;
import java.util.Set;

public class kLongestSubstring {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if(k<1||s.length()<1) return 0;
        int res = 0;
        Set<Character> forward  = new HashSet<>();
        Set<Character> back;
        back = new HashSet<>();
        char[] list = s.toCharArray();

        int i=0;
        int j=list.length-1;
        int forw = 0;
        int backw = 0;

        while(j>=0&&i<list.length){
            i++;
            j--;
            if(forward.size() <= k) {
                forward.add(list[i]);
                forw++;
            }
            if(back.size() <= k){
                back.add(list[j]);
                backw++;
            }
            if(forward.size() > k){
                forward.clear();
                forward.add(list[i]);
                forw=1;
            }
            if(back.size() > k){
                back.clear();
                back.add(list[j]);
                backw=1;
            }
            if(forw>res) res = forw;
            if(backw>res) res = backw;
        }

        return res;




    }
}
