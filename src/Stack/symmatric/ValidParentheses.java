package Stack.symmatric;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class ValidParentheses {
    //做法： 碰到了所有的左括号都push他对应的右括号到stack里， 碰到了右括号就把当前stack里最顶端的pop出来，
    // 如果跟当前右括号不一样， 或者stack里面已经是empty的， 证明左右括号不是对应的， return false;
    // 结束foreach loop 以后如果stack还是有东西的证明左括号多了， 也return false；

    //Runtime: O(n), space: O(1)

    public boolean isValid_stack(String s) {
        char[] cl = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for(char c : cl){
            if(c == '[') stack.push(']');
            else if (c == '{') stack.push('}');
            else if(c == '(') stack.push(')');
            else{
                if(!stack.isEmpty()){
                    char right = stack.pop();
                    if(right != c) return false;
                }
                else{
                    return false;
                }
            }
        }
        if(!stack.isEmpty()) {
            return false;
        }
        return true;
    }






































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
