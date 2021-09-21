package Backtrack;

import java.util.Arrays;

public class ValidTriangleNumber {
    //做法: 第一遍先sort再用permutation做的, 有点慢
    //Runtime: O(nlog(n)), Space: O(1);

    int res = 0;
    int len;

    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        len = nums.length;
        dfs(nums, 0, 0, 0);
        return res;
    }

    public void dfs(int[] nums, int i, int sum, int cnt){
        if(i == len){
            return;
        }
        if(cnt == 2){
            for(int j = i; j < len; j ++){
                if(nums[j] >= sum){
                    res += j - i;
                    return;
                }
            }
            res += len - i;
            return;
        }
        for(int j = i; j < len; j ++){
            dfs(nums, j + 1, sum + nums[j], cnt + 1);
        }

    }


    //做法: 第二遍直接backtrack, 记得引用传导要建立新的array, 但TLE了
    //Runtime: O(n^3), Space: O(3);

    public int triangleNumber_permutation(int[] nums) {
        len = nums.length;
        dfs(nums, 0, new int[3], 0);
        return res;
    }

    public void dfs(int[] nums, int i, int[] arr, int cnt){
        if(cnt == 3){
            Arrays.sort(arr);
            if(arr[0] + arr[1] > arr[2]){
                res ++;
            }
            return;
        }
        for(int j = i; j < len; j ++){
            arr[cnt] = nums[j];
            int[] tmp = Arrays.copyOf(arr, 3);
            dfs(nums, j + 1, tmp, cnt + 1);
            arr[cnt] = 0;
        }
    }
}
