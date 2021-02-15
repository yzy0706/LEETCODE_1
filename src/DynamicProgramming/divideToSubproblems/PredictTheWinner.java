package DynamicProgramming.divideToSubproblems;

public class PredictTheWinner {
    //做法: 属于dp里面记录sub problem的做法, dp[i][j]代表player 1在[i, j]的区间能得到的最大结果
    // 1. 先建立一个int[][] dp, dp[i][i] = nums[i], 用一个int sum记录nums上所有数的和, 最后一步要用
    // 2. 两个for loop, j从0到n; i从j-1一直--到0. 然后就把之后的sub problem分成player1选了i或者j, 对手分别选了j或者选了i+1的四种情况:
    // dp[i, j] = Math.max(Math.min(dp[i+1][j-1], dp[i+2][j]), Math.min(dp[i+1][j-1], dp[i][j-2])); 而且要注意边界情况, 如果在边界外就等于0
    // 3. 检查dp[0, nums.length - 1] >= sum / 2;

    // Runtime: 最多是O(n^2), Space: O(n^2)

    public boolean PredictTheWinner(int[] nums) {
        int len = nums.length;
        if(len % 2 == 0) return true;
        int[][] dp = new int[len][len];
        int sum = 0;
        for(int i = 0; i < len; i++){
            dp[i][i] = nums[i];
            sum += nums[i];
        }

        for(int j = 0; j < len; j++){
            for(int i = j - 1 ; i >= 0; i--){
                int a = (i + 1 < len && j - 1 >= 0)  ? dp[i+1][j-1] : 0;
                int b = (i + 2 < len) ? dp[i+2][j] : 0;
                int c = (j - 2 >= 0) ? dp[i][j-2] : 0; //分别判断边界值
                dp[i][j] = Math.max(Math.min(a, b) + nums[i], Math.min(a, c) + nums[j]);
            }
        }

        return dp[0][len-1] * 2 >= sum;
    }
}
