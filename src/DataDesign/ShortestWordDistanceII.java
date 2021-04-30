package DataDesign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShortestWordDistanceII {
    //做法: 第三遍还是拿hashmap, 只是key是string, value是这个string出现过的所有位置, 然后在shortest这个方程里再一一比对
    //Runtime: O(n) Space: O(n);

    HashMap<String, List<Integer>> indexes;

    public void WordDistance_indexes(String[] wordsDict) {
        indexes = new HashMap<>();
        for(int i = 0; i < wordsDict.length; i++){
            String word = wordsDict[i];
            indexes.putIfAbsent(word, new ArrayList<>());
            indexes.get(word).add(i);
        }
    }


    public int shortest_indexes(String word1, String word2) {
        List<Integer> l1 = indexes.get(word1), l2 = indexes.get(word2);
        int res = Integer.MAX_VALUE, i = 0, j = 0;
        while(i < l1.size() && j < l2.size()){
            int index1 = l1.get(i), index2 = l2.get(j);
            if(index1 > index2){
                res = Math.min(res, index1 - index2);
                j++; // 每个list上的index肯定是从小到大的
            }
            else{
                res = Math.min(res, index2 - index1);
                i++;
            }
        }
        return res;
    }



    //做法: 第一遍相当于建立了一个graph, 但好像RTE了
    //Runtime: O(n^2) Space: O(n^2);

    HashMap<String, Integer> graph;
    public void WordDistance_2(String[] wordsDict) {
        graph = new HashMap<>();
        dfs(wordsDict, 0);
    }

    private void dfs_2(String[] wordsDict, int i){
        if(i == wordsDict.length - 1) return;
        for(int j = i + 1; j < wordsDict.length; j++){
            int dist = j - i;
            String si = wordsDict[i], sj = wordsDict[j];
            if(si != sj){
                String key1 = si + " " + sj, key2 = sj + " " + si;
                if(!graph.containsKey(key1) && !graph.containsKey(key2)) graph.put(key1, dist);
                else if(graph.containsKey(key1) && graph.containsKey(key2)){
                    graph.replace(key1, Math.min(dist, Math.min(graph.get(key1), graph.get(key2))));
                    graph.remove(key2);
                }
                else if(graph.containsKey(key1)) graph.replace(key1, Math.min(graph.get(key1), dist));
                else if(graph.containsKey(key2)) graph.replace(key2, Math.min(graph.get(key2), dist));
            }
            dfs(wordsDict, j);
        }
    }

    public int shortest_2(String word1, String word2) {

        String key1 = word1 + " " + word2, key2 = word2 + " " + word1;
        if(graph.containsKey(key1) && graph.containsKey(key2)) return Math.min(graph.get(key1), graph.get(key2));
        return graph.containsKey(key1) ? graph.get(key1) : graph.get(key2);
    }





    //做法: 第一遍相当于建立了一个graph, 但好像RTE了
    //Runtime: O(n^2) Space: O(n^2);

    HashMap<String, HashMap<String, Integer>> graph1;
    public void WordDistance(String[] wordsDict) {
        graph1 = new HashMap<>();
        for(String word : wordsDict) graph1.put(word, new HashMap<>());
        dfs(wordsDict, 0);
    }

    private void dfs(String[] wordsDict, int i){
        if(i == wordsDict.length - 1) return;
        for(int j = i + 1; j < wordsDict.length; j++){
            int dist = j - i;
            String si = wordsDict[i], sj = wordsDict[j];
            graph1.get(si).putIfAbsent(sj, Integer.MAX_VALUE);
            graph1.get(sj).putIfAbsent(si, Integer.MAX_VALUE);
            graph1.get(si).replace(sj, Math.min(graph1.get(si).get(sj), dist));
            graph1.get(sj).replace(si, Math.min(graph1.get(sj).get(si), dist));
            dfs(wordsDict, j);
        }
    }

    public int shortest(String word1, String word2) {
        return graph1.get(word1).get(word2);
    }
}
