package twoPointer;

public class JZ_lengthOfKDistinctSubstring {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        char[] cl = s.toCharArray();
        int l = 0;
        int r = 0;
        int[] cur = new int[256];
        int C = 0;
        int res = 0;
        int ansr = -1;
        int ansl = -1;
        if(cl.length == 1 &&  k == 1) return 1;

        for( l = 0 ; l < cl.length ; l ++){

            while( r < cl.length && C <= k){
                cur[cl[r]]++;
                if(cur[cl[r]] == 1) C++;
                r++;
            }

            if( r == cl.length && l == 0 && C < k ) return r - l ;

            if( r - l - 1  > ansr - ansl - 1 ) {
                ansl = l;
                ansr = r;
            }

            for(int i = l ; i < r-1 ; i++){
                cur[cl[i]] -- ;
                if (cur[cl[i]] == 0) {
                    C--;
                    l = i + 1;
                }

            }
        }


        if (ansl == -1) return 0;
        else return ansr - ansl - 1;

    }



//第二次看了答案以后
public int lengthOfLongestSubstringKDistinct2(String s, int k) {
    char[] sl = s.toCharArray();
    int[] cur  = new int[256];
    int C = 0;
    int res = 0;

    for(int l = 0 , r = 0 ; r < sl.length ; r ++ ){
        cur[sl[r]] ++;
        if(cur[sl[r]] == 1) C++;

        while(C > k){
            cur[sl[l]] --;
            if(cur[sl[l]] == 0) C--;
            l++;
        }

        res = Math.max(res, r-l+1);
    }

    return res;
}
}