package Temp;

import java.util.List;

public class MinimumTotalContainerSize {

    //做法: 新做法是用2d dp, dp[i][j]代表在第i天做了j任务的difficulty
    // 1. 先forloop一遍i, 从0到len-1, dp[0][i]等于[0, i]的最大值
    // 2. a. 从i = 1开始forloop天数到d-1
    //    b. j从i开始forloop每一个任务到len-1, 用int maxBetweenIJ = jobDifficulty[j];来记录j任务到i任务之间的最大值,  并且先初始dp[i][j] = Integer.MAX_VALUE;
    //    c. k从j开始一直往前到i, 所以k在[i, j]的中间, 用difficulty[k]的值更新maxBetweenIJ, 并且 dp[i][j] = Math.min(dp[i][j], dp[i-1][k-1] + maxBetweenIJ); 相当于k位置以前的任务是上一天来做
    // Runtime: O(n^2*d) , Space: O(dn);


    public int minContainerSize(int[] p, int K) {
        int len = p.length;
        if(K > len) return -1;
        int[][] dp = new int[K][len];
        int maxBeforeI = 0;
        for(int i = 0; i < len; i++){
            maxBeforeI = Math.max(maxBeforeI, p[i]);
            dp[0][i] = maxBeforeI; //记录一下如果第0天就做到了i job的difficulty
        }

        for(int i = 1; i < K; i ++){
            for(int j = i; j < len; j ++){
                int maxBetweenIJ = p[j];
                dp[i][j] = Integer.MAX_VALUE;
                for(int k = j; k >= i; k--){
                    maxBetweenIJ  = Math.max(maxBetweenIJ, p[k]);
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][k-1] + maxBetweenIJ);
                }
            }
        }
        return dp[K-1][len-1];
    }





    //就是sort一下然后简单比较一下 arr[n] 和 arr[n-1]+1哪个更小就用哪个
    //Runtime: 因为sort了又浏览了n次所以是O(nlog(n)), space是O(n), 就是
    public int packagingAutomation(int numGroups, List<Integer> arr){
        arr.sort((a, b) -> b - a);
        if(arr.get(0) != 1) return 0;
        int[] l = new int[numGroups];

        int cnt = 0;
        for(Integer i : arr){
            l[cnt] = i;
            cnt++;
        }

        for(int i = 1; i < arr.size(); i++){
            int cur = arr.get(i);
            cur = Math.min(arr.get(i-1)+1, arr.get(i));
            l[i] = cur;
            }

            return l[numGroups-1];
    }
}
