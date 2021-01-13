package ARRAY;

import java.util.HashMap;
import java.util.Map;

public class romanToInteger {
    public int romanToInt(String s) {
        Character[] chars={'M','D','C','L','X','V','I'};
        int[] nums={1000,500,100,50,10,5,1};
        int res = 0;
        Map<Character,Integer> map= new HashMap<>();

        for(int i=0;i<chars.length;i++){
            map.put(chars[i],nums[i]);
        }

        res=map.get(s.charAt(s.length()-1));

        for(int j=0;j<s.length()-1;j++){
            if(map.get(s.charAt(j))<map.get(s.charAt(j+1))){
                res -= (map.get(s.charAt(j)));
            }
            else{
                res +=  map.get(s.charAt(j));
            }
        }
        return res;
    }
}
