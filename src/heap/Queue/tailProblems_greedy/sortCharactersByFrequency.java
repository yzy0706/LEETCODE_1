package heap.Queue.tailProblems_greedy;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class sortCharactersByFrequency {
    //用map来存每个char的频率， 然后倒过来组成stringbuilder再返还
    public String frequencySort(String s) {
        if(s.length() < 1) return "";
        char[] cl = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        Queue<Character> pq = new PriorityQueue<>((a, b) -> map.get(b) - map.get(a));
        for(char c : cl){
            map.put(c, map.getOrDefault(c, 0)+1);
        }
        for(char c : map.keySet()){
            pq.offer(c);
        }
        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()){
            char cur = pq.poll();
            for(int i = 0; i < map.get(cur); i++){
                sb.append(cur);
            }
        }
        return sb.toString();

    }
}
