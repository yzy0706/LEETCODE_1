package Greedy.Array;

import java.util.Arrays;

public class KokoEatingBananas {
    // 做法: 最后还是直接用的l = 1, r = max的二分法
    // 1. 找到piles里面的最大值 max, l = 1, r = max(注意: l不能等于0, 否则calTime的helper没法计算
    // 2. 以l <= r为界限进行binary search, 可以time == h的时候mid一直减, 但那样比较慢

    // Runtime: O(nlog(m)), Space: O(1); m是最大的数, n是piles的长度

    public int minEatingSpeed_binarySearch(int[] piles, int h) {
        int len = piles.length, max = 0;
        for(int pile : piles){
            max = Math.max(max, pile);
        }
        int l = 1, r = max;
        while(l <= r){
            int mid = (l + r) / 2, time = calTime(piles, mid);
            if(time == h){
                while(mid - 1 > 0 && calTime(piles, mid - 1) == h) mid --;
                return mid;
            }
            if(time < h){
                r = mid - 1;
            }
            else{
                l = mid + 1;
            }
        }
        return l;
    }

    public int calTime_4(int[] piles, int k){
        int time = 0;
        for(int pile : piles){
            if(pile % k != 0) time += pile / k + 1;
            else time += pile / k;
        }
        return time;
    }



    //第三遍先sort再根据index做binary search，还是不行
    public int minEatingSpeed_sort(int[] piles, int h) {
        Arrays.sort(piles);
        int len = piles.length;
        if(len == 1) return piles[0] % h == 0 ? piles[0] / h : piles[0] / h + 1;
        if(len == h) return piles[len - 1];
        int l = 0, r = len - 1;
        while(l <= r){
            int mid = (l + r) / 2, tmpK = piles[mid], time = calTime(piles, tmpK);
            if(time == h){
                while(calTime(piles, tmpK - 1) <= h) tmpK --;
                return tmpK;
            }
            if(time > h){
                while(tmpK + 1 < piles[mid + 1] && calTime(piles, tmpK) > h) tmpK ++;
                // System.out.println(tmpK);
                if(calTime(piles, tmpK) == h) return tmpK;
                else l = mid + 1;
            }
            else{
                r = mid - 1;
            }
        }
        return piles[l];

    }

    public int calTime(int[] piles, int k){
        int time = 0;
        for(int pile : piles){
            if(pile % k != 0) time += pile / k + 1;
            else time += pile / k;
        }
        return time;
    }




    //第二遍没有sort， 直接拿最大的数和最小的数做的binary search, 但是不成功
    public int minEatingSpeed_num_bs(int[] piles, int h) {
        int len = piles.length, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for(int pile : piles){
            min = Math.min(min, pile);
            max = Math.max(max, pile);
        }
        if(len == 1) return piles[0] % h == 0 ? piles[0] / h : piles[0] / h + 1;                     if(len == h) return max;
        int l = min, r = max;
        while(l < r){
            int mid = (l + r) / 2, time = 0;
            for(int pile : piles){
                if(pile % mid != 0) time += pile / mid + 1;
                else time += pile / mid;
            }
            if(time == h) return mid;
            if(time > h) l = mid;
            else r = mid - 1;
        }
        return l;

    }

    // public int calTime(int[] piles, int k){
    //     int time = 0;
    //     for(int pile : piles){
    //             if(pile % mid != 0) time += pile / mid + 1;
    //             else time += pile / mid;
    //     }
    //     return time;
    // }



    // 第一遍想用 sum / h的greedy方法来做， 答案会出现负数， 过不了
    public int minEatingSpeed(int[] piles, int h) {
        int sum = 0;
        for(int pile : piles){
            sum += pile;
        }
        int k = sum / h, time = 0;
        for(int pile : piles){
            time += pile % k == 0 ? pile / k : pile / k + 1;
        }
        while(time > h){
            k ++;
            int curTime = 0;
            for(int pile : piles){
                curTime += pile % k == 0 ? pile / k : pile / k + 1;
            }
            time = curTime;
        }
        return k;
    }
}
