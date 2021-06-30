package Array.preSum;

import java.util.HashMap;

public class ContinuousSubarrayDivisbleByK {
    
    // 做法: 第三遍直接用array， 逻辑跟第二遍还是一样， 省了一定时间
    // 注意： 记得put(0, 1)
    // Runtime: O(n), Space: O(n)

    public int subarraysDivByK_array(int[] nums, int k) {
        int r = 0, len = nums.length, res = 0;
        int[] rms = new int[k];
        rms[0] = 1;
        for(int n : nums){
            r = (r + n) % k;
            if(r < 0) r += k; // 举例 : 如果当前remainder sum == -1, sum += k = 4;
            res += rms[r];
            rms[r] ++;
        }
        return res;
    }






    // 第二遍不是用sum而是用的每个sum的remainder， 碰到remainder的题都可以这么做
    // 做法: 还是经典的HashMap + prefix sum做法, 但是有一点比较重要的是如果sum[0, i] % k == sum[0, j] % k -> sum[i, j] % k == 0;
    // 注意： 记得put(0, 1)
    // Runtime: O(n), Space: O(n)

    public int subarraysDivByK_remainder(int[] nums, int k) {
        int r = 0, len = nums.length, res = 0;
        HashMap<Integer, Integer> rms = new HashMap<>();
        rms.put(0, 1);
        for(int n : nums){
            r = (r + n) % k;
            if(r < 0) r += k; // 举例 : 如果当前remainder sum == -1, sum += k = 4;
            if(rms.containsKey(r)) res += rms.get(r);
            rms.put(r, rms.getOrDefault(r, 0) + 1);
        }
        return res;
    }




    // 做法 第一遍经典的music pair的方法来做的, 如果数据太大就TLE了
    // Runtime: O(n^2), Space: O(n)

    public int subarraysDivByK(int[] nums, int k) {
        int sum = 0, len = nums.length, res = 0;
        HashMap<Integer, Integer> sums = new HashMap<>();
        for(int i = 0; i < len; i ++){
            sum += nums[i];
            if(sum == 0 || sum % k == 0) res ++;
            for(int prev : sums.keySet()){
                if(sum - prev == 0 || (sum - prev) % k == 0) res += sums.get(prev);
            }
            sums.put(sum, sums.getOrDefault(sum, 0) + 1);
        }
        return res;
    }
}
