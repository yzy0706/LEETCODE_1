package Stack.reCombine;

import java.util.Stack;

public class EvaluateReversePolishNotation_postFix {
    //做法： 就是用stack储存前两个数字然后碰到非数字的符号就用calculate的helper计算，
    // 1. 主要注意判断一个String里面是不是Integer可以用 Character.isDigit(String的最后一位char);
    // 2. Integer.parseInt(String) 可以一次性读取String里面的整个数字

    // Runtime: O(n^2), 每次都要重新浏览前两个数， space： O(n), 除了运算符都要放到stack里
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for(String s : tokens){
            char c = s.charAt(s.length()-1);
            if(Character.isDigit(c)){
                stack.push(Integer.parseInt(s));
            }
            else{
                int r = stack.pop();
                int l = stack.pop();
                stack.push(calculate(l, r, c));
            }
        }

        return stack.pop();
    }

    public int calculate(int l, int r, Character c){
        if(c == '+') return l + r;
        if(c == '-') return l - r;
        if(c == '*') return l * r;
        return l / r;

    }
}
