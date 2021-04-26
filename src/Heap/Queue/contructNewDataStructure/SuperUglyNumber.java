package Heap.Queue.contructNewDataStructure;

import java.util.PriorityQueue;
import java.util.Queue;

public class SuperUglyNumber {

    //做法: 比较简单的做法就是用一个pq记录当前用最小的数乘以各个prime以后的所有的数.
    // 1. pq里面放进1, 这是我们第一次要poll出来的数, 也是肯定的第一个super ugly number
    // 2. 然后forloop从0到n-2, 一共poll了n-1次, 从第一次pq.poll()出1开始, 每次都用pq最小的数cur乘以所有的prime并且offer到pq里, 然后把所有pq里跟当前cur一样的数都poll掉
    // Runtime: O(n), Space: O(n);

    public int nthSuperUglyNumber_forloop(int n, int[] primes) {
        if(n == 1) return 1;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(1);
        for(int i = 0; i < n-1; i++){
            int cur = pq.poll();
            for(int p : primes){
                long mul = (long)cur * (long)p;
                if(mul < Integer.MAX_VALUE) pq.offer((int)mul);
            }
            while(cur == pq.peek()){
                pq.poll();
            }
        }
        return pq.peek();
    }



    //做法: 第二种做法是新建了一个int[]{num, prime, index}的结构
    // 来储存安排到{res里面的每一个数, 他对应的prime, 他上一位乘以当前的prime过来的数在res里的位置}
    // Runtime: O(n), Space: O(n);

    public int nthSuperUglyNumber_newDataStructure(int n, int[] primes) {
        if(n == 1) return 1;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int[] res = new int[n];
        res[0] = 1;
        for(int p : primes) pq.offer(new int[]{p, p, 0});
        int pos = 1;
        while(pos < n){
            int[] cur = pq.poll();
            int num = cur[0], prime = cur[1], index = cur[2];
            if(res[pos-1] != num) res[pos++] = num;
            pq.offer(new int[]{prime * res[index + 1], prime, index + 1});
        }
        return res[n-1];
    }


    // 用pq去承载当前从2开始到n-1（for i = 0; i < n-2)个丑数与各个primes的乘积（第一个数1的乘积就直接offer prime就好），并保证里面一直是不同的丑数，
    // 这样从第一个到倒数第二个数都会与各个prime相乘，i == 1的时候已经是第二个了, 那么n-1个数在forloop里面就是第n-2次乘prime, 接下来那次poll()肯定是第n个

    public int nthSuperUglyNumber_cal(int n, int[] primes) {
        if(n == 1) return 1;
        Queue<Integer> pq = new PriorityQueue<>();
        for(int p : primes){
            pq.offer((p));
        }

        // i == 1的时候已经是第二个了, 那么n-1个数在forloop里面就是第n-2次乘prime, 接下来那次poll()肯定是第n个
        for(int i = 1; i < n - 1; ++i){
            int cur = pq.poll();
            for(int p : primes){
                Long pMult = (long)cur * (long)p;
                if(pMult < Integer.MAX_VALUE) pq.offer(pMult.intValue());
            }
            while(pq.peek() ==  cur) pq.poll();
        }
        return pq.poll();
    }



    //建立新的int[]
    public int nthSuperUglyNumber(int n, int[] primes) {
        PriorityQueue<int[]> queue=new PriorityQueue<>((a,b)->(a[0]-b[0]));
        for (int i=0;i<primes.length;i++)
            queue.offer(new int[]{primes[i], primes[i], 0});

        int[] nums=new int[n+1];
        nums[0] = 1;

        int i=1;
        while (i<n){
            int[] entry=queue.poll();
            int num=entry[0], prime=entry[1], index=entry[2];
            // remove duplicate
            if (num != nums[i-1]){
                nums[i]=num;
                i++;
            }
            queue.offer(new int[]{prime*nums[index+1], prime, index+1});
        }
        return nums[n-1];
    }


    // Review写的方法， 不太对
    public int nthSuperUglyNumber_reviewed(int n, int[] primes) {
        if(n == 1) return 1;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(1);
        int pos = 0, origin = n;
        int[] res = new int[n];
        while(n > 0){
            int cur = pq.poll();
            res[pos++] = cur;
            n --;
            // while(!pq.isEmpty() && pq.peek() < cur * primes[0] && n > 0){
            //     res[pos ++] = pq.poll();
            //     n--;
            // }
            for(int p : primes){
                if(!pq.contains(cur*p)) pq.offer(cur * p);
            }
        }
        return res[origin - 1];
    }
}
