package binarySearch;

public class Pow {
    //第一个解法是把n/2次方用 half = mypow(x, n/2) 表示出来, 然后再判断n%2 != 0的情况,
    //如果n < 0则用half*half*(1/n), 如果n>0则用half*half*n
    // Runtime:因为这是一个recursion, 我每次都在找并存储x^(n/2), 所以我最多需要O(log(n))次计算, Space也就是O(log(n))个数
    public double myPow_recursive(double x, int n) {
        if(n == 0) return 1.0;
        if(x == 1) return 1.0;
        double half = myPow(x, n/2);
        if(n % 2 != 0) return n < 0? 1/x * half * half : x * half * half;
        return half * half;
    }



    //不涉及到recursion的解法
    public double myPow(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        double ans = 1;
        double current_product = x;
        for (long i = N; i > 0; i /= 2) {
            if ((i % 2) == 1) {
                ans = ans * current_product;
            }
            current_product = current_product * current_product;
        }
        return ans;
    }





}
