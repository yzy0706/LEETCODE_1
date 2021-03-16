package Stack.reCombine;

import java.util.Stack;

public class CanPlaceFlowers {
    // 做法: 用stack做的, 稍微做的有点复杂, 如果当前是符合条件的0 就cnt++且push(1)进去, 其他情况都push当前的数
    // Runtime: O(n), Space: O(n)
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int len = flowerbed.length;
        if(n == 0) return true;
        if(len < n) return false;
        Stack<Integer> stack = new Stack<>();
        int cnt = 0;
        for(int i = 0; i < len; i++){
            if(flowerbed[i] == 0){
                if((stack.isEmpty() || stack.peek() == 0) && (i+1 == len || flowerbed[i+1] == 0)){
                    cnt ++;
                    stack.push(1);
                    if(cnt >= n) return true;
                }
                else {
                    stack.push(0);
                }
            }
            else{
                stack.push(1);
            }
        }
        return false;
    }
}
