package MidpointExpand;

public class JZ_JC_1_ValidPalindrome {
    //网上做法
    public boolean isPalindrome(String s) {
        if (s.equals(" ") || s.length() <= 1) return true;
        boolean res = false;
        char[] cl = s.toCharArray();

        if (cl.length == 2) {
            if (Character.isLetterOrDigit(cl[0]) && Character.isLetterOrDigit(cl[1])) {
                if (Character.toLowerCase(cl[0]) != Character.toLowerCase(cl[1]))
                    return false;
            }

            return true;
        }

        for (int i = 1; i < cl.length - 1; i++) {
            if (!Character.isLetterOrDigit(cl[i - 1]) && !Character.isLetterOrDigit(cl[i + 1]))
                continue;
            if (Character.toLowerCase(cl[i - 1]) == Character.toLowerCase(cl[i + 1])) {
                return true;
            }
        }

        return false;


    }

    //自己做的

    public int longestPalindrome(String s) {
        if (s.length() < 1) return 0;
        char[] list = s.toCharArray();
        int res = 0;
        int[] chars = new int[1010];
        for (char c : list) {
            chars[c]++;
            if (chars[c] % 2 == 0) res += 2;
        }
        if (res <= s.length() - 1) res += 1;
        return res;
    }
}