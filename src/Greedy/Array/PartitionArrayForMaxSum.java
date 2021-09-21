package Greedy.Array;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class PartitionArrayForMaxSum {
    // 做法: 用的double forloop的dp做法: dp[i] = Math.max(dp[i], Max(arr[j]...arr[i]) * (i - j + 1) + dp[j - 1]), dp[i]代表的是之前所有数的和的最大结果
    // Runtime: O(n^2), Space: O(n)

    public int maxSumAfterPartitioning_dp(int[] arr, int k) {
        int len = arr.length;
        int[] dp = new int[len];
        for(int i = 0; i < len; i++){
            int tmpMax = arr[i];
            for(int j = i; j >= i - k + 1 && j >= 0; j --){
                if(arr[j] > tmpMax){
                    tmpMax = arr[j];
                }
                if(j - 1 >= 0){
                    dp[i] = Math.max(dp[i], tmpMax * (i - j + 1) + dp[j - 1]);
                }
                else{
                    dp[i] = Math.max(dp[i], tmpMax * (i - j + 1));
                }
            }
        }
        return dp[len - 1];
    }



    // 第一遍想用pq做没做出来， 应该用dp
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int len = arr.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (int i = 0; i < len; i++) {
            pq.offer(new int[]{arr[i], i});
        }
        HashSet<Integer> edited = new HashSet<>();
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int index = top[1];
            int l = index - k + 1 < 0 ? 0 : index - k + 1, r = l + k - 1 < len ? l + k - 1 : len - 1;
            int curSum = 0, minS = -1, minSum = Integer.MAX_VALUE;
            Set<Integer> minSet = new HashSet<>();
            for (int i = l; i <= r; i++) {
                curSum += arr[i];
            }
            while (r < len) {
                curSum += arr[r++];
                if (curSum < minSum) {
                    minSum = curSum;
                    minS = l;
                }
                curSum -= arr[l++];
            }

        }
        return 0;
    }
}
