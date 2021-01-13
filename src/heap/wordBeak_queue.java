package heap;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class wordBeak_queue {
    //queue解法，dp解法在dp里
    public boolean wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        // int[] visited = new int[len];
        boolean[] visited = new boolean[len];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        while(!queue.isEmpty()){
            int start = queue.poll();
            if(!visited[start]){
                for(int end = start+1 ; end <= len ; end++){
                    if(wordDict.contains(s.substring(start,end))){
                        queue.offer(end);
                        if(end == len) {
                            return true;
                        }
                    }
                }
            }
            visited[start] = true;
        }

        return false;

    }
}
