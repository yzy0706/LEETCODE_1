package DynamicProgramming.greedyCombinations;

public class CoinCombinationsII {
    // 做法: 这还是类似于combination的题, 当我们需要看一个数怎么被另外几个数组合的时候成一个确定的sum的时候,
    // 我们就要用dp记录sum与元素之间的差有几种组合, 要用双重forloop在浏览每一个元素的同时给他能组成的数, dp[i] += dp[i - coin];

    //1. 建立一个dp, 代表着每一个数可以被组成的的组合个数, dp[0] = 1
    //2. 我们forloop每个元素, 在这其中forloop >= 这个元素的数, 因为我们只要[a, b]而不要[b, a]
    //3. 这个数i肯定由  i-coin + coin 组成, 每找到一个数i那么这个数的组合就会增加dp[i - coin]个, 所以找到每个i的 dp[i] += dp[i - coin]; (coin可以被任何其他的元素取代)
    //4. return dp[amount]就行

    //Runtime: O(n^2), space: O(n)
    public int change_solution(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for(int coin : coins){
            for(int i = coin; i <= amount; i++){
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }






    //第一遍自己写的
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for(int i = 1; i <= amount; i++){
            for(int coin : coins){
                if(i - coin >= 0){
                    dp[i] += dp[i - coin];
                }
            }
        }
        return dp[amount];
    }
}
