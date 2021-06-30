package Array;

import java.util.TreeMap;

public class MinimumIncrementToMakeArrayUnique {
    //做法: 第二种做法是不用sort, 直接用一个int[]来记录从0到max - 1的move的总和, 最后剩下一个max也要一直move到1
    //Runtime: O(n)(从 1...n), Space: O(n)(每次都删掉了一个key)

    public int minIncrementForUnique_2(int[] nums) {
        int[] freq = new int[40002]; //constraints of the length of nums
        int max = 0;
        for(int n : nums){
            freq[n] ++;
            max = Math.max(max, n);
        }
        int res = 0;
        for(int i = 0; i < max; i ++){
            int num = freq[i];
            if(num > 1){
                int move = num - 1;
                res += move;
                freq[i + 1] += move;
            }
        }
        int last = freq[max] - 1;
        res += (last + 1) * last / 2; //从last一直往一步步-1到1
        return res;
    }





    //做法: 用的TreeMap来排列当前每一种数字的数量,
    //Runtime: O(n^2)(从 1...n), Space: O(n)(每次都删掉了一个key)

    public int minIncrementForUnique(int[] nums) {
        TreeMap<Integer, Integer> freq = new TreeMap<>();
        for(int n : nums){
            if(freq.containsKey(n)) freq.replace(n, freq.get(n) + 1);
            else freq.put(n, 1);
        }
        int res = 0;
        while(!freq.isEmpty()){
            int key = freq.firstKey(), val = freq.get(key);
            int move = 0;
            if(val > 1){
                move = val - 1;
                if(freq.containsKey(key + 1)){
                    freq.replace(key + 1, freq.get(key + 1) + move);
                }
                else{
                    freq.put(key + 1, move);
                }
                res += move;
            }
            freq.remove(key);
        }
        return res;
    }
}
