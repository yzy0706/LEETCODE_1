package DynamicProgramming.greedyCombinations;

import java.util.ArrayDeque;
import java.util.Deque;

public class LongestValidParenthesis {
    //dp解法

    //做法: 设置一个dp给所有的')'的位置:
    //1. 假如当前cl[i-1] == '(', 那么dp[i] = (i - 2 >= 0? dp[i - 2] : 0 ) + 2;
    //2. 假如当前cl[i-1] != ')', 且cl[i-dp[i-1]-1] == '(', 也就是跳过这之前的所有连续bracket的那个位置是正好互补的'('
    //dp[i] = dp[i-1] + (i - dp[i-1] - 2 >= 0? dp[i - dp[i-1] - 2] : 0) + 2; 把跳过这之前的连续bracket的dp加上再加上刚刚新成立的一个bracket 2
    //Runtime: O(n), space: O(n)
    public int longestValidParentheses_dp(String s){
        int res = 0;
        char[] cl = s.toCharArray();
        int[] dp = new int[s.length()];

        for(int i = 1; i < s.length(); i++){
            if(cl[i] == ')'){
                if(cl[i-1] == '('){
                    dp[i] = (i - 2 >= 0? dp[i-2] : 0) + 2;
                }
                else if(i - dp[i-1] > 0 && cl[i - dp[i-1] - 1] == '('){ //cl[i] == ')' 且 cl[i-1] == '('的情况
                    dp[i] = dp[i - 1] + ( i - dp[i-1] - 2 >= 0? dp[i - dp[i-1] - 2] : 0 ) + 2;  //如果当前的i - 连续的bracket的长度 - 2 > 0 的话要把上一段连续的bracket也要加上
                }
            }
            res = Math.max(dp[i], res);
        }

        return res;
    }




   //stack解法



    public boolean isValid(String s){
        char[] cl = s.toCharArray();
        Deque<Integer> positions = new ArrayDeque<Integer>();

        for(int i = 0 ; i < cl.length ; i++){

            if(cl[i] == '('){
                positions.push(i);
                // if(i == cl.length - 1) return false;
            }

            else if(!positions.isEmpty()){
                positions.pop();
            }

            else{
                return false;
            }

        }

        return positions.isEmpty();
    }







    //第一遍自己写的
    public int longestValidParentheses(String s){

        int res = 0, temp = 0, mark = 0;
        char[] cl = s.toCharArray();
        if(cl.length <= 1) return 0;
        Deque<Integer> positions = new ArrayDeque<>();
        for(int i = 0; i < cl.length ; i++){

            if(cl[i] == '(') {
                if(i == cl.length - 1){
                    if(temp >= res){
                        res = temp;
                        break;
                    }
                }
                positions.push(i);
            }

            else if(cl[i] == ')'){
                // left --;
                if(positions.isEmpty()){
                    if(temp >= res){
                        res = temp;
                        temp = 0;
                        // left = 0;
                    }
                    positions.clear();
                }

                else{
                    positions.pop();
                    temp += 2;

                    if(i == cl.length - 1){

                        if(positions.isEmpty()){
                            if(temp >= res){
                                res = temp;
                                break;
                            }
                        }

                        else{
                            int start = positions.pop();
                            res = i - start;
                            break;
                        }
                    }
                }
            }
        }
        return res;
    }


    //brute force解法（这样会有runtime error）
    public int longestValidParentheses_bruteForce(String s) {
        int res = 0;
        char[] cl = s.toCharArray();

        for(int i = 0; i < cl.length ; i++){

            for(int j = i + 2; j <= cl.length; j += 2){

                if(isValid(s.substring(i,j))){
                    res = Math.max(j - i, res);
                }

            }
        }
        return res;
    }







}



