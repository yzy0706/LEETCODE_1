package stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class validParentheses {
    public boolean isValid(String s) {
        boolean res= true;
        if(s.length()<1||s==null){
            return true;
        }
        Deque<Character> sta = new ArrayDeque<>();
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i)=='}'||s.charAt(i)==']'||s.charAt(i)==')'){
                res = !sta.isEmpty();
                if(!sta.isEmpty()&&s.charAt(i)!=sta.pop()){
                    res=false;
                    break;
                }
            }
            if(s.charAt(i)=='{') sta.push('}');
            if(s.charAt(i)=='[') sta.push(']');
            if(s.charAt(i)=='(') sta.push(')');
        }
        if(!sta.isEmpty()){
            return false;
        }
        return res;


    }


    //第二遍我自己写的
    public boolean isValid_2 (String s) {
        Deque<Character> stack = new ArrayDeque<>();
        if(s.length() < 1) return true;
        boolean res = false;

        char[] cl = s.toCharArray();
        for(int i = 0; i < cl.length ; i++){
            char c = cl[i];
            if(c == '(') stack.push(')');
            else if(c == '{') stack.push('}');
            else if(c == '[') stack.push(']');
            else {
                if(stack.isEmpty()) return false;
                char cur = stack.pop();
                if(c == cur) res = true;
                else return false;
            }
        }

        if(!stack.isEmpty()) return false;
        return res;
    }
}
