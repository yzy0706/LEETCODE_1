package DynamicProgramming.accumulate;

public class IsSubsequence {
    // 做法: 用一个index放在记录s里哪个char已经被找到了, 如果最后index到了s.length()代表s.length() - 1已经被找到了, 直接return true; 用一个boolean[] dp = new boolean[s.length()+1]来记录也是一样的结果
    // Runtime: O(n), Space: O(n);
    public boolean isSubsequence(String s, String t) {
        if(s == "" || s.length() < 1) return true;
        if(t == "") return false;
        char[] sl = s.toCharArray();
        char[] tl = t.toCharArray();
        int index = 0;
        for(char c : tl){
            if(c == sl[index]) index++;
            if(index == s.length()) return true;
        }
        return index == s.length();
    }
}
