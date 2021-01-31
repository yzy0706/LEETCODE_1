package DynamicProgramming.greedyCombinations;

import java.util.Arrays;

public class CoinCombinations {
    //做法: 对于每一个coin, 我都forloop它的面值一直到amount, 如果dp[i-coin]也有值的话代表着到这个值需要的最少的硬币个数, 加上这枚硬币就是+1, 然后与dp[i]比
    //所以我们得到如果 (dp[i - coin] != Integer.Max_Value) dp[i] = Math.min(dp[i], dp[i-coin]);
    //Runtime: 最坏的情况是我每次在coin上都加了整个amount的大小, 所以是O(n^2), Space是O(N), N是amount的大小
    public int coinChange(int[] coins, int amount) {
        if(amount < 1) return 0;
        int[] dp = new int[amount+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for(int coin : coins){
            for(int i = coin; i <= amount; i++){
                if(dp[i - coin] != Integer.MAX_VALUE) dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }

        return dp[amount] == Integer.MAX_VALUE? -1 : dp[amount];
    }






    //第一遍写的
    public int coinChange_self(int[] coins, int amount) {
        if(coins.length < 1) return -1;
        Arrays.sort(coins);
        int[] dp = new int[coins.length];
        dp[coins.length-1] = amount / (coins[coins.length-1]
        );
        amount = amount % dp[coins.length-1];
        for(int i = coins.length-2; i >= 0; i--){
            dp[i] = amount/ coins[i] + dp[i+1];
            amount = amount % coins[i];
            if(amount == 0) return dp[i];
        }
        return -1;
    }
}
