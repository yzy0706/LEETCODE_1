package Array.string;

public class ReplaceAllQuestionMarksAndAvoidRepeating {
    // 做法: 用了个whileloop去避免与前后的字符重合, 结果很慢
    // Runtime: O(n), Space: O(n)

    public String modifyString(String s) {
        int len = s.length();
        char[] cl = s.toCharArray();
        for(int i = 0; i < len; i++){
            if(cl[i] != '?') continue;
            int last = -1, next = -1;
            if(i - 1 >= 0) last = cl[i - 1] - 'a';
            if(i + 1 < len && cl[i + 1] != '?') next = cl[i + 1] - 'a';
            int cur = 0;
            while((last != -1 && last == cur) || (next != -1 && next == cur)) cur ++;
            cl[i] = (char)(cur + 97);
        }
        String res = "";
        for(char c : cl) res += c;
        return res;
    }
}
