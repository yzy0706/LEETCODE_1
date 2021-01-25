package StackAlgorithms;

import java.util.HashMap;
import java.util.Stack;

//做法： 先建立一个优先级的map， 包括括号， 然后再把整个中缀表达式浏览（String)
// 主要分成了三个情况：
//1. 如果是 + - * / 中的一个， 那么我peek()出来stack最顶上的东西看看是不是比当前我的优先级小， 如果是的就pop()出来加到答案里， 然后再继续peek()
//出来看
//2. 如果是数字， 直接加到答案里
//3. 如果是'(', 直接push到stack里
//4. 如果是')'， 在碰到'(' break之前把所有的符号统统加到答案里， 不用担心优先级， 因为之前我们在括号里碰到优先级低的operator时我们已经把高优先级的
//加到sb里了

//Runtime: O(n^2), space: O(n)为最多


public class InFixToPostFix {
    public String inFixToPostFix(String s){
        HashMap<Character, Integer> priority = constructPriority();
        char[] cl = s.toCharArray();
        Stack<Character> operatorStack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for(char c : cl){
            if(Character.isDigit(c)) sb.append(c); //如果是int直接append到结果上
            else{ //不是数字的情况
                if(c == '*' || c == '/' || c == '+' || c == '-'){ //如果不是（
                    Character top = operatorStack.peek();
                    while(priority.get(c) <= priority.get(top)){ //如果当前的operator优先级小于等于前面的， 把前面的都加进去
                        top = operatorStack.pop();
                        sb.append(top);
                        if(operatorStack.isEmpty()) { //当前已经pop出最后一个operator， 直接break
                            break;
                        }
                        top = operatorStack.peek(); //在循环继续前把stack的头再peek出来准备下一轮用
                    }
                    operatorStack.push(c); //最后再把当前最小的push进去
                }

                else if(c == '('){
                    operatorStack.push(c);
                }
                else if(c == ')'){ //如果是右括号把碰到左括号前的所有的operator全都append上
                    while(!operatorStack.isEmpty()){
                        Character top = operatorStack.pop();
                        if (top == '('){
                            break;
                        }
                        sb.append(top);
                    }
                }

            }
        }
        while(!operatorStack.isEmpty()){
            sb.append(operatorStack.pop());
        }
        return sb.toString();
    }

    public HashMap<Character, Integer> constructPriority(){
        HashMap<Character, Integer> priority = new HashMap<>();
        priority.put('*', 2);
        priority.put('/', 2);
        priority.put('+', 1);
        priority.put('-', 1);
        priority.put('(', 0);
    }
}
