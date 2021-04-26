package HashTable.hashSet;

import java.util.HashSet;
import java.util.Set;

public class JewelsAndStones {
    // 做法: 直接forloop S, 记录每个Character是不是在J里面存在
    // Runtime: O(n), Space: O(1)

    public int numJewelsInStones_reviewed(String J, String S) {
        int res = 0;
        for(Character c : S.toCharArray()){
            if(J.indexOf(c) != -1) res ++;
        }
        return res;
    }


    public int numJewelsInStones_2(String J, String S) {
        int res = 0;
        if(S.length()>50 || J.length() > 50) return 0;
        Set<Character> jewels = new HashSet<Character>();

        for(char c : J.toCharArray()){
            jewels.add(c);
        }

        for(char ch : S.toCharArray()){
            if(jewels.contains(ch)){
                res++;
            }
        }
        return res;

    }

    public int numJewelsInStones_3(String J, String S) {
        int res = 0;
        if(S.length()>50 || J.length() > 50) return 0;
        J.toCharArray();
        S.toCharArray();
        for(int i = 0 ; i<S.length() ; i++){
            if(J.indexOf(S.charAt(i))!= -1){
                res++;
            }
        }
        return res;

    }



}
