package Heap.queue;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class MaxNumberOfEatenApples {

    // 做法: 这题一开始我自己用的greedy, 每次如果当前这一篮子某个日子来的苹果比当前这一天的保质期短的话, 那就先吃之前的, 但这个想法没有考虑到之前所有日子的剩下的每一篮子苹果, 所以应该用PriorityQueue<int[]> pq来记录并且根据变质日期排列每一个日子长出来的苹果, new int[]{苹果数量, 保质期};
    // 1. 建立一个pq<>((a, b) -> (a[1] - b[1])), forloop每一天的苹果, 而且如果pq还不是空的情况下还要继续forloop, 因为就算n天之后还可以继续吃
    //      a. 如果当前i < n, 那今天还会长出新的苹果, 先放到pq里;
    //      b. 如果pq.peek()[1] <= i, 证明有些苹果已经变质了, 把他们poll()出来
    //      c. 如果poll()出来变质的苹果pq已经空了, 证明今天没苹果吃了, 直接continue, 而不是break, 因为之后可能还有苹果吃
    //      d. 到了这一步证明今天有苹果吃, 直接res++, 然后看 --pq.peek()[0] == 0 ? 如果等于0吃完一个这一天的苹果就没有了, 直接把这一天的poll()出来

    // Runtime: O(nlog(n)), Space: O(n)

    public int eatenApples_discussion(int[] apples, int[] days) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        int res = 0, n = days.length;
        for(int i = 0; i < n || !pq.isEmpty(); i++){
            if(i < n) pq.offer(new int[]{apples[i], i + days[i]});
            while(!pq.isEmpty() && pq.peek()[1] <= i) pq.poll();
            if(pq.isEmpty()) continue;
            if(--pq.peek()[0] == 0) pq.poll();
            res ++;
        }
        return res;
    }



    // 第一遍我用greedy写的， 感觉好像不太对， 因为我们每次吃掉的苹果应该是当前结束的最早的那个日子的苹果， 所以我们要储存所有的日子开始时剩下的苹果
    public int eatenApples(int[] apples, int[] days) {
        Set<Integer> appleDays = new HashSet<>();
        for(int i = 0; i < apples.length; i++){
            if(apples[i] == 0){
                continue;
            }
            else{
                int left = appleDays.add(i) ? apples[i] - 1 : apples[i];
                for(int j = i + 1; j < i + days[i]; j++){
                    if(left == 0){
                        break;
                    }
                    if(!appleDays.contains(j) &&  (j >= apples.length || apples[j] == 0 || i + days[i] < j + days[j])){
                        appleDays.add(j);
                        left --;
                    }
                }
            }
        }
        return appleDays.size();
    }
}
