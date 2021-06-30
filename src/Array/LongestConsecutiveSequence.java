package Array;

import java.util.HashSet;

public class LongestConsecutiveSequence {
    // 做法: 用hashset记录了一遍, 再根据set里面的每个数向高低拓展, 一个个把最长的连续数字找出来
    // Runtime: O(n), Space: O(n)

    public int longestConsecutive(int[] nums) {
        int len = nums.length;
        if(len < 1) return 0;
        HashSet<Integer> set = new HashSet<>();
        for(int n : nums) set.add(n);
        int res = 0;
        while(!set.isEmpty()){
            int n = set.iterator().next();
            set.remove(n);
            int l = n - 1, h = n + 1;
            while(set.contains(l)) set.remove(l --);
            while(set.contains(h)) set.remove(h ++);
            res = Math.max(res, h - l - 1);
        }
        return res;
    }
}
