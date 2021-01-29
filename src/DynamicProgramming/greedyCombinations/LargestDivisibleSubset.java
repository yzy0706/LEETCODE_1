package DynamicProgramming.greedyCombinations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LargestDivisibleSubset {
    //做法: 这个题主要是去找最长的连续整除的那个数的末尾在哪, 用一个dp记载
    // 1. 我们先sort好这个nums, 然后我们去forloop上面的每一个数, 并在每一个数的基础上一直往左找右没有数可以被nums[i]整除, 可以的话dp[i] = Math.max(dp[i], dp[j] + 1)
    // 2. 我们浏览一遍dp, 找出dp值最大的位置maxIndex
    // 3. 现在curDp = dp[maxIndex]就是我们答案要找的连续整除数列的数的个数, curNum = nums[maxIndex]就是答案的最右边最大的那个数, 一直往左边forloop, 找到每一个dp[i]正好是递减的curDp的i, 而且他还要能被curNum整除, 加入到res里, 并return res;

    //Runtime: O(n^2), space: O(n)
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        int maxIndex = 0;

        for(int i = 1; i < nums.length; i++){
            for(int j = i-1; j >= 0; j--){
                if(nums[i] % nums[j] == 0){
                    dp[i] = Math.max(dp[i], dp[j] + 1); //从每一个i的左边一个j往左浏览, 如果能整除这个j证明可以成为连续整除的subset的一部分, 更新dp[i]
                }
            }
        }

        for(int i = 0; i < dp.length; i++){
            if(dp[i] > dp[maxIndex]){
                maxIndex = i;
            }
        }

        List<Integer> res = new ArrayList<>();
        int temp = nums[maxIndex];
        int curDp = dp[maxIndex];
        for(int i = maxIndex; i >= 0; i--){
            if(temp % nums[i] == 0 && dp[i] == curDp){
                res.add(nums[i]); //如果整除就把能被整除的数放进res, 就是最长的整除数列的一部分
                temp = nums[i]; //更新一下temp让他整除前面的数;
                curDp --;
            }
        }
        return res;
    }
}
