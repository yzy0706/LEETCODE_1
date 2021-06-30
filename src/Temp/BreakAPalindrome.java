package Temp;

public class BreakAPalindrome {
    // 做法: 用stringbuilder做的, 用sb来承载整个palindrome
    // 1. forloop整个sb, 如果碰到了不是'a'的字符, 直接把它变成'a'并且return sb.toString();
    // 2. 如果结束了forloop还能读取到当前的一行, 证明palindrome全部都是'a', 为了lexicographically small, 把最后一位变成'b'

    // Runtime: O(n), Space: O(1);


    public String breakPalindrome_reviewed(String palindrome) {
        int len = palindrome.length();
        if(len <= 1) return "";
        StringBuilder sb = new StringBuilder(palindrome);
        for(int i = 0; i < len / 2; i++){
            if(palindrome.charAt(i) != 'a'){
                sb.replace(i, i + 1, "a");
                return sb.toString();
            }
        }
        sb.replace(len - 1, len, "b");
        return sb.toString();
    }



    public String breakPalindrome(String palindrome) {
        char[] cl = palindrome.toCharArray();
        int length = cl.length;
        for(int i = 0; i < length/2; i++){
            if(cl[i] != 'a') {
                cl[i] = 'a';
                return String.valueOf(cl);
            }
        }
        cl[length-1] = 'b';
        return length < 2? "" : String.valueOf(cl);
    }
}
