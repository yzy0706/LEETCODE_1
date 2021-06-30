package Stack.reCombine;

import java.util.Stack;

public class RemoveAllAdjacentDuplicatesInString {
    //  做法: 用stack做的, 碰到这种模拟两两抵消的题目都可以用stack做
    //  Runtime: O(n), Space: O(n);

    public String removeDuplicates(String S) {
        int len = S.length();
        if(len < 1) return S;
        Stack<Character> stack = new Stack<>();
        for(char c : S.toCharArray()){
            if(!stack.isEmpty() && stack.peek() == c){
                while(!stack.isEmpty() && stack.peek() == c) stack.pop();
            }
            else{
                stack.push(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) sb.append(stack.pop());
        return sb.reverse().toString();
    }
}
