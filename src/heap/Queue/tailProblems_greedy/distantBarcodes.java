package heap.Queue.tailProblems_greedy;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class distantBarcodes {
    //跟isPossible其实是一样， 拿map来记录频率， 拿pq来比较最多的字母跟当前的尾巴是不是一样，能不能插进去
    public int[] rearrangeBarcodes(int[] barcodes) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i : barcodes){
            map.put(i, map.getOrDefault(i, 0)+1);
        }
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> map.get(b) - map.get(a));
        for(int i : map.keySet()){
            pq.offer(i);
        }
        int[] res = new int[barcodes.length];
        int idex = 0;
        while(!pq.isEmpty()){
            int cur = pq.poll();
            if(idex == 0 || res[idex-1] != cur ){
                res[idex] = cur;
                if(map.get(cur) - 1 > 0){
                    map.put(cur, map.get(cur) - 1);
                    pq.offer(cur);
                }
            }
            else{
                int next = pq.poll();
                res[idex] = next;
                if(map.get(next) - 1 > 0){
                    map.put(next, map.get(next) - 1);
                    pq.offer(next);
                }
                pq.offer(cur);
            }
            idex++;
        }
        return res;


    }
}
