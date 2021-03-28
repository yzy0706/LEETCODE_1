package DynamicProgramming.accumulate;

public class MinimumCostForTickets {
    // 做法: 这题我自己做的时候主要是直接用了days的长度来建立dp, 这样的话dp[i]也是当前这一天做完以后的总cost, 但dp[i-1]不确定是在哪一天就相对比较复杂, 所以看了答案改成了一个用days[days.length - 1] + 1作为dp的长度
    // 1. 建立一个boolean[] 来记录哪一天需要旅游, 那一天变成true
    // 2. forloop整个dp
    //      a. 如果第i天不需要旅游 dp[i] = dp[i-1];
    //      b. 如果第i天需要旅游, 就要比较昨天的cost + costs[0] : 买一周的周票前的那一天的cost + costs[1] : 买月票的前一天天的cost + costs[2]
    //  dp[i] = Math.min(dp[Math.max(0, i-7)] + costs[1], dp[Math.max(0, i - 30)] + costs[2]);
    //           dp[i] = Math.min(dp[i], dp[i-1] + costs[0]);

    //Runtime: O(n), Space: O(n), n是最后的一天的天数

    public int mincostTickets_2nd(int[] days, int[] costs) {
        int lastDay = days[days.length - 1];
        int[] dp = new int[lastDay + 1];
        boolean[] travelDays = new boolean[lastDay + 1];
        for(int day : days){
            travelDays[day] = true;
        }
        for(int i = 1; i < dp.length; i++){
            if(!travelDays[i]){
                dp[i] = dp[i-1];
            }
            else{
                dp[i] = Math.min(dp[Math.max(0, i-7)] + costs[1], dp[Math.max(0, i - 30)] + costs[2]);
                dp[i] = Math.min(dp[i], dp[i-1] + costs[0]);
            }
        }
        return dp[dp.length-1];
    }





    public int mincostTickets(int[] days, int[] costs) {
        int[] dp = new int[days.length];
        for(int i = 0; i < days.length; i++){
            dp[i] = i == 0 ? costs[0] : dp[i-1] + costs[0];
        }
        for(int i = 0; i < days.length; i++){
            int curDay = days[i];
            int startOf7 = curDay - 6, startOf30 = i - 29;
            for(int j = i; j >= 0; j --){
                int jDay = days[j];
                int costBefore = j == 0? 0 : dp[j-1];
                if(jDay >= startOf7) dp[i] = Math.min(dp[i], costBefore + costs[1]);
                if(jDay >= startOf30) dp[i] = Math.min(dp[i], costBefore + costs[2]);
            }
            dp[i] = i == 0 ? dp[i] : Math.min(dp[i], dp[i-1] + 2);
        }
        return dp[days.length-1];
    }
}
