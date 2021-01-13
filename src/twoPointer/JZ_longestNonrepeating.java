package twoPointer;

public class JZ_longestNonrepeating {
    public int lengthOfLongestSubstring(String s) {
        char[] sl = s.toCharArray();
        int[] cur =  new int[256];
        int  ans = 0;

        for( int l = 0 , r = 0 ; r < s.length() ; r ++){
            cur[sl[r]]++;
            while (cur[sl[r]] > 1){
                cur[sl[l]] -- ;
                l++;
            }
            ans = Math.max(ans, r-l+1);
        }

        return ans;
    }
}
