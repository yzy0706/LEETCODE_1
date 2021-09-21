package Greedy.Array;

public class MinSwapsToGroupAll1Together {
    //做法: 用的是greedy + sliding window的方法
    // 1. 找到所有的1的个数 num1
    // 2. 在长度为num1的不同subArray里找到0的个数最小的那一次, 最小的0的个数就是最小swap的次数
    // Runtime: O(n), Space: O(1)

    public int minSwaps(int[] data) {
        int num1 = 0, len = data.length;
        for(int n : data){
            if(n == 1) num1 ++;
        }
        if(num1 == 1 || num1 == len){
            return 0;
        }
        int subNum0 = 0, l = 0, r = 0;
        while(r < num1){
            if(data[r++] == 0) subNum0 ++;
        }
        r --;
        int res = subNum0;
        while(r < len){
            if(l == len || r == len - 1) break;
            if(data[l++] == 0) subNum0 --;
            if(data[++r] == 0) subNum0 ++;
            res = Math.min(res, subNum0);
        }
        return res;
    }
}
