package Heap.queue.greedy;

import java.util.PriorityQueue;

public class SellDiminishingValuedColoredBalls {
    // 做法: 其实这题主要是要用pq去由大到小存储所有颜色的球的个数
    // 1. 浏览inventory里所有的数, 通过pq记录所有非零的颜色的球的个数, int cnt = 1; 代表现在被拿出来的球的颜色种类的数量
    // 2. 我们根据orders > 0这个条件建立一个whileloop:
    //    a. int cur = pq.poll(), 代表之前被拿出来的数量最多的颜色球的数量, int next = pq.peek(), 代表当前数量第二多的颜色的球的数量
    //    b. (cur - next) * cnt <= orders, 代表这一次取出[next+1, cur]数量区间的球不会让orders清零, int num = 首项(next + 1)和末项(cur)的连续数字的和 * cnt, 记得用long表示 num, 且 res = (res + num) % mod;
    //    c. 如果不符合上方那个条件, 证明当前不能直接把cur这些数量的颜色的球直接减少到next数量了, 因为orders会小于0, 所以: next = cur - orders/cnt, 直接把orders清零, 然后照常算[next + 1, cur]区间的和, 然后最后有一步跟小数点有关的: res = (res + 1L * next * (orders % cnt)) % mod;, 我觉得不重要
    // 注意:
    // 1. 重点是用cnt来记录当前已经被拿出来而且不再放回去的那些相同数量的不同颜色的球的种类, 避免一直放回去重复计算
    // 2. 所有长数字都用long来记录, 然后 res = (res + long)% mod;

    // Runtime: O(nlog(n)), Space: O(nlog(n));


    public int maxProfit_discussion(int[] inventory, int orders) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
        for(int n : inventory) if(n != 0) pq.offer(n);
        long res = 0;
        int mod = (int)(Math.pow(10, 9) + 7), cnt = 1;
        while(orders > 0){
            int cur = pq.poll();
            int next = pq.isEmpty() ? 0 : pq.peek();
            if((cur - next) * cnt <= orders){
                long num = 1L * (cur + next + 1) * (cur - next) * cnt / 2;
                res = (res + num) % mod;
                orders -= (cur - next) * cnt;
            }
            else{
                next = cur - orders / cnt;
                long num = 1L * (cur + next + 1) * (cur - next) * cnt / 2;
                res = (res + num) % mod;
                res = (res + 1L * next * (orders % cnt)) % mod;
                break;
            }
            cnt ++;
        }
        return (int)(res);
    }


    public int maxProfit(int[] inventory, int orders) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> inventory[b] - inventory[a]);
        for(int i = 0; i < inventory.length; i++) if(inventory[i] != 0) pq.offer(i);
        long res = 0;
        int mod = (int)(Math.pow(10, 9) + 7);
        while(!pq.isEmpty() && orders > 0){
            int cur = pq.poll();
            int next = pq.isEmpty() ? -1 : pq.peek();
            int curNum = inventory[cur], nextNum = next == -1 ? 0 : inventory[next];
            if(next != -1 && curNum - nextNum + 1 <= orders){
                res += (long)((curNum + nextNum) * (curNum - nextNum + 1) / 2 % mod);
                orders -= curNum - nextNum + 1;
                inventory[cur] -= curNum - nextNum + 1;;
            }
            else{
                nextNum = curNum - orders + 1;
                // System.out.println(curNum + " " + nextNum + " " + orders);
                res = (long)(res + (long)(((curNum + nextNum) * orders) / 2));
                res %= mod;
                // System.out.println(res);
                break;
            }
            if(inventory[cur] > 0) pq.offer(cur);
        }
        return (int)(res);
    }
}
