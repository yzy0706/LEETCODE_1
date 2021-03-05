package Greedy.Array;

public class MaxPointsYouCanObtainFromCards {
    //做法: 本来想用dp做, 结果用了简单的greedy做法
    // 1. 先计算出如果全部都取右边的数一共的和是多少, 用res记录他
    // 2.forloop i到k, 用left来记录左边我们取的数的和, 每次我们要得到i, 就要对应的从right里面删除掉 cardPoints[len - k + i]; (是从len-k开始的第i个), 更新res = Math.max(res, right + left);
    //Runtime: O(N), space: O(1)
    public int maxScore_greedy(int[] cardPoints, int k) {
        int left = 0, right = 0, len = cardPoints.length;
        for(int i = len - 1; i >= len - k; i --){
            right += cardPoints[i];
        }
        int res = right;

        for(int i = 0; i < k; i++){
            right -= cardPoints[len - k + i];
            left += cardPoints[i];
            res = Math.max(res, right + left);
        }
        return res;
    }




    public int maxScore(int[] cardPoints, int k) {
        int len = cardPoints.length;
        int[] cardToTake = new int[2*k];
        int mark = 0;
        for(int i = 0; i < k; i++){
            cardToTake[i] = cardPoints[i];
            mark = i;
        }
        mark ++;
        for(int i = len - k; i < len; i++){
            cardToTake[mark++] = cardPoints[i];
        }
        int[][] dp = new int[len][len];
        for(int i = 0; i < k; i ++){
            for(int j = 2*k - 1; j >= k; j--){
                dp[i+1][j+1] = Math.max(dp[i][j+1] + cardToTake[i], dp[i+1][j] + cardToTake[j]);
            }
        }
        return dp[1][k+1];
    }
}
