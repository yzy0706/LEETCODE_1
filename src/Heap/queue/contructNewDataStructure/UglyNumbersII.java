package Heap.queue.contructNewDataStructure;

import java.util.PriorityQueue;
import java.util.Queue;

public class UglyNumbersII {

//做法: 还是照常的用pq承载所有的数,
    // 1. 先把1放在pq里
    // 2. 然后forloop 0 到 n-1, 每次poll()出来一个数top, 所以一共poll()出来了n-1次, 每次都拿这个top去和所有的prime一个一个的相乘得到Long mul, 如果pq里没有mul, 则可以把mul加进去

    // Runtime: O(n^2log(n)), Space: O(n(log(n)))

    public int nthUglyNumber_reviewed_simplePQ(int n){
        if(n == 1) return 1;
        PriorityQueue<Long> pq = new PriorityQueue<>();
        pq.offer((long)1);
        int[] primes = new int[]{2, 3, 5};
        for(int i = 0; i < n - 1; i++){
            Long top = pq.poll();
            for(int p : primes){
                Long mul  = top * (long)p;
                if(mul < Integer.MAX_VALUE && !pq.contains(mul)) pq.offer(mul);
            }
        }
        return pq.poll().intValue();
    }




    //主要就是往一个pq里面加n-1次当前pq.poll() * prime的值， 记得Long和int之间的转换
    public int nthUglyNumber(int n) {
        if(n == 1) return 1;
        Queue<Long> pq = new PriorityQueue<>();
        int[] ftrs = new int[]{2, 3, 5};
        pq.offer((long)1);

        for(int i = 1; i < n; i++){
            Long cur = pq.poll();
            for(int p : ftrs){
                Long next = cur * p;
                if(!pq.contains(next)) pq.offer(next);
            }
        }

        return pq.poll().intValue();
    }

    public int nthUglyNumber_constructNewDataStructure(int n) {
        if (n == 1) return 1;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int[] res = new int[n];
        int[] primes = new int[]{2, 3, 5};
        for (int p : primes) pq.offer(new int[]{p, p, 0});
        res[0] = 1;
        int pos = 1;
        while (pos < n) {
            int[] top = pq.poll();
            int num = top[0], prime = top[1], index = top[2];
            if (res[pos] != num) res[pos++] = num;
            pq.offer(new int[]{res[index + 1] * prime, prime, index + 1});
        }
        return res[n - 1];
    }
}
