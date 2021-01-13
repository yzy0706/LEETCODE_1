package dynamicProgramming;

import java.util.HashSet;
import java.util.Set;

public class wordBreak_dp {
    public boolean wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        // int[] visited = new int[len+1];
        boolean[] visited = new boolean[len+1];
        Set<String> wordSet = new HashSet<>(wordDict);
        visited[0] = true;

        for(int i= 1; i <= len ; i++){
            for(int j = 0 ; j < i ; j++){
                if(visited[j] && wordSet.contains(s.substring(j,i))){
                    visited[i] = true;
                    break;
                }
            }
        }
        return visited[len];
    }
}
