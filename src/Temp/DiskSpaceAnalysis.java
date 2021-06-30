package Temp;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;

public class DiskSpaceAnalysis {

    // dq做法： 跟leetcode上不同的就是， 每次都把dq从最近的开始比i位置的值大的值去掉，以保证dq的最前端是最小值， 正好跟leetcode上那个题相反
    // Runtime: O(n), Space: 0(k), k是segmentLength

    public int diskSpaceAnalysis_reviewed_dq(int numComputer, int[] hardDiskSpace, int segmentLength){
        if(numComputer < 1) return 0;
        int res = 0;
        Deque<Integer> dq = new ArrayDeque<>();
        for(int i = 0; i < hardDiskSpace.length; i++){
            while(!dq.isEmpty() && dq.peekFirst() < i - segmentLength + 1) dq.pollFirst();
            while(!dq.isEmpty() && hardDiskSpace[dq.peekLast()] > hardDiskSpace[i]) dq.pollLast();
            dq.add(i);
            if(i >= segmentLength - 1) res = Math.max(res, dq.peekFirst());
        }
        return res;
    }



    //这个做法用的是类似于sliding window maximum的pq做法， 把不在当前检查范围的int[]拿出来
    public int diskSpaceAnalysis_reviewed_pq(int numComputer, int[] hardDiskSpace, int segmentLength){
        int res = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(segmentLength, (a, b) -> a[1] - b[1]);
        for(int i = 0; i < hardDiskSpace.length; i++){
            pq.offer(new int[]{i, hardDiskSpace[i]});
            while(pq.peek()[0] < i - segmentLength + 1) pq.poll();
            res = Math.min(res, pq.peek()[1]);
        }
        return res;

    }







    //答案用的Deque过了， 这里Deque只能用ArrayDequeue call, 方法是用一个stack来储存当前nums上各个数的index， 用一个pos在res[]上标记
    //当前的位置， 在一个forloop i = 0到 nums.length的过程中来处理stack加上i之前的stack： 用两个whileloop，
    // 第一个whileloop用peek来检查坐标是不是在< i-k+1的范围内，不在的全部poll（）
    //第二个whileloop用peekLast（）来检查是不是比当前的nums[i]小， 小的全部pollLast（）； 最后，stack offer一下i，然后因为i是从0开始的，
    // 我们需要检查是不是已经>=k-1了
    //不然我们是没必要去把运算到i之前的这个stack的peek（）加到res[]里的，
    // Runtime： 全部i都只浏览了一次， 自然是O(n),在stack处理的过程中就算每次我们处理空了也只是另一个O(n), 最后一起就是O(n)；
    // 然后Space也是res[]和stack的合， 最多也就是O(2n） = O(n）

    public int[] maxSlidingWindow_3(int[] nums, int k) {
        int length = nums.length;
        Deque<Integer> deque = new ArrayDeque<>();
        int[] res = new int[length - k + 1];
        int pos = 0;
        for (int i = 0; i < length; i++) {
            while (!deque.isEmpty() && deque.peek() < i - k + 1) {
                deque.poll();
            }
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            deque.offer(i);
            if (i >= k - 1) {
                res[pos] = nums[deque.peek()];
                pos++;
            }
        }
        return res;
    }


    //第三遍看了刀的写法改进的pq写法
    public int[] maxSlidingWindow_pq_3(int[] nums, int k){
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> b[2] - a[2]);
        int[] res = new int[nums.length - k + 1];
        int pos = 0;
        for(int i = 0; i < nums.length; i++){
            while(!pq.isEmpty() && pq.peek()[1] < i - k + 1) pq.poll();
            pq.offer(new int[]{i, nums[i]});
            if(i >= k-1) res[pos++] = pq.peek()[2];
        }
        return res;
    }
        //第二遍用remove过了但是又说runtime error了
    public int[] maxSlidingWindow_pq_2(int[] nums, int k) {
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int l = 0, r = k  ;
        for(int i = 0; i < r && i < nums.length; i++){
            pq.offer(nums[i]);
        }
        if(r >= nums.length) return new int[]{pq.peek()};

        int[] res = new int[nums.length - k + 1];
        int pos = 1;
        res[0] = pq.peek();
        while(r < nums.length){
            pq.remove(nums[l]);
            l++;
            pq.offer(nums[r]);
            r++;
            res[pos] = pq.peek();
            pos++;
        }
        return res;

    }
    // 第一遍不会用pq.remove把这个写复杂了
    public int[] maxSlidingWindow(int[] nums, int k) {

        Queue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int l = 0, r = k-1  ;
        for(int i = 0; i <= r && i < nums.length; i++){
            pq.offer(nums[i]);
        }
        if(r >= nums.length) return new int[]{pq.peek()};
        int[] res = new int[nums.length - k + 1];
        int pos = 0;

        while(r <= nums.length-1){
            for(int i = l; i < r+1; i++){
                pq.offer(nums[i]);
            }
            int cur = pq.peek();
            res[pos] = cur;
            System.out.println(pq.toString());
            l++;
            r++;
            if(r == nums.length) break;
            pq.clear();
            pos++;
        }
        return res;

    }
}
