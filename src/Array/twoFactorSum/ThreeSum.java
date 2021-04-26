package Array.twoFactorSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

    // 做法: 从forloop + two pointer的基础上
    // 1. 加了一个在forloop完i和找到一个有效结果以后跳过所有相同的数, 这样就省了很多时间
    // 2. 因为跳过了就不用每次在res里找contains, 这样又省了检查整个res的时间
    // Runtime: O(n^2), Space: O(1)
    public List<List<Integer>> threeSum_reviewed(int[] nums) {
        List<List<Integer>> res= new ArrayList<>();
        if(nums.length < 3) return res;
        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 2; i++){
            if(i > 0 && nums[i] == nums[i-1]) continue;
            int l = i + 1, r = nums.length - 1, target = - nums[i];
            while(l < r){
                int sum = nums[l] + nums[r];
                if(sum == target){
                    List<Integer> temp = new ArrayList<>();
                    temp.addAll(Arrays.asList(nums[i], nums[l], nums[r]));
                    res.add(temp);
                    while(l < r && nums[l+1] == nums[l]) l++;
                    while(l < r && nums[r-1] == nums[r]) r--;
                    l++;
                    r--;
                }
                else if(sum > target){
                    r--;
                }
                else{
                    l++;
                }
            }
        }
        return res;
    }



    // 做法： 第一遍自己用forloop + twopointer写的， 好像有点太慢了
    // Runtime: O(n^3), Space: O(1)

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res= new ArrayList<>();
        if(nums.length < 3) return res;
        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 2; i++){
            int l = i + 1, r = nums.length - 1, target = - nums[i];
            while(l < r){
                int sum = nums[l] + nums[r];
                if(sum == target){
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(nums[l]);
                    temp.add(nums[r]);
                    if(!res.contains(temp)) res.add(temp);
                    l++;
                    r--;
                }
                else if(sum > target){
                    r--;
                }
                else{
                    l++;
                }
            }
        }
        return res;
    }
}
