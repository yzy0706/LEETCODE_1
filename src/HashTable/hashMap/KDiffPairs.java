package HashTable.hashMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KDiffPairs {
    // 做法: 跟twoSum一样, 看到这种和是固定的都可以用HashMap<>();
    // 1. 用HashMap<>()存当前这个数和这个数出现了多少次
    // 2. forloop所有的number:
    //   a. 如果当前map里有这个cur, 如果k == 0而且cur只出现过一次, res++, 如果k != 0, 且这个数出现过肯定是寻找过 cur+k和cur-k了, 直接continue;
    //   b. 如果当前k里面没有这个cur的话, 找一下map.containsKey()cur+k和cur-k, res分别++, 然后map里放入当前的k
    // 3. return res;

    // Runtime: O(n), Space: O(n)
    public int findPairs_self(int[] nums, int k) {
        if(k < 0) return 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for(int i = 0; i < nums.length; i++){
            int cur = nums[i];
            if(map.containsKey(cur)){
                if(k != 0) continue;
                else{
                    if(map.get(cur) == 1) res++;
                    map.put(cur, map.get(cur) + 1);
                    continue;
                }
            }
            if(map.containsKey(cur + k)){
                res ++;
            }
            if(map.containsKey(cur - k)){
                res++;
            }
            map.putIfAbsent(cur, 0);
            map.put(cur, map.get(cur) + 1);
        }
        return res;
    }

    // 第一遍用arraylist做的
    public int findPairs(int[] nums, int k) {
        if(k < 0) return 0;
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        int res = 0;
        for(int i = 0; i < nums.length; i++){
            int cur = nums[i];
            if(map.containsKey(cur)){
                if(k != 0) continue;
                else{
                    if(map.get(cur).size() == 1) res++;
                    map.get(cur).add(i);
                    continue;
                }
            }
            if(map.containsKey(cur + k)){
                res ++;
            }
            if(map.containsKey(cur - k)){
                res++;
            }
            map.putIfAbsent(cur, new ArrayList<>());
            map.get(cur).add(i);
        }
        return res;
    }




    public int findPairs_discussion(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 0)   return 0;

        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (k == 0) {
                //count how many elements in the array that appear more than twice.
                if (entry.getValue() >= 2) {
                    count++;
                }
            } else {
                if (map.containsKey(entry.getKey() + k)) {
                    count++;
                }
            }
        }

        return count;
    }



}
