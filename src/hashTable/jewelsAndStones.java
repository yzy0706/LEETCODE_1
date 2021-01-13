package hashTable查重;

import java.util.HashSet;
import java.util.Set;

public class jewelsAndStones {
    public int numJewelsInStones(String J, String S) {
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

    public int numJewelsInStones(String J, String S) {
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
