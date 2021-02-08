package Array.rearrange;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class TaskScheduler {
    // 做法: 这属于rearrange一些元素的题目, 核心是看怎么分配各个元素, 而greedy去随时arrange数量最多的字符的做法太耗时间
    // 我们中心是求idle一共有多少个, 加上tasks的长度就是所需时间

    // 1. 用int[]记录每一个字母的数量, forloop一遍, max记录频率最高值, maxCount记录有几个字符频率并列最高:
    //   a. 如果当前频率等于最高频率, maxCount ++;
    //   b. 如果当前频率大于最高频率, maxCount = 1; 重置
    // 2. 我们需要的结果肯定是 A ?? A ?? A的形式,
    // ??就是每个part, 会被频率最高的字符分割成 max - 1个part,
    // part的长度就是 cooldown的时间 - 剩余频率并列最高的字符的个数 = n - (maxCount - 1);
    // 所以 个数 * part长度 就是当前可以装其他字母或者idle的位置, 其他字母的个数是 tasks.length - maxCount * max
    // 所以 装其他字母或者idle的位置 - 其他字母的个数如果>0, 就是装idle的个数, 否则idle就只有0个

    // Runtime: O(n), space: O(26);

    public int leastInterval_map(char[] tasks, int n) {
        int[] freq = new int[26]; //int[]当map用记录每个字母的frequency
        int max = 0, maxCount = 0; //分别记录最高频率的大小和最高频率出现的次数

        for(char c : tasks){
            freq[c - 'A'] ++;
            if(freq[c - 'A'] == max){
                maxCount ++;
            }
            else if(freq[c - 'A'] > max){
                max = freq[c - 'A'];
                maxCount = 1;
            }
        }

        int numParts = max - 1;
        int availablePos = numParts * (n - (maxCount - 1));
        int restTasks = tasks.length - (max * maxCount);
        int numIdles = Math.max(0, availablePos - restTasks);

        return tasks.length + numIdles;
    }




    //第一遍用map和priorityQueue写的， 类似于贪心算法， Runtime太高了
    public int leastInterval(char[] tasks, int n) {
        HashMap<Character, Integer> freq = new HashMap<>();
        PriorityQueue<Character> pq = new PriorityQueue<>((a, b) -> (freq.get(b) - freq.get(a)));
        int res = 0;
        int cooldown = n;
        Queue<Character> wait = new LinkedList<>();
        for(Character c : tasks){
            if(!freq.containsKey(c)){
                freq.put(c, 0);
                pq.offer(c);
            }
            freq.put(c, freq.get(c) + 1);
        }
        while(!pq.isEmpty() || !wait.isEmpty()){
            if(wait.size() == n + 1){
                Character cBack = wait.poll();
                if(cBack != '!' && freq.get(cBack) != 0){
                    pq.offer(cBack);
                }
            }
            if(pq.isEmpty()){
                wait.offer('!');
                res ++;
                continue;
            }

            Character c = pq.poll();
            freq.put(c, freq.get(c) - 1);
            wait.offer(c);
            res ++;
        }

        return res;
    }
}
