package twoPointer;

public class longestParlindromicSubstring {


    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int left=0, right=0;
        for (int i = 0; i < s.length(); i++) {

            int l1= expandThePoint(s,i,i), l2= expandThePoint(s,i,i+1);
            int maxLen= Math.max(l1,l2);
            if(maxLen>right-left){
                right=i - (maxLen-1)/2;
                left=i + maxLen/2;
            }
        }
        return s.substring(left,right+1);

    }

    public int expandThePoint(String s, int left, int right) {
        int L =left, R = right;
        while(L >= 0 && R<s.length() && s.charAt(L)==s.charAt(R) ){
                R ++;
                L--;
            }
        return R-L-1;
    }


// 第二遍的解法

    int maxlen=0;
    int right=0;
    int left= 0;
    public String longestPalindrome_2(String s) {
        if(s.length()<1||s==null){
            return "";
        }
        for(int i=0;i <s.length(); i++){
            expandPoint(s,i,i);
            expandPoint (s,i,i+1);
        }
        return s.substring(left,right);

    }

    private void expandPoint (String s, int i,int j){
        while(i>=0&&j<s.length()&&s.charAt(i)==s.charAt(j))
        {
            i--;
            j++;
        }
        if(j-i+1> maxlen){
            right=j;
            left=i+1;
            maxlen=j-i+1;
        }
    }

    //九章解法：

    public String longestPalindrome_JZ(String s) {
        if(s.length() < 1) return "";
        if(s.length() == 1) return s;
        char[] cl = s.toCharArray();
        int maxLen = 0, start = 0;

        for(int i = 0 ; i < cl.length ; i++){
            if(findMax(i,i,cl) > maxLen){
                maxLen = findMax(i,i,cl);
                start = i - maxLen/2;
            }

            if(findMax(i,i+1,cl) > maxLen){
                maxLen = findMax(i,i+1,cl);
                start = i - maxLen/2 +1;
            }
        }

        return s.substring(start,start + maxLen);
    }

    private int findMax(int l, int r, char[] cl){
        int len = 0;
        while( l > 0 && r < cl.length  && cl[l] == cl[r]){
            if(cl[l] == cl[r]){
                if(l == r) len += 1;
                else len += 2;
            }
            l--;
            r++;
        }
        return len;

    }


    //曼彻斯特

    public String longestPalindrom_Manchester(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        // abc => #a#b#c#
        String str = generateString(s);

        int[] palindrome = new int[str.length()];
        int mid = 0, longest = 1;
        palindrome[0] = 1;
        for (int i = 1; i < str.length(); i++) {
            int len = 1;
            if (mid + longest > i) {
                int mirrorOfI = mid - (i - mid);
                len = Math.min(palindrome[mirrorOfI], mid + longest - i);
            }

            while (i + len < str.length() && i - len >= 0) {
                if (str.charAt(i - len) != str.charAt(i + len)) {
                    break;
                }
                len++;
            }

            if (len > longest) {
                longest = len;
                mid = i;
            }

            palindrome[i] = len;
        }

        longest = longest - 1; // remove the extra #
        int start = (mid - 1) / 2 - (longest - 1) / 2;
        return s.substring(start, start + longest);
    }

    private String generateString(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append('#');
            sb.append(s.charAt(i));
        }
        sb.append('#');

        return sb.toString();
    }
}









