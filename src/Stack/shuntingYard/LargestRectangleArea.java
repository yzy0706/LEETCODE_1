package Stack.reconstruct;

import java.util.Stack;

public class LargestRectangleArea {
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for(int i = 0; i <= len; i++){
            int curHeight = i == len ? 0 : heights[i];
            if(stack.isEmpty() || curHeight >= heights[stack.peek()]){
                stack.push(i);
            }
            else{
                int highestIndex = stack.pop();
                res = Math.max(res, heights[highestIndex] * (stack.isEmpty()? i : i - 1 - stack.peek()));
                i--;
                //比如说i是1的时候， i--到0， 下一轮开始前又会++到1， 这样下一轮开始以后因为heights[0]已经被pop出去了，
                // 所以会把height[1]加进去当作左边最高的高度
            }
        }
        return res;
    }
}
