package DynamicProgramming.accumulate;

public class MinDifficultyOfAJobSchedule {
    //做法: 新做法是用2d dp, dp[i][j]代表在第i天做了j任务的difficulty
    // 1. 先forloop一遍i, 从0到len-1, dp[0][i]等于[0, i]的最大值
    // 2. a. 从i = 1开始forloop天数到d-1
    //    b. j从i开始forloop每一个任务到len-1, 用int maxBetweenIJ = jobDifficulty[j];来记录j任务到i任务之间的最大值,  并且先初始dp[i][j] = Integer.MAX_VALUE;
    //    c. k从j开始一直往前到i, 所以k在[i, j]的中间, 用difficulty[k]的值更新maxBetweenIJ, 并且 dp[i][j] = Math.min(dp[i][j], dp[i-1][k-1] + maxBetweenIJ); 相当于k位置以前的任务是上一天来做
    // Runtime: O(n^2*d) , Space: O(dn);


    public int minDifficulty_reviewed(int[] jobDifficulty, int d) {
        int len = jobDifficulty.length;
        if(d > len) return -1;
        int[][] dp = new int[d][len];
        int maxBeforeI = 0;
        for(int i = 0; i < len; i++){
            maxBeforeI = Math.max(maxBeforeI, jobDifficulty[i]);
            dp[0][i] = maxBeforeI; //记录一下如果第0天就做到了i job的difficulty
        }

        for(int i = 1; i < d; i ++){
            for(int j = i; j < len; j ++){
                int maxBetweenIJ = jobDifficulty[j];
                dp[i][j] = Integer.MAX_VALUE;
                for(int k = j; k >= i; k--){
                    maxBetweenIJ  = Math.max(maxBetweenIJ, jobDifficulty[k]);
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][k-1] + maxBetweenIJ);
                }
            }
        }
        return dp[d-1][len-1];
    }
    // 2d dp的做法：

    //做法: dp[i]代表着当还有dayLeft天剩下的时候从第i个job开始做总共消耗多少difficulty, dp[i] = Math.min(dp[i], maxTillToday + dp[j+1])
    //1. 首先从最后一个job开始, 把dp[i]换成 dp[i+1]和 jobDifficulty[i] 更大的那个
    //2. 一共有三层forloop
    //a. 首先我们dayLeft肯定要从2开始到d, 因为我们至少要留两天给两个工作, 而且是倒序来的, 第一位就还剩下d天
    //b. i从0开始到difficulty - dayLeft, 因为我们至少要给dayLeft的每天留一个工作, maxTillToday = 0,  dp[i] = Integer.Max_VALUE;
    //c. j从i开始到difficulty - dayLeft, 作为一个分割, maxTillToday根据 difficulty[j]更新一下, 看哪个大, dp[i] = Math.min(dp[i], maxTillToday + dp[j+1])
    //3. 最后return dp[0];

    //Runtime: O(n^2*d), Space: O(n)
    public int minDifficulty(int[] jobDifficulty, int d) {
        int len = jobDifficulty.length;
        if (d > len) return -1;
        int[] dp = new int[len + 1];
        for (int i = len - 1; i >= 0; i--) {
            dp[i] = Math.max(dp[i + 1], jobDifficulty[i]);
        }
        for (int dayLeft = 2; dayLeft <= d; dayLeft++) {
            for (int i = 0; i <= len - dayLeft; i++) {
                dp[i] = Integer.MAX_VALUE;
                int maxTillToday = 0;
                for (int j = i; j <= len - dayLeft; j++) { //最少要给每天留一个工作
                    maxTillToday = Math.max(maxTillToday, jobDifficulty[j]); //更新一下一直第j天时的最大difficulty
                    dp[i] = Math.min(dp[i], dp[j + 1] + maxTillToday); //根据j+1看一下如果从j+1个工作开始作为分界线加上当前截止的所消耗的difficulty : 从i个工作作为分界线的difficult, 看看哪个更小
                }
            }
        }
        return dp[0];
    }
}
