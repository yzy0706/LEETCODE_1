package hashTable查重;

import java.util.HashSet;
import java.util.Set;

public class lengthOfLongestSubstring {
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

    public int lengthOfLongestSubstring(String s){
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






public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }
}