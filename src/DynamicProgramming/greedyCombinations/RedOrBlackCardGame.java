package DynamicProgramming.greedyCombinations;

public class RedOrBlackCardGame {
    //做法： 这题是要求每一个卡牌状态下的最好收入， 首先我们判断r = 0的情况， 因为没有赢面了直接收入就=0；
    // else if， 如果b = 0， 那么dp[b][r]直接 = r的数量， 这都没有争议
    //主要是不是这两种情况的时候我们要比较r-b和 拿黑牌的概率 * dp[b-1][r]（拿到黑牌的收入） + 拿红牌的概率 * dp[b][r-1] 哪个更大，
    //特别要注意求拿每种牌的概率的时候， 要（float)b/(b+r)， 如果不float分子或者分母中的一个的话会直接return一个0出来，因为java的Integer
    //小于1直接就等于0
    //Runtime： O(n^2), n是红牌或者黑牌的数量， space： O(n)
    public static float game(){
        float[][] dp = new float[27][27];
        for(int r  = 0 ; r < 27; r++){
            for(int b = 0; b < 27; b++){
                if(r == 0) dp[b][r] = 0;
                else if(b == 0) dp[b][r] = r;
                else dp[b][r] = Math.max((float)(r - b), ((float) b/(b+r)) * dp[b-1][r] + ((float)r/(b+r)) * dp[b][r-1]);
            }
        }
        System.out.println(dp[1][18]);
        return dp[26][26];
    }
}
