package Sort.SortThenCompareString;

import java.util.Arrays;

public class ValidAnagram {
    //做法: 直接把两个string转换成char[], sort以后比较是否完全一样
    //Runtime: O(nlog(n)), space: O(n)
    public boolean isAnagram(String s, String t) {
        if(s.length() == 0 && t.length() == 0) return true;
        if(s.length() != t.length()) return false;
        char[] cls = s.toCharArray(), clt = t.toCharArray();
        Arrays.sort(cls);
        Arrays.sort(clt);
        for(int i = 0; i < cls.length; i++){
            if(cls[i] != clt[i]) return false;
        }
        return true;
    }
}
