package HashTable.hashMap;

import java.util.HashMap;

public class DegreeOfArray {
    // 做法: 用了hashmap做的, forloop了两次
    // 1. 记录每一个数字的第一次和最后一次出现, 以及频率, 确定整个array的degree
    // 2. 浏览map, 如果有频率等于degree, 看他是不是最短subarray
    // Runtime: O(n), Space: O(n);

    public int findShortestSubArray(int[] nums) {
        int degree = 0;
        HashMap<Integer, int[]> map = new HashMap<>();
        for(int i = 0; i < nums.length; i ++){
            map.putIfAbsent(nums[i], new int[]{i, i, 0});
            map.get(nums[i])[2] ++;
            map.get(nums[i])[1] = i;
            degree = Math.max(degree, map.get(nums[i])[2]);
        }
        int res = Integer.MAX_VALUE;
        for(int n : map.keySet()){
            int[] record = map.get(n);
            if(record[2] == degree){
                res = Math.min(res, record[1] - record[0] + 1);
            }
        }
        return res;
    }
}
