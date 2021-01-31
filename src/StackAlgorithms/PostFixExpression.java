package StackAlgorithms;

import java.util.Stack;

public class PostFixExpression {
    //例子： "2 9 3 / + 5 -"
    //做法：把String转换成character， 然后在碰到不是数字的符号的时候把stack pop()两次分别找到右边、 左边的符号就行了
    //Runtime: O(n), space: O(n)为最大
    private int postFixExpression(String s){
        Stack<Integer> stack = new Stack<>();
        char[] cl = s.toCharArray();
        for(char c : cl){
            if(Character.isDigit(c)){
                stack.push(Integer.valueOf(c));
            }
            else{
                int right = stack.pop();
                int left = stack.pop();
                stack.push(calculate(left, right, c));
            }
        }
        return stack.pop();
    }

    private int calculate(int l, int r, Character operator){
        if(operator.equals('+')) return l + r;
        if(operator.equals('-')) return l - r;
        if(operator.equals('*')) return l * r;
        return l / r;
    }
}
