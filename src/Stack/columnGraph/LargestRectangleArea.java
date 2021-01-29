package Stack.columnGraph;

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
            }
        }
        return res;
    }
}
