package TwoPointer;

import java.util.LinkedList;

public class SumOfSubarrayMins {


    //第二遍用linkedlist做类似于sliding window的做法也没过

    public int sumSubarrayMins_linkedlist(int[] arr) {
        int len = arr.length;
        double res = 0;
        double mod = 1e9 + 7;
        LinkedList<Integer> l = new LinkedList<>();
        for(int i = 0; i < len; i++){
            int cur = arr[i];
            while(!l.isEmpty() && l.peekFirst() >= cur){
                l.pollFirst();
            }
            l.addLast(cur);
            res = (res + (double)(l.peekFirst() * (i + 1))) % mod;
        }
        return (int)res;
    }



    // 第一遍直接brute force， TLE过不了
    public int sumSubarrayMins_double_forloop(int[] arr) {
        int len = arr.length;
        double res = 0, mod = Math.pow(10, 9) + 7;
        for(int i = 0; i < len; i ++){
            double min = arr[i];
            for(int j = i; j < len; j ++){
                min = Math.min(min, arr[j]);
                res =(res + (double)min) % mod;
            }
        }
        return (int)res;
    }
}
