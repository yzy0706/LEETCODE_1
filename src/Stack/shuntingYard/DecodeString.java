package Stack.shuntingYard;

import java.util.*;

public class DecodeString {
    //做法： 自己写的， 用一个stack装所有的number， 一个stack装当前所有的被拆掉括号后的Character
    // forloop整个String s
    // 1. 如果当前是digit， 就whileloop所有连起来的digit， 并且用一个 num = num * 10 + (cl[i] - '0'); 把数字连起来（也可以用string但不是很稳定）
    // 2. 如果是'['直接push到stack里
    // 3. 如果当前是']'就把stack里面到最近的一个'['之前的所有chracter都pop出来加到一个List<Chracter>上， 然后再把这个list reverse， 按照最近的也就是
    // 他们括号前面的那个number的个把这个反向的list装到stack里n次
    // 最后把这个Stack完全pop出来append到string上， 再return这个string的反向

    //Runtime: 理论上最差是O(n^2)， 但如果stack装的是string的话可能会快很多； Space: O(n)

        public String decodeString_self(String s) {
        StringBuilder sb = new StringBuilder();
        char[] cl = s.toCharArray();
        int len = s.length();
        Stack<Integer> numbers = new Stack<>();
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < len; i++){
            if(Character.isDigit(cl[i])){
                int number = 0;
                while(Character.isDigit(cl[i])){
                    number = 10 * number + (cl[i] - '0');
                    i++;
                }
                i--;
                numbers.push(number);
            }

            else if(cl[i] == ']'){
                List<Character> list = new ArrayList<>();
                Character top = stack.peek();
                while(top != '['){
                    top = stack.pop();
                    list.add(top);
                    top = stack.peek();
                }
                Collections.reverse(list);
                stack.pop(); //pop掉'['
                int times = numbers.pop();
                for(int j = 0; j < times; j++){
                    stack.addAll(list);
                }
            }
            else{
                stack.push(cl[i]);
            }
        }

        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
        sb.reverse();
        return sb.toString();
    }



    //Stack里装string和Integer的做法

    public String decodeString(String s) {
        String res = "";
        Stack<Integer> countStack = new Stack<>();
        Stack<String> resStack = new Stack<>();
        int idx = 0;
        while (idx < s.length()) {
            if (Character.isDigit(s.charAt(idx))) {
                int count = 0;
                while (Character.isDigit(s.charAt(idx))) {
                    count = 10 * count + (s.charAt(idx) - '0');
                    idx++;
                }
                countStack.push(count);
            }
            else if (s.charAt(idx) == '[') {
                resStack.push(res);
                res = "";
                idx++;
            }
            else if (s.charAt(idx) == ']') {
                StringBuilder temp = new StringBuilder (resStack.pop());
                int repeatTimes = countStack.pop();
                for (int i = 0; i < repeatTimes; i++) {
                    temp.append(res);
                }
                res = temp.toString();
                idx++;
            }
            else {
                res += s.charAt(idx++);
            }
        }
        return res;
    }










       //远古时期做的
        public String decodeString_oneStack(String s) {
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
                    StringBuilder curS = new StringBuilder();
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
    }

