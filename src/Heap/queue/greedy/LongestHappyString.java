package Heap.queue.greedy;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class LongestHappyString {
    //做法: 用pq和greedy做的, 尽可能的多放连续的两个字母, 除非当前是第二个抽出来的字符且它的频率比上一个抽出来的低
    // 1. 建立一个pq, 由大到小排列各个字符的频率, 把三个字符'a', 'b', 'c'都放进去
    // 2. 建立一个whileloop, 当pq.size() > 1的时候, 每次都抽出来两个字符
    //      a. 抽取第一个字符cur1, 如果freq[cur1 - 'a'] >= 2就放进去两个cur1, 否则就放进去一个, 在freq里改变cur1的频率
    //      b. 抽取第二个字符cur2, 如果freq[cur2 - 'a'] >= 2 且 freq[cur2 - 'a'] >= freq[cur1 - 'a']; 就放进去两个cur2, 否则放进去一个cur2, 且记录cur2的频率变化
    //      c. 如果cur1和cur2有频率变成0了就不用回到pq里了
    // 3. 最后检查一下pq是不是空了, 不是空的话看看最后一个在pq里剩下的字符能加两个还是一个进去

    // Runtime: O(nlog(n)) , Space: O(3log(3))

    public String longestDiverseString_pq_greedy(int a, int b, int c) {
        StringBuilder res = new StringBuilder();
        int[] freq = new int[3];
        freq[0] = a;
        freq[1] = b;
        freq[2] = c;
        PriorityQueue<Character> pq = new PriorityQueue<>((i, j) -> (freq[j - 'a'] - freq[i - 'a']));
        if(a > 0) pq.offer('a');
        if(b > 0) pq.offer('b');
        if(c > 0) pq.offer('c');
        while(pq.size() > 1){
            Character cur1 = pq.poll();
            if(freq[cur1 - 'a'] >= 2){
                res.append(cur1);
                res.append(cur1);
                freq[cur1 - 'a'] -= 2;
            }
            else{
                res.append(cur1);
                freq[cur1 - 'a'] --;
            }
            Character cur2 = pq.poll();
            if(freq[cur2 - 'a'] >= 2 && freq[cur2 - 'a'] >= freq[cur1 - 'a']){
                res.append(cur2);
                res.append(cur2);
                freq[cur2 - 'a'] -= 2;
            }
            else{
                res.append(cur2);
                freq[cur2 - 'a'] --;
            }
            if(freq[cur1 - 'a'] != 0) pq.offer(cur1);
            if(freq[cur2 - 'a'] != 0) pq.offer(cur2);
        }
        if(!pq.isEmpty()){
            Character last = pq.poll();
            if(res.length() < 1 || last != res.charAt(res.length() - 1)){
                if(freq[last - 'a'] >= 2){
                    res.append(last);
                    res.append(last);
                }
                else{
                    res.append(last);
                }
            }
        }
        return res.toString();
    }

    //第一遍自己做的
    public String longestDiverseString_1(int a, int b, int c) {
        StringBuilder res = new StringBuilder();
        int[] freq = new int[3];
        freq[0] = a;
        freq[1] = b;
        freq[2] = c;
        PriorityQueue<Character> pq = new PriorityQueue<>((i, j) -> (freq[j - 'a'] - freq[i - 'a']));
        if(a > 0) pq.offer('a');
        if(b > 0) pq.offer('b');
        if(c > 0) pq.offer('c');
        while(!pq.isEmpty()){
            int size = pq.size();
            List<Character> temp = new ArrayList<>();
            Character cur = pq.poll();
            if(res.length() > 0 && res.charAt(res.length() - 1) == cur){
                temp.add(cur);
                if(!pq.isEmpty()) cur = pq.poll();
                else break;
            }
            if(freq[cur - 'a'] >= 2){
                res.append(cur);
                res.append(cur);
                freq[cur - 'a'] -= 2;
            }
            else{
                res.append(cur);
                freq[cur - 'a'] --;
            }
            if(freq[cur - 'a'] != 0) temp.add(cur);
            pq.addAll(temp);
        }
        return res.toString();
    }
}
