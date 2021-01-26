package BinarySearch.greedy_area_etc;

public class EqualCircle {
    //做法： 这个题主要是结合greedy和binary search，
    //1. 我们把当前给我的半径的sorted数列直接转换成对应的面积的数列
    //2. 把0和最大的面积当作始终点进行binary search
    //3. 在得到每一个切除面积mid以后，我们把面积数列里的面积从右到左每一个都除以这个mid，cnt += area[i] / mid, 假如cnt >= segments,
    //证明当前的面积mid符合条件， 我们就继续进行binary search， 直到 end - start <= 1e-4了， 证明误差够了不用再减小了， 直接return mid
    //Runtime: 最多就是O(n^2) ， 找到了当前这个mid而且当切这个mid要被area里面所有的值除一遍并相加才能满足segments的数量， space： O(n)
    public static double equalCircle(int n, int[] radius, int segments){
        double[] area = new double[n];
        final double pi = 3.14159265359;
        for(int i = 0; i < n; i++){
            area[i] = pi * radius[i] * radius[i];
        }
        double start = 0, end = area[n-1];
        while(start <= end){
            double mid = (start + end)/2;
            int cnt = 0;
            for(int i = n - 1; i >= 0; i--){
                cnt += Math.floor(area[i]/ mid);
                if(cnt >= segments) break;
            }
            if(cnt >= segments) start = mid;
            else end = mid;
            if(end - start < 1e-4) return mid;
        }
        return -1;
    }
}
