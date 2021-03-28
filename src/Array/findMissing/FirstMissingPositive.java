package Array.findMissing;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FirstMissingPositive {
    // 做法: 跟find all duplicates的思路差不多, 碰到这种要我们用O(n)复杂度和O(1) Space来找整个array里面缺失的那一个的都可以用nums[nums[i]-1]来标记当前的值
    // 也就是按照每一位的数值来按顺序标记这个数列
    // 1. forloop nums上的每一位, 如果当前位置的值大于0, 小于等于nums.length, 则当前的值可以作为index,
    // 则用一个whileloop一直swap i 和 nums[i]-1上的数, 也可以用swap了以后再i--, 因为下一轮i又会++回来继续判断当前位置的值是不是就是i+1
    // 2. 设置当前最小的missing positive是1, 只要当前res应该还在num上就一直往大的移动, 一直到找到nums上当前res-1位置不是对应的值res, 或者当前nums长度以内的数都是符合条件的

    // Runtime: O(n), Space: O(1);
    public int firstMissingPositive_valToMark(int[] nums) {
        if(nums.length < 1) return 1;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == i + 1 || nums[i] < 0) continue;
            while(nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) swap(nums, i, nums[i] - 1);
        }
        int res = 1;
        while(res <= nums.length && nums[res-1] == res){
            res ++;
        }
        return res;
    }

    public void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }





    public int firstMissingPositive_HashSet(int[] nums) {
        int res = 1;
        Set<Integer> appeared = new HashSet<>();
        for(int i : nums){
            if(!appeared.contains(i)) appeared.add(i);
            while(appeared.contains(res)){
                res ++;
            }
        }
        return res;
    }






    public int firstMissingPositive(int[] nums) {
        int res = 1;
        Arrays.sort(nums);
        for(int i = 0; i < nums.length ; i++){
            int cur = nums[i];
            if(cur < res){
                continue;
            }
            else if(cur == res){
                res ++;
                continue;
            }
            else if(cur > res){
                if(i==0) break;
                if(nums[i-1] < res || i == 0){
                    break;
                }
            }

        }
        return res;
    }
}
