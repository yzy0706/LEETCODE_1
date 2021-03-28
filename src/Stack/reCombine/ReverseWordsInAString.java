package Stack.reCombine;

import java.util.Stack;

public class ReverseWordsInAString {
    // 做法: 这题就是
    // 1. 普通的浏览一下String s, 顺便把所有‘ ’跳过, 把所有的string都装到stack里
    // 2. 然后再调过头来把stack里的string一个个pop出来拼接在一个sb上, 后面加一个‘ ’以当做空格用, 最后再把最后一个空格删掉就是了
    // Runtime: O(n), Space: O(n)
    public String reverseWords(String s) {
        Stack<String> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == ' '){
                continue;
            }
            int curEnd = i;
            while(curEnd < s.length() && s.charAt(curEnd) != ' '){
                curEnd ++;
            }
            String curString = s.substring(i, curEnd);
            stack.push(curString);
            i = curEnd;
        }
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            String top = stack.pop();
            sb.append(top);
            sb.append(' ');
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
