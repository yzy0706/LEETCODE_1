package HashTable.hashSet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class LengthOfLongestSubstring {

    // 做法: 还是经典的同向双指针, 但因为这题里的参数s可能包含空格, 所以没法拿int[] freq来记录频率, 所以只能用HashMap来记录频率, 稍微有点慢
    // Runtime: O(n), Space: O(n)

    public int lengthOfLongestSubstring_reviewed(String s){
        if(s.length() < 1) return 0;
        int l = 0, r = 0, res = 0;
        HashMap<Character, Integer> freq = new HashMap<>();
        while(r < s.length()){
            Character cur = s.charAt(r);
            freq.putIfAbsent(cur, 0);
            freq.replace(cur, freq.get(cur) + 1);
            while(freq.get(cur) > 1 && l < r){
                freq.replace(s.charAt(l), freq.get(s.charAt(l)) - 1);
                l ++;
            }
            res = Math.max(res, r - l + 1);
            r++;
        }
        return res;
    }

    public int lengthOfLongestSubstring(String s){

        int cnt = 0;
        int high=0;
        Set<Character> sub = new HashSet<>();
        for(int i=1; i<s.length()-1;i++){
            if(s==null) {
                high = 0;
            }
            if(sub==(null)){
                cnt++;
                sub.add(s.charAt(i));
            }
            if(sub.contains(s.charAt(i))){
                if(cnt>high);
                high=cnt;
                cnt=0;
                sub=null;
            }
                cnt++;
                sub.add(s.charAt(i));
        }
        return high;
    }

    public int lengthOfLongestSubstring_1(String s){
      int ans = 0;
      Set<Character> result = new HashSet<>();
      int i=0;
      int j=0;

      while(i<s.length()&&j<s.length()){
          if(!result.contains(s.charAt(j))){
              result.add(s.charAt(j++));
              ans=Math.max(ans,j-i);
          }
          else{
              result.remove(s.charAt(i++));
          }
      }
      return ans;
    }
}