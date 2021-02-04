package TwoPointer;

public class JZ_minimunWindowSubstring {
    //同向双指针算法 O(n)
    public String minWindow(String source , String target) {
        String res = "";
        char[] s = source.toCharArray();
        char[] t = target.toCharArray();
        int[] cntT = new int[256];
        int[] cntS = new int[256];
        int K = 0;

        if (source.equals("")) return "";

        // for(int i = 0 ; i < 256 ; i++){
        //     cntS[i] = cntT[i] = 0;
        // }

        for (char c : t){
            cntT[c] ++;
            if(cntT[c] == 1){
                K++;

            }
        }

        int r = 0;
        int C = 0;
        int ansl = -1 , ansr = -1;
        for (int l = 0 ; l < s.length ; l++){
            while (r < s.length && C < K){
                cntS[s[r]]++;
                if(cntS[s[r]] == cntT[s[r]]) C++;
                r++;//r++了就移动到r的后面一位去了
            }

            if (C == K){
                if(ansl == -1 || r - l < ansr - ansl) {
                    ansl = l;
                    ansr = r;
                }
                // if((l == 0 && r ==0) || r - l  < res.length()){
                //     res = source.substring(l,r);
                // }

            }

            cntS[s[l]]--;
            if(cntS[s[l]] == cntT[s[l]] - 1){
                C--;
            }
        }
        //  return res;
        if( ansl == -1) return "";
        else return source.substring(ansl,ansr);
    }
}
