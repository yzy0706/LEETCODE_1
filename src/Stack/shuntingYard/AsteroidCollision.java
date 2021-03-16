package Stack.shuntingYard;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class AsteroidCollision {
    // 做法: 用stack做的, 其实用的就是shunting yard, stack储存之前所有的数的位置
    // 如果当前是负数, 用一个whileloop一直去去除之前是正数且小于当前的数的绝对值的,
    //  a. 如果最后停下来是因为碰到了一样大的就都pop掉
    //  b. 如果最后停下来是stack.isEmpty()或者前面一个数也是负数就把现在位置push进去

    // Runtime: O(n) , Space: O(n)
    public int[] asteroidCollision_stack(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < asteroids.length; i++){
            if(asteroids[i] > 0){
                stack.push(i);
                continue;
            }

            else{
                if(!stack.isEmpty()){
                    int lastPosVal = asteroids[stack.peek()];
                    int val = Math.abs(asteroids[i]);
                    while(lastPosVal > 0 && val > lastPosVal){
                        stack.pop();
                        if(stack.isEmpty()){
                            break;
                        }
                        lastPosVal = asteroids[stack.peek()];
                    }
                    if(lastPosVal == val){
                        stack.pop();
                        continue;
                    }
                    if(lastPosVal < 0 || stack.isEmpty()){
                        stack.push(i);
                    }
                }
                else{
                    stack.push(i);
                }
            }
        }
        int[] res = new int[stack.size()];
        for(int i = stack.size() - 1; i >= 0; i --){
            res[i] = asteroids[stack.pop()];
        }
        return res;
    }









    // 第一个方法自己写的， 过了， 其实就是简单的greedy， forloop整个arr，
    // 碰到了负数就往左消除比他小的或者相等的， 并把当前的位置变为0， 一直到碰到负数或碰到比他大的为止
    // Runtime: O(n), Space: O(1)
    public int[] asteroidCollision(int[] asteroids) {
        for(int i = 0; i < asteroids.length; i++){
            if(asteroids[i] < 0){
                if(i == 0) continue;
                int val = Math.abs(asteroids[i]);
                int pos = i;
                for(int j = i - 1; j >= 0; j --){
                    if(asteroids[j] < 0){
                        break;
                    }
                    else{
                        if(val > asteroids[j]){
                            asteroids[pos] = 0;
                            pos = j;
                            asteroids[pos] = -val;
                        }
                        else if(val == asteroids[j]){
                            asteroids[pos] = 0;
                            asteroids[j] = 0;
                            break;
                        }
                        else{
                            asteroids[pos] = 0;
                            break;
                        }
                    }
                }
            }
        }
        List<Integer> res = new ArrayList<>();
        for(int i : asteroids){
            if(i != 0) res.add(i);
        }
        int[] result = new int[res.size()];
        for(int i = 0; i < res.size(); i++){
            result[i] = res.get(i);
        }
        return result;
    }
}
