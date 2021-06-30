package Tree.DFS;

public class MaxSumOfTwoNonOverlappingSubarray {
    // 做法: 第一遍用了一个类似于backtrack(dfs)的解法, 有点慢, 但其实就是在组合当前位置之后两种可能的length的sum
    // 1. 先把nums各个位置变成到i的所有的数的sum
    // 2. 从位置0开始forloop, dfs所有的位置j, 根据当前cnt是0或者1或者2的可能, 分别尝试不同长度的连续sum +=  nums[j + len - 1] - lastSum), dfs(nums, j + len, cnt + len, sum, len1, len2);

    // Runtime:  O(n^2), Space: O(n);

    int res;
    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        res = 0;
        for(int i = 1; i < nums.length; i ++){
            nums[i] += nums[i - 1];
        }
        dfs(nums, 0, 0, 0, firstLen, secondLen);
        return res;
    }

    public void dfs(int[] nums, int i, int cnt, int sum, int len1, int len2){
        if(cnt == 3){
            res = Math.max(res, sum);
            return;
        }
        if(i >= nums.length){
            return;
        }
        for(int j = i; j < nums.length; j ++){
            int lastSum = j - 1 >= 0 ? nums[j - 1] : 0;
            if(cnt == 1 || cnt == 0){
                if(j + len2 <= nums.length){
                    dfs(nums, j + len2, cnt + 2, sum + nums[j + len2 - 1] - lastSum, len1, len2);
                }
            }
            if(cnt == 2 || cnt == 0){
                if(j + len1 <= nums.length){
                    dfs(nums, j + len1, cnt + 1, sum + nums[j + len1 - 1] - lastSum, len1, len2);
                }
            }
        }

    }
}
