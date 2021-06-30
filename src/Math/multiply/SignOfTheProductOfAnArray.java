package Math.multiply;

public class SignOfTheProductOfAnArray {
    // 做法: 直接记录有多少个负数, 如果有偶数个乘积肯定大于0, 有奇数个就小于0
    // Runtime: O(n), Space: O(1);

    public int arraySign(int[] nums) {
        int negative = 0;
        for(int n : nums){
            if(n == 0) return 0;
            if(n < 0) negative ++;
        }
        return negative % 2 == 0 ? 1 : -1;
    }
}
