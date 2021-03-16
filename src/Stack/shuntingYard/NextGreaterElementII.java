package Stack.shuntingYard;

import java.util.*;

public class NextGreaterElementII {
    //做法: 建立一个int[] res, 所有位置都初始化为 -1, 这样对于每一个位置来说如果之后浏览不能碰到比他大的数就会保持-1
    //forloop整个nums两遍, 用的stack来存储到i之前浏览过的所有index, 每浏览到都会用shunting yard的方式, 把stack里比当前位置的数小的位置全部都poll掉
    //Runtime: O(n), Space: O(n)
    public int[] nextGreaterElements_stack(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int len = nums.length;
        int[] res = new int[len];
        Arrays.fill(res, -1);

        for(int i = 0; i < 2 * len; i++){
            int index = i % nums.length;
            int topIndex = stack.isEmpty() ? 0 : stack.peek();
            while(!stack.isEmpty() && nums[index] > nums[topIndex]){
                topIndex = stack.pop();
                res[topIndex] = nums[index];
                if(stack.isEmpty()) break;
                topIndex = stack.peek();
            }
            stack.push(index);
        }

        return res;
    }

    //试图用dfs做失败了 【
    int[] res;
    public int[] nextGreaterElements_dfs(int[] nums) {
        if(nums.length < 1) return null;
        if(nums.length == 1) return new int[]{-1};
        res = new int[nums.length];
        Arrays.fill(res, Integer.MAX_VALUE);
        for(int i = 0; i < nums.length; i++){
            findNext(nums, i);
        }
        return res;
    }

    private void findNext(int[] nums, int i){
        if(res[i] != Integer.MAX_VALUE) return;
        int next = (i + 1) % nums.length;
        if(nums[next] > nums[i]){
            res[i] = nums[next];
            return;
        }
        else{
            findNext(nums, next);
            if(res[next] == -1){
                res[i] = -1;
                return;
            }
            while(next != i && nums[next] <= nums[i]){
                next = (next + 1) % nums.length;
            }
            if(next == i) res[i] = -1;
            else res[i] = nums[next];
        }
    }





    //试图用PriorityQueue做的但TLE了
    public int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];
        int len = nums.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> nums[b] - nums[a]);
        for(int i = 0; i < nums.length; i++){
            pq.offer(i);
        }
        for(int i = 0; i < nums.length; i++){
            int largestIndex = pq.peek();
            if(largestIndex == i || nums[largestIndex] == nums[i]){
                res[i] = -1;
            }
            else{
                int nearst = largestIndex, topIndex = largestIndex;
                int dist = topIndex > i ? topIndex - i : len + topIndex - i;
                List<Integer> polled = new ArrayList<>();
                while(!pq.isEmpty() && nums[topIndex] > nums[i]){
                    polled.add(pq.poll());
                    int curDist = topIndex > i ? topIndex - i : len + topIndex - i;
                    if(curDist < dist){
                        dist = curDist;
                        nearst = topIndex;
                    }
                    if(pq.isEmpty()){
                        break;
                    }
                    topIndex = pq.peek();
                }
                res[i] = nums[nearst];
                pq.addAll(polled);
            }
        }
        return res;
    }
}
