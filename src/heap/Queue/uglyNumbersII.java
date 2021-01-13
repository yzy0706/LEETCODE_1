package heap.Queue;

public class uglyNumbersII {
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
}
