package Stack.reCombine;

public class MinDeletionCostToAvoidRepeatingLetters {
    //做法: 这题我用的一维dp记录当前用掉了多少cost, 从位置1开始,
    //只要当前的位置跟之前那个char是一样的, 那么总要删掉一个cost小的: dp[i] = dp[i-1] + Math.min(cost[i], cost[i-1]); 然后当前这个位置cost[i]变成这两个之间cost大的, 因为删掉当前这个以后可能后面还会有一样的, 就跟重新组合一样, 用stack也能做
    // Runtime: O(n), Space: O(n)
    public int minCost(String s, int[] cost) {
        if(s.length() == 1) return 0;
        int[] dp = new int[s.length()];
        for(int i = 1; i < cost.length; i++){
            if(s.charAt(i) == s.charAt(i-1)){
                dp[i] = dp[i-1] + Math.min(cost[i], cost[i-1]);
                cost[i] = Math.max(cost[i], cost[i-1]);
            }
            else{
                dp[i] = dp[i-1];
            }
        }
        return dp[cost.length-1];
    }
}
