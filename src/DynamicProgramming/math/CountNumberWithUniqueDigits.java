package DynamicProgramming.math;

public class CountNumberWithUniqueDigits {
    // 做法:
    // 这道题主要是一个数学问题, 我们知道一位数有10个不同数字;
    // 两位数有 一位数的个数 + (1到9一共九个能在第一位的数字) * (跟前面不同但可以是0到9一共9位数字) = 10 + 9 * 9 = 91位
    // 三位数有 91 + 第一位可以有9种可能 * 第二位9种可能 * 第三位8种可能 = 91 + 9 * 9 * 8
    // ...这样依次类推下去, 我们设置一个int 新的可能的单个位数, 一个是上一轮新增的位数
    // 每次 newNumbers *= newPossibleDigit, newPossibleDigit -- ,
    // 然后 dp[i] = dp[i-1] + newNumbers;

    // Runtime: O(n), space: O(n)
    public int countNumbersWithUniqueDigits(int n) {
        if(n == 0) return 1;
        if(n == 1) return 10;
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 10;
        int newPossibleDigit = 9, newNumbers = 9;
        for(int i = 2; i <= n; i++){
            newNumbers *= newPossibleDigit;
            newPossibleDigit --;
            dp[i] = dp[i-1] + newNumbers;
        }
        return dp[n];

    }
}
