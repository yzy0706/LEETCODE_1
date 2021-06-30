package Stack.histogram;

import java.util.Stack;

public class BuildingWithAnOceanView {
    //做法: 第一个做法是用stack + greedy做的, 从右到左随时判断当前的位置是不是右边到当前最高的
    //Runtime: O(n), Space: O(n);

    public int[] findBuildings(int[] heights) {
        int n = heights.length, max = 0;
        Stack<Integer> stack = new Stack<>();
        for(int i = n - 1; i >= 0; i --){
            if(heights[i] > max){
                max = heights[i];
                stack.push(i);
            }
        }
        int[] res = new int[stack.size()];
        int pos = 0;
        while(!stack.isEmpty()){
            res[pos ++] = stack.pop();
        }
        return res;
    }
}
