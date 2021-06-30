package Temp;

import java.util.PriorityQueue;

public class CutOffRank {
    // 我自己用pq写的， 因为arrays.sort()没法降序排列， 我嫌麻烦
    // Runtime: O(nlog(n)), Space: O(nlog(n))

    public int cutOffRank_reviewed(int cutOffRank, int num, int[] scores){
        if(scores.length == 0) return 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a) ;
        for(int i : scores) pq.offer(i);
        int cnt = 0, res = 0;
        while (!pq.isEmpty() && cnt <= cutOffRank){
            int cur = pq.poll();
            res ++;
            cnt ++;
            while(!pq.isEmpty() && pq.peek() == cur){
                pq.poll();
                res ++;
            }
        }
        return res;
    }



    public int cutOffRank(int cutOffRank, int num, int[] scores) {
        if(cutOffRank == 0) return 0;
        int[] cache = new int[101];
        for (int n : scores){
            cache[n]++;
        }
        int  res = 0;
        for (int i = 100; i > 0; i--){
            if (cutOffRank <= 0) break;
            cutOffRank -= cache[i];
            res += cache[i];
        }
        return res;
    }

}
