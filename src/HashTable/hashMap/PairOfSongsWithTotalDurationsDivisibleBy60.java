package HashTable.hashMap;

import java.util.HashMap;

public class PairOfSongsWithTotalDurationsDivisibleBy60 {
    //解法: 数学解法， 用int[]当map用
    // 如果 a + b整除 60, 那么 a % 60 = 60 - b % 60;
    //我们在这道题里面还得考虑两个都是60的情况, 所以我们用一个int[] map来储存每一个time[i] % 60和对应time[i]的个数
    // res += map[(60 - cur % 60) % 60], 在括号外面再 %60 以避免 cur = 60的情况 (一对都是60， 60)

    //Runtime: O(n), space: O(n), 假设每个数%60都不一样, int[]就要存所有的数

    public int numPairsDivisibleBy60_math(int[] time) {
        int[] map = new int[60];
        int res = 0;
        for(int i = 0; i < time.length; i++){
            int cur = time[i];
            res += map[(60 - cur % 60) % 60]; //60 - (60 - cur % 60)
            map[cur % 60] ++;
        }
        return res;
    }



    //做法： 用map记录每一个数出现的频率
    //Runtime: O(16n), 16是 * 60的倍数， space: O(n)
    public int numPairsDivisibleBy60(int[] time) {
        int res = 0;
        if(time.length <= 1) return 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < time.length; i++){
            int cur = time[i];
            for(int n = 1; n < 17; n++){
                if(map.containsKey(60 * n - cur)){
                    res += map.get(60 * n - cur);
                }
            }
            map.put(time[i], map.getOrDefault(time[i], 0) + 1);
        }
        return res;
    }
}
