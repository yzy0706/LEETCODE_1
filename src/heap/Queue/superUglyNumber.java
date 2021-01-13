package heap.Queue;

public class superUglyNumber {
    // 用pq去承载当前从2开始到n-1（for i = 0; i < n-2)个丑数与各个primes的乘积（第一个数1的乘积就直接offer prime就好），并保证里面一直是不同的丑数，
    // 这样从第一个到倒数第二个数都会与各个prime相乘，i == 1的时候已经是第二个了, 那么n-1个数在forloop里面就是第n-2次乘prime, 接下来那次poll()肯定是第n个
    public int nthSuperUglyNumber(int n, int[] primes) {
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



    //比较难的方法
    public int nthSuperUglyNumber(int n, int[] primes) {
        PriorityQueue<int[]> queue=new PriorityQueue<>((a,b)->(a[0]-b[0]));
        for (int i=0;i<primes.length;i++)
            queue.offer(new int[]{primes[i], primes[i], 0});

        int[] nums=new int[n+1];
        nums[0]=1;

        int i=1;
        while (i<n){
            int[] entry=queue.poll();
            int num=entry[0], prime=entry[1], index=entry[2];
            // remove duplicate
            if (num!=nums[i-1]){
                nums[i]=num;
                i++;
            }
            queue.offer(new int[]{prime*nums[index+1], prime, index+1});
        }
        return nums[n-1];
    }
}
