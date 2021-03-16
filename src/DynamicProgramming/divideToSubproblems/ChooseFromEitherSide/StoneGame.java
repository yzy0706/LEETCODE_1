package DynamicProgramming.divideToSubproblems.ChooseFromEitherSide;

public class StoneGame {

    //做法： 这个做法是借鉴了PredictTheWinner那道题的， 如果是碰到这种从两头轮流选一个才谁赢的题目，
    // dp[i][j]代表player1在i， j这段中能取得的最大价值都可以用到这个2D DP,
    // 1. 用一个sum记录当前所有数的和， forloop j从0到len-1， 再forloop i从j-1到0
    // 2. 用mid， bothI， bothJ分别求出来两个人分别选了i， j（当前player1可以选i或者j）， 或者是都选了i， 都选了j四种情况比较大小
    // 3. mid + piles[i]， mid + pile[j], bothI + pile[i], bothJ + pile[j] 更新每一段dp[i][j]的最大值
    // 4. 检查dp[0][len-1]是不是大于sum的一半

    // Runtime： O（n^2）， Space： O（n^2)
    public boolean stoneGame(int[] piles) {
        int len = piles.length;
        if(len % 2 != 0) return true;
        int[][] dp = new int[len][len];
        int sum = 0;
        for(int j = 0; j < piles.length; j++){
            dp[j][j] = piles[j];
            sum += piles[j];
            for(int i = j - 1; i >= 0; i--){
                int mid = dp[i+1][j-1];
                int bothI = i + 2 < len ? dp[i+2][j] : 0;
                int bothJ = j - 2 >= 0 ? dp[i][j-2] : 0;
                dp[i][j] = Math.max(Math.max(mid + piles[i], mid + piles[j]), Math.max(bothI + piles[i], bothJ + piles[j]));
            }
        }
        return dp[0][len-1] * 2 > sum;
    }
}
