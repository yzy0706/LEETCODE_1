package Heap;

import java.util.*;

public class TopKFrequentWords {
    //做法: review了以后直接现场建 comparator, 因为他们要alphabetically小的, 而且频率大的, 我们先把pq倒着排, 把频率低的和字母排序大的全部poll()掉
    // Runtime: O(nlog(n)), Space: O(nlog(n));

    public List<String> topKFrequent_reviewed(String[] words, int k) {
        List<String> res = new ArrayList<>(k);
        if(words.length < 1) return res;
        Map<String, Integer> map = new HashMap<>();
        PriorityQueue<String> queue = new PriorityQueue<>(k, (a, b) ->{
            if(map.get(b) == map.get(a)) return b.compareTo(a);
            return map.get(a) - map.get(b);
        });
        for(String s: words){
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        for(String s : map.keySet()){
            queue.offer(s);
            if(queue.size() > k) queue.poll();
        }
        while(!queue.isEmpty()) res.add(queue.poll());
        Collections.reverse(res);
        return res;
    }



    // 看了答案以后添加了map，修改了comparator
    class pair{
        String word;
        int freq;
        public pair(String word, int freq){
            this.word = word;
            this.freq = freq;
        }
    }
    class com implements Comparator<pair> {
        public int compare(pair a, pair b){
            if(a.freq == b.freq) return b.word.compareTo(a.word);
            return a.freq - b.freq;
        }

    }
    public List<String> topKFrequent(String[] words, int k) {
        List<String> res = new ArrayList<>(k);
        if(words.length < 1) return res;
        Map<String, Integer> map = new HashMap<>();
        PriorityQueue<pair> queue = new PriorityQueue<pair>(k, new com());
        // Set<String> repeat = new HashSet<String>();
        for(String s: words){
            map.put(s, map.getOrDefault(s, 0)+1);
        }
        for(String s : map.keySet()){
            queue.offer(new pair(s, map.get(s)));
            if(queue.size() > k) queue.poll();
        }
        while(!queue.isEmpty()) res.add(queue.poll().word);
        Collections.reverse(res);
        return res;

    }



    //第一遍自己写的，用的hashset查重， 然后放到一个queue

    public List<String> topKFrequent_1(String[] words, int k) {
        List<String> res = new ArrayList<>(k);
        if(words.length < 1) return res;
        PriorityQueue<pair> queue = new PriorityQueue<pair>(k, new com());
        Set<String> repeat = new HashSet<String>();
        for(String s: words){
            if(!repeat.contains(s)){
                queue.offer(new pair(s, 1));
                repeat.add(s);
            }
            else{
                for(pair p : queue){
                    if(p.word == s) p.freq++;
                }
            }
        }
        for(int i = 0; i < 2; i++){
            pair p = queue.poll();
            res.add(p.word);
        }
        // Collections.reverse(res);
        return res;

    }
}
