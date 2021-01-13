package dynamicProgramming;

import java.util.ArrayDeque;
import java.util.Deque;

public class longestValidParenthesis {
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


    //dynamic programming解法
    public int longestValidParentheses_DP(String s){
        int res = 0;
        char[] cl = s.toCharArray();
        int[] dp = new int[cl.length];
        for(int i = 1; i < cl.length; i++){
            if(cl[i] ==')'){
                if(cl[i-1] == '('){
                    dp[i] = (i >= 2? dp[i-2] : 0) +2;
                }
                else if(i - dp[i-1] > 0 && cl[i-dp[i-1]-1] == '('){
                    dp[i] = dp[i-1] + ((i-dp[i-1]) >= 2? dp[i-dp[i-1]-2] : 0) + 2;
                }
            }
            res = Math.max(res,dp[i]);
        }
        return res;
    }




}



