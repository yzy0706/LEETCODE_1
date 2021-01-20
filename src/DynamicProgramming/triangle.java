package DynamicProgramming;

import java.util.List;

public class triangle {
    //从三角形的倒数第一行往上推， 每一行的第i个数字， 等于dp[i]和dp[i+1]的最小值加上当前这个数， 因为我们每次只能往下左右两个位置移动
    //所以对于每一行的每一个index来说， 他最后只会被归入dp[i]和dp[i+1]这两个可能的轨迹中， 因为每个node只能到下面左右两个adjacent的node，
    // 那么对于每一层的第i个node来说他所能并入的dp[]也只有dp[i]和dp[i+1]
    // Runtime:triangle的size都浏览了一次， 每次又浏览了每行， 所以是O(n^2), space就是dp的大小O(n)

    public int minimumTotal(List<List<Integer>> triangle) {
            int[] dp = new int[triangle.size() + 1];
            for(int i = triangle.size()-1; i >= 0; i--){
                for(int j = 0; j < triangle.get(i).size(); j++){
                    dp[j] = Math.min(dp[j], dp[j+1]) + triangle.get(i).get(j);
                }
            }
            return dp[0];

    }
}
