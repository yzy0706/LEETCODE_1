package Sort.constructComparator;

import java.util.Arrays;

public class VerifyingAnAlienDictionary {
    //做法: 我用了外星人order做了一个以每个words里面的word, 在order里的index为标准的comparator, 来sort这个words, 然后看sort完了以后是不是跟原来的copy是一样的
    //Runtime: O(nlog(n)), space: O(n)
    public boolean isAlienSorted(String[] words, String order) {
        String[] copy = words.clone();
        Arrays.sort(words, (a, b) -> {
            int shorterLength = Math.min(a.length(), b.length());
            for(int i = 0; i < shorterLength; i++){
                if(a.charAt(i) != b.charAt(i)){
                    return order.indexOf(a.charAt(i)) - order.indexOf(b.charAt(i));
                }
            }
            return a.length() - b.length();
        });

        for(int i = 0; i < copy.length; i++){
            if(!copy[i].equals(words[i])){
                return false;
            }
        }
        return true;
    }



    //map的做法

    int[] mapping = new int[26];
    public boolean isAlienSorted_map(String[] words, String order) {
        for (int i = 0; i < order.length(); i++)
            mapping[order.charAt(i) - 'a'] = i;
        for (int i = 1; i < words.length; i++)
            if (bigger(words[i - 1], words[i]))
                return false;
        return true;
    }

    boolean bigger(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        for (int i = 0; i < n && i < m; ++i)
            if (s1.charAt(i) != s2.charAt(i))
                return mapping[s1.charAt(i) - 'a'] > mapping[s2.charAt(i) - 'a'];
        return n > m;
    }
}
