package Heap.queue;

import java.util.HashMap;
import java.util.PriorityQueue;

public class RankTeamByVotes {
    // 做法: 我的第一种做法是拿HashMap<Character, char[]> + pq
    // 1. 用HashMap来记录每一个Character在各个位置上出现过的频率
    // 2. 在pq里比较各个Character对应的char[], 如果一样的话比较字母排列的顺序

    // Runtime:  O(nlog(n)), Space: O(nlog(n));

    public String rankTeams(String[] votes) {
        HashMap<Character, char[]> map = new HashMap<>();
        int len = votes[0].length();
        for(String vote : votes){
            for(int i = 0; i < len; i++){
                Character c = vote.charAt(i);
                map.putIfAbsent(c, new char[len]);
                map.get(c)[i] += '1';
            }
        }
        PriorityQueue<Character> pq = new PriorityQueue<>((a, b) -> {
            String sA = String.valueOf(map.get(a)), sB = String.valueOf(map.get(b));
            if(sB.compareTo(sA) == 0){
                return a.compareTo(b);
            }
            return sB.compareTo(sA);
        });
        for(Character c : map.keySet()){
            pq.offer(c);
        }
        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()){
            sb.append(pq.poll());
        }
        return sb.toString();

    }
}
