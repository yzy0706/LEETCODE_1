package Math;

public class ReverseInteger {
    // 做法: 记得运算比较大的数的时候用long
    // Runtime: O(n), Space: O(1), n是digit的数量

    public int reverse(int x) {
        long res = 0;
        boolean negative = false;
        if(x < 0){
            negative = true;
            x = -x;
        }
        while(x > 0){
            res *= 10;
            int digit = x % 10;
            res += digit;
            if(res > Integer.MAX_VALUE) return 0;
            x /= 10;
        }
        return negative ? (int)- res : (int)res;

    }

    public int reverse_2(int x) {
        int temp  = 0;
        while(Math.abs(x)>=10) {
            temp = (temp+x%10)*10;
            x = x/10;
        }

        if(Math.abs(temp+x)>=Integer.MAX_VALUE) return 0;
        else return (int) temp+x;
    }



    //第二遍解法
    public int reverse_3(int x) {
        if(x == 0 || x < -Math.pow(2,31) || x > Math.pow(2,31) -1 ) return 0;
        boolean n = false;
        long res = 0;  //这里必须是long，而不是int

        if(x < 0 ) {
            n=true;
            x = -x;
        }

        while( x >= 1){
            int digit = x % 10;
            res = res *10 + digit;
            x /= 10;
        }

        if(Math.abs(res) > Integer.MAX_VALUE) return 0;
        if(n) return (int)-res;
        return (int)res;
    }


    //discussion解法
    public int reverse_4(int x) {
        long num = 0;
        boolean k = false;
        if(x < 0){
            k = true;
            x = -1 * x;
        }
        while(x > 0){
            int mod = x%10;
            num = num *10 + mod;
            x = x/10;
        }

        if(k)
            num = -1*num;
        if(num > Integer.MAX_VALUE || num < Integer.MIN_VALUE) return 0;
        return (int)num;
    }
}
