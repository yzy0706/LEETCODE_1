package Heap.queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class CampusBikesII {
    //做法: 这是个结合dfs(一路把所有的worker都查一遍) + dp(在给每一个worker分配bike时, 记录之前已经计算出来的distance) + permutation-backtrack(因为boolean[]是引用参数所以我们进入一轮dfs以后在当前的forloop要把bikeUsed[i]还原到false)
    // 1. 建立一个dfs的helper, 建立一个boolean[bLen]去记录当前已经被分配了的bike, 如果当前的dist大于minDist了直接return; 如果当前浏览到的worker已经到了最后一位了比较一下minDist和当前curDist, 然后return.
    // 2. 从从一个worker开始, 每次都forloop所有的bike, 开始给他尝试分配所有的bike, bikeUsed[i] = true, 然后再进入新的dfs方程,进入了以后把当前的bikeUsed[i]变回false, 跟backtrack一个原理
    // Runtime: O(mn), Space: O(n)
    private int minDist = Integer.MAX_VALUE;
    private int wLen, bLen;
    public int assignBikes_dfs(int[][] workers, int[][] bikes) {
        wLen = workers.length;
        bLen = bikes.length;
        dfs(workers, bikes, 0, new boolean[bLen], 0);
        return minDist;
    }

    public void dfs(int[][] workers, int[][] bikes, int numWorker, boolean[] bikeUsed, int curDist){
        if(numWorker == wLen){
            minDist = Math.min(minDist, curDist);
            return;
        }
        if(curDist > minDist){
            return;
        }
        for(int i = 0; i < bLen; i++){
            if(bikeUsed[i]){
                continue;
            }
            bikeUsed[i] = true;
            dfs(workers, bikes, numWorker + 1, bikeUsed, curDist + calDist(workers[numWorker], bikes[i]));
            bikeUsed[i] = false;
        }
    }

    public int calDist(int[] p1, int[] p2){
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }





    public int assignBikes(int[][] workers, int[][] bikes) {
        int wLen = workers.length, bLen = bikes.length;
        int[][] dp = new int[wLen][bLen];
        for(int[] worker : dp) Arrays.fill(worker, Integer.MAX_VALUE);
        List<HashSet<Integer>> arrangements = new ArrayList<HashSet<Integer>>();
        for(int i = 0; i < bikes.length; i++){
            dp[0][i] = Math.abs(workers[0][0] - bikes[i][0]) + Math.abs(workers[0][1] - bikes[i][1]);
            HashSet<Integer> cur = new HashSet<>();
            cur.add(i);
            arrangements.add(cur);
        }
        for(int i = 1; i < wLen; i ++){
            for(int j = 0; j < bLen; j++){
                int closestBike = -1, closestDist = Integer.MAX_VALUE;
                for(int k = 0; k < bLen; k++){
                    if(arrangements.get(j).contains(k)){
                        continue;
                    }
                    else{
                        int curDist = Math.abs(workers[i][0] - bikes[k][0]) + Math.abs(workers[i][1] - bikes[k][1]);
                        if(curDist < closestDist){
                            closestDist = curDist;
                            closestBike = k;
                        }
                    }
                }
                arrangements.get(j).add(closestBike);
                dp[i][j] = dp[i-1][j] + closestDist;
            }
        }
        int res = Integer.MAX_VALUE;
        for(int dist : dp[dp.length-1]){
            res = Math.min(res, dist);
        }
        return res;
    }
}
