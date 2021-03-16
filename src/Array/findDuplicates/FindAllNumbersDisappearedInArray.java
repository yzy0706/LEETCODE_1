package Array.findDuplicates;

import java.util.ArrayList;
import java.util.List;

public class FindAllNumbersDisappearedInArray {
    // 做法: 这题跟find all duplicates类似, 把当前的val当作坐标, 但是我们是要标记什么数出现过而不是出现过几次, 所以已经变成负数的位置就不用再反过来一遍了 if(nums[val-1] > 0) nums[val-1] = - nums[val-1];
    // 最后再把大于0的位置, 也就是都没改过的位置加入到res里, 证明这些位置对应的val没在res里出现过
    // Runtime: O(n), Space: O(1)

    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            int val = Math.abs(nums[i]);
            if(nums[val-1] > 0) nums[val-1] = - nums[val-1];
        }

        for(int i = 0; i < nums.length; i++){
            if(nums[i] > 0) res.add(i+1);
        }
        return res;
    }
}
