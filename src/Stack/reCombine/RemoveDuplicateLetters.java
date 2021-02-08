package Stack.reCombine;

import java.util.Stack;

public class RemoveDuplicateLetters {
    //答案给的做法， 基本思路一样， 但是用int[]去记录每个字符的频率并且用boolean记录之前stack里面有没有这个字符会比较简便
    
    //做法: 建立一个int[] freq来记录每个字符出现的频率; 建立一个boolean[] added来记录当前是不是已经把某个字符加到stack里了
    // 1. 先foreach loop一遍s, 把每个字符的频率记录一下
    // 2. 再foreach loop一遍s, 每个字符的频率 -- ,
    //  a. 如果已经加入过了则直接continue
    //  b. stack的套路写法: while(!stack.isEmpty() && stack.peek() > c && freq[stack.peek() - 'a'] != 0)的话, 证明后面还有这个字母, 且组成的排序更小
    // 所以把当前stack里比当前这个字母大的字母全部都pop掉, added[stack.peek() - 'a']改成false,
    //3. 最后再把stack里的字符反过来组成string并return

    // Runtime: 如果这个String都是一样的字母的话他每次都要向前移动n次, 所以是O(n^2), space : O(n);
    public String removeDuplicateLetters_solution(String s) {
        Stack<Character> stack = new Stack<>();
        int[] freq = new int[26];
        boolean[] added  = new boolean[26];
        char[] cl = s.toCharArray();
        for(char c : cl){
            freq[c - 'a'] ++;
        }

        for(char c : cl){
            freq[c - 'a'] --;
            if(added[c - 'a']){
                continue;
            }
            while(!stack.isEmpty() && stack.peek() > c && freq[stack.peek() - 'a'] != 0){
                Character tp = stack.pop();
                added[tp - 'a'] = false;
            }
            stack.push(c);
            added[c - 'a'] = true;
        }

        StringBuilder sb =  new StringBuilder();
        while(!stack.isEmpty()){
            char c = stack.pop();
            sb.append(c);
        }

        return sb.reverse().toString();

    }





    //第一遍自己做的， 对于处理stack中有跟当前浏览到的字母相同的情况弄的太复杂了。
    public String removeDuplicateLetters(String s) {
        Stack<Character> stack = new Stack<>();
        char[] cl = s.toCharArray();
        for(char c : cl){
            if(stack.isEmpty() || !stack.contains(c)){
                stack.push(c);
            }

            else{
                Character tp = stack.peek();
                Stack<Character> temp = new Stack<>();
                while(tp > c){
                    Character cur = stack.pop();
                    temp.push(cur);
                    tp = stack.peek();
                }

                if(tp == c){
                    stack.pop();

                }
                else if(tp < c){

                    while(!temp.isEmpty()){
                        Character back = temp.pop();
                        stack.push(back);
                    }
                    stack.push(c);
                }
            }
        }

        StringBuilder sb =  new StringBuilder();
        while(!stack.isEmpty()){
            char c = stack.pop();
            sb.append(c);
        }

        return sb.reverse().toString();

    }
}
