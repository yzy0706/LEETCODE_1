package binarySearch;

public class sqrt {
    //二分法解法
    public int sqrt(int x){
        if(x < 2) return x;
        int left = 2;
        int right = x/2;
        int temp;
        while (left<= right){
            temp = left + (right-left)/2;
            long num = (long)temp*temp;
            if(num > x){
                right = temp-1;

            }
            else if(num < x){
                left  = temp+1;
            }
            else return temp;
        }
        return right;
    }


    //这个简洁一点的二分法解法主要是来判断mid *mid或者 (mid+1) * (mid+1) 说是不是大于x
    public int mySqrt(int x) {
        if (x == 0)
            return 0;
        int left = 1, right = x;
        while (true) {
            int mid = left + (right - left)/2;
            if (mid > x/mid) {
                right = mid - 1;
            } else {
                if (mid + 1 > x/(mid + 1))
                    return mid;
                left = mid + 1;
            }
        }





        //牛顿解法
    double err = 0.01;
    public int sqrt(int x){
        double n = (double)x;
        double tmp = x/2;
        while(Math.abs(tmp * tmp - x) >= err){
            tmp = (tmp + n/tmp)/2;

        }
        return (int)tmp;

    }
}

