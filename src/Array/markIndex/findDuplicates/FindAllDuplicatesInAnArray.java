package Array.markIndex.findDuplicates;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindAllDuplicatesInAnArray {

    // 做法: 这种在[1, n]的array里找duplicate或者跟448一样找disappeared的元素
    // 又要O(1)space的都可以尝试把当前的数值 - 1作为index来标记某个数是否出现过。
    // 1. int val = Math.abs(nums[i]); if(nums[val-1] < 0) res.add(val); 证明之前已经出现过相同的数把这里变成负数了
    // 2. 每一步最后都把nums[val-1] = - nums[val-1];

    // Runtime: O(n), Space: O(1)
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            int val = Math.abs(nums[i]);
            if(nums[val-1] < 0) res.add(val);
            nums[val-1] = - nums[val-1];
        }
        return res;
    }


// swap做法
    public List<Integer> findDuplicates_swap(int[] nums) {
        List<Integer> res =  new ArrayList<>();
        if(nums == null || nums.length == 0) return res;
        for(int i = 0; i < nums.length; i++){
            int num = nums[i];
            if(num == i + 1 || num == -1){ //当前就是正确的数或者当前已经检查过是duplicate了
                continue;
            }
            if(nums[num-1] == num){ //我想要置换的目的地已经有我需要的这个数了 证明存在两个这样的数, res.add(num);
                nums[i] = -1;
                res.add(num);
                continue;
            }
            swap(nums, i, num - 1);
            i--;  //i--是为了把当前的位置再置换一遍一直到当前的位置是需要的数或者当前的位置是-1
        }
        return res;
    }

    public void swap(int nums[], int i ,int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }





    //hashset比较傻
    public List<Integer> findDuplicates_hashset(int[] nums) {
        List<Integer> res = new ArrayList<>();
        Set<Integer> prev = new HashSet<>();
        for(int i : nums){
            if(!prev.add(i)) res.add(i);
        }
        return res;
    }

}
