package Stack.histogram;

import java.util.Stack;

public class TrappingRainWater {
    //做法: 这题主要是用stack存储每一个位置的高度, 当碰到一个height[i] > height[stack.peek()]时, 用whileLoop浏览stack里所有比当前矮的高度,
    //1. 洼地值middleHeight一直变化, 一直是stack.pop()
    //2. leftHeight左边的高度 = height[stack.peek()], 我们现在还没有判断curHeight是不是大于leftHeight所以不能随便就把leftHeight直接pop了
    //3. (用左右边比较矮的高度 - 洼地高度) * (i - leftIndex - 1);

    //Runtime: O(n^2), space: O(n)
    public int trap(int[] height) {
        int res = 0;
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(0);
        for(int i = 1; i < height.length; i++){
            int curHeight = height[i];
            while(!stack.isEmpty() && curHeight > height[stack.peek()]){
                int middleHeight = height[stack.pop()];
                if(!stack.isEmpty()){
                    int leftIndex = stack.peek();
                    int leftHeight = height[leftIndex];
                    int lowerHeight = Math.min(leftHeight, curHeight);
                    res += (lowerHeight - middleHeight) * (i - leftIndex - 1);
                }
            }
            stack.push(i);
        }
        return res;

    }
}
