package Stack.shuntingYard;

import java.util.HashMap;
import java.util.Stack;

public class NextGreaterElementI {
    // 做法: 跟503一样用shunting yard做的, 但这个题没有循环nums2, 到了nums2的结尾就终止了
    // 1. 拿一个map记录nums2里所有的数在nums1里的位置, 这个位置也是每个数在res里对应的位置, stack存储在浏览nums2的时候之前碰到的所有没被更大的数消除的数
    // 2. 我们只需要浏览一次nums2, num就是当前nums2里的数, 并在res里, 把stack里所有比当前的数num小的数的位置, 设置为当前的数num
    // 3. 如果stack里还有数证明这些数没在nums2里碰到比他们更大的数, 至少在一次循环里没有碰到, 所以如果他们存在在nums1里都设置为-1

    // Runtime: O(n), Space: O(n)
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        HashMap<Integer, Integer> nums1Pos = new HashMap<>();
        for(int i = 0; i < nums1.length; i++){
            nums1Pos.put(nums1[i], i);
        }
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < nums2.length; i ++){
            int num = nums2[i];
            int top = stack.isEmpty() ? 0 : stack.peek();
            while(!stack.isEmpty() && num > top){
                stack.pop();
                if(nums1Pos.containsKey(top)) res[nums1Pos.get(top)] = num;
                if(stack.isEmpty()) break;
                top = stack.peek();
            }
            stack.push(num);
        }

        while(!stack.isEmpty()){
            int num = stack.pop();
            if(nums1Pos.containsKey(num)){
                res[nums1Pos.get(num)] = -1;
            }
        }
        return res;
    }
}
