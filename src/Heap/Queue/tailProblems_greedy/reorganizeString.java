package Heap.Queue.tailProblems_greedy;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class reorganizeString {
    //主要是拿一个maxheap的pq去承载各个char， 一个map来记录频率， 然后在whileloop里面如果sb的尾巴等于这个poll出来的char， 那么就再poll一个加进去
    //因为现在再poll出来的肯定跟上一个就不一样了， 反之， 如果sb的尾巴和当前poll出来的不想等或者sb是空的， 就直接把当前的这个char append上去， 不管何种方法，
    //append上去的char都要看减去一个频率以后还大不大于零再放回去， 如果poll了两个出来第一个也要放回去。
    public String reorganizeString(String S) {
        char[] cl = S.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for(char c : cl){
            map.put(c, map.getOrDefault(c, 0)+1);
            if(map.get(c) > (S.length()+1)/2) return "";
        }
        Queue<Character> pq = new PriorityQueue<>((a, b) -> map.get(b) - map.get(a));
        for(char c : map.keySet()){
            pq.offer(c);
        }
        StringBuilder sb = new StringBuilder();
        char wait = ' ';
        while(!pq.isEmpty()){
            char c = pq.poll();
            if(sb.length() == 0 || sb.charAt(sb.length()-1) != c){
                sb.append(c);
                if(map.get(c) -1 > 0){
                    map.put(c, map.get(c)-1);
                    pq.offer(c);
                }
            }
            else{
                char next = pq.poll();
                sb.append(next);
                if(map.get(next) - 1 > 0){
                    map.put(next, map.get(next)-1);
                    pq.offer(next);
                }
                pq.offer(c);
            }
        }
        return sb.toString();
    }
}
