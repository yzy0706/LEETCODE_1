package Stack.shuntingYard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class BasicCalculatorII {
    public int calculate(String s) {
        char[] cl = s.toCharArray();
        HashMap<Character, Integer> priority = priorityMap();
        Stack<Character> opStack = new Stack<>();
        List<String> postFix = new ArrayList<>();

        for(int i = 0; i < cl.length; i++){
            Character cur = cl[i];
            if(Character.isDigit(cur)){
                int num = 0;
                while(i < cl.length && Character.isDigit(cl[i])){
                    num = num * 10 + Character.getNumericValue(cl[i]);
                    i++;
                }
                postFix.add(String.valueOf(num));
                i--;
            }
            else if(cl[i] == ' '){
                continue;
            }
            else{
                if(opStack.isEmpty()){
                    opStack.push(cur);
                }
                else{
                    Character top = opStack.peek();
                    while(priority.get(cur) <= priority.get(top)){
                        top = opStack.pop();
                        postFix.add(top.toString());
                        if(opStack.isEmpty()){
                            break;
                        }
                        top = opStack.peek();
                    }
                    opStack.push(cur);
                } //调度场算法
            }
        }

        while(!opStack.isEmpty()){
            postFix.add(opStack.pop().toString());
        }

        Stack<Integer> numbers = new Stack<>();

        for(int i = 0; i < postFix.size(); i++){
            String cur = postFix.get(i);
            if(Character.isDigit(cur.charAt(0))){
                numbers.push(Integer.valueOf(cur));
            }
            else{
                int right = numbers.pop();
                int left = numbers.pop();
                if(cur.equals("+")){
                    numbers.push(left + right);
                }
                else if(cur.equals("-")){
                    numbers.push(left - right);
                }
                else if(cur.equals("*")){
                    numbers.push(left * right);
                }
                else if(cur.equals("/")){
                    numbers.push(left / right);
                }
            }
        }

        return numbers.pop();
    }

    public HashMap priorityMap(){
        HashMap<Character, Integer> priority = new HashMap<>();
        priority.put('+', 0);
        priority.put('-', 0);
        priority.put('*', 1);
        priority.put('/', 1);
        return priority;
    }

}
