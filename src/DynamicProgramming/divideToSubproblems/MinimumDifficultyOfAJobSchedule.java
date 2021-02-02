package DynamicProgramming.divideToSubproblems;

public class MinimumDifficultyOfAJobSchedule {
    public int minDifficulty(int[] jobDifficulty, int d) {
        int len = jobDifficulty.length;
        if (d > len) return -1;
        int[] dp = new int[jobDifficulty.length + 1];
        for (int i = jobDifficulty.length - 1; i >= 0; i--) {
            dp[i] = Math.max(dp[i + 1], jobDifficulty[i]);
        }
        for (int dayLeft = 2; dayLeft <= d; dayLeft++) {
            for (int i = 0; i <= d - dayLeft; i++) {
                dp[i] = Integer.MAX_VALUE;
                int maxToday = 0;
                for (int j = i; j <= d - dayLeft; j++) {
                    maxToday = Math.max(maxToday, jobDifficulty[j]); //更新一下第j天时的最大difficulty
                    dp[i] = Math.min(dp[i], dp[j + 1] + maxToday); //根据j+1看一下如果从j+1个工作开始作为分界线加上当前截止的所消耗的difficulty : 从i个工作作为分界线的difficult, 看看哪个更小
                }
            }
        }
        return dp[0];
    }
}
