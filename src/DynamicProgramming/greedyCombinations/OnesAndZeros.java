package DynamicProgramming.greedyCombinations;

public class OnesAndZeros {
    //做法: 用int[][] dp = new int[m][n]; 代表m个0和n个1的限制下最多的subset个数
    //1. 浏览strs上的每一个string, 对于每一个string用一个helper统计出0和1的个数: int[0's, 1's]
    //2. 用两个forloop, i从m开始--到0的个数num0, j从n开始--到1的个数num1, 所有比当前这个string的0和1的个数多的dp[i][j] subset都因为string的出现由dp[i-num0][j-num1]的基础上combine增加了一个, 也就是以i-num0个0为最大, j-num1个1的个数为封顶的string个数+1.
    // dp[i][j] = Math.max(dp[i-num[0]][j-num[1]] + 1, dp[i][j]);

    //Runtime: O(n^3), Space: O(mn)

    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m+1][n+1];

        for(String s : strs){
            int[] count = helper(s);
            int num0 = count[0];
            int num1 = count[1];
            for(int i = m; i >= num0; i--){
                for(int j = n; j >= num1; j--){
                    dp[i][j] = Math.max(dp[i-num0][j-num1] + 1, dp[i][j]);
                }
            }
        }

        return dp[m][n];

    }

    private int[] helper(String s){
        int[] res = new int[2];
        for(char c: s.toCharArray()){
            res[c - '0']++;
        }
        return res;
    }
}
