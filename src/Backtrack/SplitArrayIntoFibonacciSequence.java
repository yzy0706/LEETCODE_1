package Backtrack;

import java.util.ArrayList;
import java.util.List;

public class SplitArrayIntoFibonacciSequence {

//做法: 用backtrack, 在helper(s, res, idx), 不断修改List<Integer> res最后一位的数字去尝试所有的可能
    // 1. 建立一个helper, 虽然是return boolean的, 但因为ArrayList是引用传递, res可以一直被修改, 而且return true的时候就可以截断之后别的recursion, 这叫做branch pruning
    // 2. 在helper里:
    //      a. 如果res的大小 >= 3且idx == s.length(), return true;
    //      b. 让i从idx开始forloop S上所有的可能, 并截取s.substring(idx, i+1);来得到long num, 也就是当前截取的数字
    //      c. 如果当前res大小大于等于2且num大于res里前面两个数的和, 直接break, 因为不可能组成有效的数列了
    //      d. 如果res的大小 <= 1或者num等于前面两个数的和, 当前res加入num, 然后进行helper(s, res, i+1)的recursion,
    //          然后像正常backtrack一样把尾巴去掉尝试别的可能
    // 3. 直接return res, 虽然helper是return boolean的

    // Runtime: O(n!), 极端情况是每次我们都只用掉一个数, 每次接下来探索的可能都少1个; Space: O(n), 最多n个数
    public List<Integer> splitIntoFibonacci_backTrack(String S) {
        List<Integer> res = new ArrayList<>();
        helper(S, res, 0);
        return res;
    }

    public boolean helper(String s, List<Integer> res, int idx){
        if(idx == s.length()&& s.length() >= 3){
            return true;
        }
        for(int i = idx; i < s.length(); i++){ //一直截取到i+1, 包括i, 要头不要尾
            if(s.charAt(idx) == '0' && i > idx){
                break;
            }
            long number = Long.parseLong(s.substring(idx, i+1));
            if(number > Integer.MAX_VALUE){
                break;
            }
            int size = res.size();
            if(size >= 2 && number > res.get(size - 1) + res.get(size - 2)){
                //如果是大于前面两个数的和就直接break了. 因为没法变小
                break;
            }
            if(size <= 1 || number == res.get(size - 1) + res.get(size - 2)){
                res.add((int)number);
                if(helper(s, res, i+1)) return true;
                //如果当前number后面的数列也能组成fibonacci就return true,
                //根据branch pruning 的方法, return true给上层了以后就会截断下面的recursion
                res.remove(res.size() - 1); //把尾巴去掉去试别的地方
            }
        }
        return false;
    }















    //第一遍自己用backtrack写的
    private List<Integer> res;
    public List<Integer> splitIntoFibonacci(String S) {
        res = new ArrayList<>();
        if(S.charAt(0) == '0' || S.length() < 3) return res;
        // char[] cl = S.toCharArray();
        for(int i = 1; i < S.length(); i++){
            for(int j = i; j < S.length(); j++){
                String s0 = S.substring(0, i);
                int num0 = Integer.getInteger(s0);
                String s1 = S.substring(i, j);
                int num1 = Integer.getInteger(s1);
                findNext(num0, num1, j, S);
            }
        }
        return res;
    }

    public void findNext(int num0,  int num1, int j, String S){
        if(j == S.length()) return;
        for(int l = j+1; l <= S.length(); l++){
            String s2 = S.substring(j, l);
            int num2 = Integer.getInteger(s2);
            if(num2 == num0 + num1){
                res.add(num0);
                res.add(num1);
                res.add(num2);
                findNext(num1, num2, l, S);
            }
        }
    }
}
