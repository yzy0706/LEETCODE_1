package math;

public class divideTwoIntegers {
    public int divide(int dividend, int divisor) {
        // if(dividend < Integer.MIN_VALUE || dividend > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        // if (dividend == Integer.MIN_VALUE && divisor == 1) {
        //     return Integer.MIN_VALUE;
        // }
        int negative = 2;
        if(dividend > 0){
            negative --;
            dividend = - dividend;
        }
        if(divisor > 0){
            negative --;
            divisor = - divisor;
        }

        int res = 0;
        while(dividend - divisor <= 0){
            dividend -= divisor;
            res -- ;
        }
        if(negative != 1){
            res = - res;
        }

        return res;

    }

    //过了edge case的写法
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        if (dividend == Integer.MIN_VALUE && divisor == 1) {
            return Integer.MIN_VALUE;
        }

        int posDividend = dividend < 0 ? dividend : -dividend;
        boolean posDivd = dividend < 0 ? false : true;
        int posDivisor = divisor < 0 ? divisor : -divisor;
        boolean posDivs = divisor < 0 ? false : true;
        int sum = 0;
        int count = 0;

        while (posDividend <= posDivisor) {
            int val = posDivisor;
            int power = 1;

            while (val+val >= posDividend && val >= -1073741824) {
                power=power+power;
                val+=val;
            }

            count += power;
            posDividend -= val;
        }

        if ((posDivd && posDivs) || (!posDivd && !posDivs))
            return count;
        else
            return -(count);
    }
}
