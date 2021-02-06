package HashTable.hashMap;

import java.util.HashMap;

public class SubarraySumEqualsK {
//做法: 类似于dp的做法, 用map存储<每一次sum, 这样sum的次数>, 因为sum(i,j)=sum(0,j)-sum(0,i),
    //1. 记录每一次sum和k的差, 如果map.containsKey(sum - k); 证明之前有连续加到的和等于sum - k, 那他只需要 - 这些组合, 所以count += map.get(sum-k);
    //2. 把当前sum放到map里去, 如果已经有这个sum了就value+1, 一开始要把(0, 1)放进去, 也就是差等于0的情况

    //Runtime: O(n), space:O(n)
    public int subarraySum(int[] nums, int k) {
        int count = 0, sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // sum == k, 肯定至少有一个这个结果
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            if(map.containsKey(sum - k)){
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return count;
    }
}
