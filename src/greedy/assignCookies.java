package greedy;

import java.util.Arrays;

public class assignCookies {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int i=0;
        int j=0;
        int res=0;
        if(g.length<1||s.length<1) return 0;

        while(j<s.length&&i<g.length){
            if(g[i]<=s[j]){
                i++;
                j++;
                res=i;
            }
            else {
                j++;
            }
        }
        return res;

    }
}
