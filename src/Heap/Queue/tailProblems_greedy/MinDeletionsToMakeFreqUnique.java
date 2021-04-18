package Heap.Queue.tailProblems_greedy;

import java.util.*;

public class MinDeletionsToMakeFreqUnique {

    // 做法: 这题用的是一个greedy的做法
    // 1. 先把所有字符的频率记录在一个freq = new int[26]上
    // 2. 对于freq里每一个不是0的频率, 我们都记载在一个freqs = new HashSet<>()上, 如果当前的频率大于0且在freqs里有相同的频率, 那就一直消除当前字符到没有重合的频率, 或者当前字符都消除完了为止

    // Runtime: O(n), Space: O(26), n是s的长度

    public int minDeletions_greedy(String s) {
        if(s.length() == 0) return 0;
        int[] freq = new int[26];
        for(Character c : s.toCharArray()) freq[c - 'a'] ++;
        int res = 0;
        Set<Integer> freqs = new HashSet<>();
        for(int i = 0; i < 26; i++){
            while(freq[i] > 0 && !freqs.add(freq[i])){
                freq[i] --;
                res ++;
            }
        }
        return res;
    }



    // 第二次没有用pq而是用map了， 还是有点问题
    public int minDeletions_map(String s) {
        if(s.length() == 1) return 0;
        int[] freq = new int[26];
        HashMap<Integer, List<Character>> sameFreq = new HashMap<>();
        for(Character c : s.toCharArray()) freq[c - 'a'] ++;
        for(int i = 0; i < 26; i++){
            if(freq[i] != 0){
                sameFreq.putIfAbsent(freq[i], new ArrayList<>());
                sameFreq.get(freq[i]).add((char)(65 + i));
            }
        }
        int res = 0;
        for(int f : sameFreq.keySet()){
            List<Character> list = sameFreq.get(f);
            while(list.size() > 1){
                Character cur = list.remove(0);
                int curF = f;
                while(sameFreq.containsKey(curF)){
                    curF --;
                    res ++;
                }
                sameFreq.put(curF, new ArrayList<>());
                sameFreq.get(curF).add(cur);
            }
        }
        return res;
    }







    public int minDeletions_array(String s) {
        if(s.length() == 1) return 0;
        int[] freq = new int[26];
        PriorityQueue<Character> pq = new PriorityQueue<>((a, b) -> (freq[b - 'a'] - freq[a - 'a']));
        for(Character c : s.toCharArray()) freq[c - 'a'] ++;
        for(Character c : s.toCharArray()) if(!pq.contains(c)) pq.offer(c);
        int res = 0;
        Character cur = pq.poll();
        while(!pq.isEmpty()){
            Character next = pq.poll();
            if(freq[cur - 'a'] == freq[next - 'a']){
                freq[next - 'a'] --;
                res ++;
            }
            cur = next;
        }
        return res;
    }





    //  int[]记录频率碰到问题以后用hashmap做的， 但其实问题出在一边记录freq一边把字符offer到pq里
    public int minDeletions(String s) {
        if(s.length() == 1) return 0;
        // int[] freq = new int[26];
        HashMap<Character, Integer> freq = new HashMap<>();
        // PriorityQueue<Character> pq = new PriorityQueue<>((a, b) -> (freq[b - 'a'] - freq[a - 'a']));
        PriorityQueue<Character> pq = new PriorityQueue<>((a, b) -> (freq.get(b) - freq.get(a)));
        for(Character c : s.toCharArray()){
            // if(freq[c - 'a'] == 0) pq.offer(c);
            // freq[c - 'a'] ++;
            if(!freq.containsKey(c)){
                freq.put(c, 0);
                pq.offer(c);
            }
            freq.put(c, freq.get(c)  + 1);
        }
        int res = 0;
        Character cur = pq.poll();
        // System.out.println(cur);
        while(!pq.isEmpty()){
            Character next = pq.poll();
            // if(freq[cur - 'a'] == freq[next - 'a']){
            //     freq[next - 'a'] --;
            //     res ++;
            // }
            if(freq.get(cur) == freq.get(next)){
                freq.put(next, freq.get(next) - 1);
                res ++;
            }
            cur = next;
        }
        return res;
    }
}
