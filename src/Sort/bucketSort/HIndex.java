package Sort.bucketSort;

public class HIndex {
    //做法： 这题主要是用一个bucket来记录每一个citation出现的频率， 如果 >= n就直接bucket[n]++就好了，
    //  然后再从bucket[]倒过来叠加大于等于i个citation的总共数量
    //如果叠加的数量 >= i证明找到了至少有i个大于等于i的citation， 直接return， 否则return 0；
    //Runtime:  O(n), space： O(n)
    public int hIndex(int[] citations) {
        int n = citations.length;
        int[] bucket = new int[n+1];
        for(int c : citations){
            if(c >= n) bucket[n]++;
            else bucket[c]++;
        }
        int leastI = 0;
        for(int i = n; i >= 0; i--){
            leastI += bucket[i];
            if(leastI >= i) return i;
        }
        return 0;
    }
}

