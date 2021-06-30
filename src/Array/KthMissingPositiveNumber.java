package Array;

import java.util.HashSet;

public class KthMissingPositiveNumber {
    // 做法:第二种是每次都去比较arr上的每个数和上一个数之间miss了几个数(cur - prev - 1)
    // 1. 如果i == 0上一个数就是0
    // 2. 如果miss了的数 >= k的话就return prev + k;
    // 3. 如果跑完arr以后还没return结果就是arr最后一个数 + k

    // Runtime: O(n), n是arr的大小, Space: O(1)

    public int findKthPositive_2(int[] arr, int k) {
        for(int i = 0; i < arr.length; i ++){
            int cur = arr[i], prev = i == 0 ? 0 : arr[i - 1];
            if(cur == prev + 1) continue;
            int missing = cur - prev - 1;
            // System.out.println(prev + " " + k);
            if(k <= missing) return prev + k;
            k -= missing;
        }
        return arr[arr.length - 1] + k;
    }



    // 做法: 第一种是brute force, 感觉非常慢
    // Runtime: O(k) (k是第k个missing的数), Space: O(n)

    public int findKthPositive_brute(int[] arr, int k) {
        int num = 1;
        HashSet<Integer> set = new HashSet<>();
        for(int i : arr){
            set.add(i);
        }
        while(k > 0){
            if(!set.contains(num ++)) k --;
        }
        return num - 1;
    }
}
