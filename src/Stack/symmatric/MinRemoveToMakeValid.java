package Stack.symmatric;

import java.util.Stack;

public class MinRemoveToMakeValid {
    // 做法: 用stack来储存所有'[’的位置,  用一个StringBuilder承载s方便修改
    // 1. forloop整个s, 如果碰到了‘[', 直接push当前i
    // 2. 如果碰到了']'
    //  a. 如果stack是空的, 那么当前sb.setCharAt(i, "!"); 准备被删掉
    //  b. 如果stack不是空的, 与最近的那个位置抵掉
    // 3. 最后forloop结束以后, stack还没抵消完, 证明有多余的'[', 把那些位置也都sb.setCharAt(pos, "!");
    // 4. sb.toString(); 以后string.removeAll("!", ""); 统一删除

    //Runtime: O(n), space: O(n);
    public String minRemoveToMakeValid(String s) {
        char[] cl = s.toCharArray();
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder(s);
        for(int i = 0; i < cl.length; i++){
            if(cl[i] == '('){
                stack.push(i);
            }
            else if(cl[i] == ')'){
                if(!stack.isEmpty()){
                    stack.pop();
                }
                else{
                    sb.setCharAt(i, '!');
                }
            }
        }

        while(!stack.isEmpty()){
            int pos = stack.pop();
            sb.setCharAt(pos, '!');
        }

        String res = sb.toString();

        return res.replaceAll("!", "");

    }
}
