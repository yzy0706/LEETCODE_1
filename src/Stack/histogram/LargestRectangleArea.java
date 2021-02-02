package Stack.histogram;

import java.util.Stack;

public class LargestRectangleArea {
    //做法: 建立一个stack装载左边最高的高度, forloop i <= heights.length;
    //1. 如果stack是空的或者heights[i] >= stack.peek(), 证明当前的高度最高, push(i)
    //2. 否则的话stack.pop()出当前最高的高度, 看stack是不是变空了,
    //  a. 就是如果stack里面还有一个第二高的位置res就更新成 最高高度* 最高的位置 - 第二高的位置 -1
    //  b. 否则就直接是当前最高高度 * i
    // 3. 最后再i--往回调一位

    //Runtime : 每次i回调一位再++只是当前这个高度如果没有被加入到stack里去的话, 让他再进行一次与变化了的stack.peek()的比较看能不能成为最高的位置,
    //或者再计算最大面积, 所以最坏的情况就是进行了O(n^2)次, space: O(n)

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
                //比如说i是1的时候， i--到0， 下一轮开始前又会++到1，
                // 这样下一轮开始以后因为heights[0]已经被pop出去了，所以会把height[1]加进去当作左边最高的高度
            }
        }
        return res;
    }
}
