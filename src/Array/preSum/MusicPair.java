package Array.preSum;

import java.util.HashMap;

public class MusicPair {

    //解法: 如果 a + b整除 60, 那么 a % 60 = 60 - b % 60;
    //我们在这道题里面还得考虑两个都是60的情况, 所以我们用一个int[] map来储存每一个time[i] % 60和对应time[i]的个数
    // res += map[(60 - cur % 60) % 60], 在括号外面再 %60 以避免 cur = 60的情况(一对都是60)

    //Runtime: O(n), space: O(n), 假设每个数%60都不一样, int[]就要存所有的数

    public int numPairsDivisibleBy60_reviewed(int[] time) {
        int len = time.length;
        if(len == 1) return 0;
        int[] map = new int[60];
        int res = 0;
        for(int i = 0; i < len; i++){
            int cur = time[i], coorRemainder = (60 - cur % 60) % 60;
            //in case of cur == 0
            res += map[coorRemainder];
            map[cur % 60] ++;
        }
        return res;
    }




    //第一次review做的， 这个用了O(n^2)
    public int numPairsDivisibleBy60(int[] time) {
        int len = time.length;
        if(len == 1) return 0;
        HashMap<Integer, Integer> freq = new HashMap<>();
        int res = 0;
        for(int i = 0; i < len; i++){
            int cur = time[i];
            for(int coor : freq.keySet()){
                if((cur + coor)% 60 == 0) res += freq.get(coor);
            }
            freq.putIfAbsent(cur, 0);
            freq.replace(cur, freq.get(cur) + 1);
        }
        return res;
    }
}
