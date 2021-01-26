package Stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class decodeString {
    public String decodeString(String s) {
        if(s.length() < 1) return "";
        char[] cl = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        StringBuilder unclosedS = new StringBuilder(); // more '[' to be closed
        Deque<Character> stack = new ArrayDeque<>();
        int cnt = 0;
        for(char c: cl){

            stack.push(c);
            if(c == '[') {
                cnt++;
                StringBuilder curS = new StringBuilder();
            }
            if(c == ']'){
                stack.pop(); //pop ']'
                while(stack.peek() != '['){
                    char cur = stack.pop();
                    curS.append(cur);
                }

                stack.pop(); //" pop '['

                int curN = Character.getNumericValue(stack.pop());
                String curStr = curS.reverse().toString();
                for(int i = 0 ; i < curN ; i++) unclosedS.append(curStr);
                cnt --;
            }

            if(cnt == 0) {
                sb.append(unclosedS.toString());
                unclosedS.setLength(0);
            }
        }
        if(!stack.isEmpty()){
            StringBuilder sbLeft = new StringBuilder();
            while(!stack.isEmpty()){
                sbLeft.append(stack.pop());
            }
            sb.append(sbLeft.reverse());
        }
        return sb.toString();

    }


    //
}
