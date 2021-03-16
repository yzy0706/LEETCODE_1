package TwoPointer.diffrentDirection;

public class CountBinaryStrings {
    // 做法: 用的greedy和two pointer的做法, 每次碰到前后两个不一样的位置就根据这两个位置往两边拓展, 每次两端都能找到连续的一样的字符就res++;
    // Runtime: O(n^2), Space: O(1)
    public int countBinarySubstrings(String s) {
        int res = 0;
        char[] cl = s.toCharArray();
        for(int i = 0; i < cl.length - 1; i++){
            if(cl[i] != cl[i+1]) res ++;
            else continue;
            int l = i-1, r = i+2;
            while(l >= 0 && cl[l] == cl[l+1] && r < cl.length && cl[r] == cl[r-1]){
                res++;
                l--;
                r++;
            }
        }
        return res;
    }
}
