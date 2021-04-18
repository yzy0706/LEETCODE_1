package Tree.BFS.topologicalSort;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MaxNetworkRank {

    //做法: 用类似于拓扑结构的做法, 但其实也不全是, 因为并不需要去找indegree == 0的点
    // 1. 浏览每一对road, 两端的城市的indegree都++, 用boolean[][] connected记录当前的城市是不是相连的
    // 2. 记录完了以后对于每一个城市我们都去跟比其它的城市组成一对, 类似于permutation, 然后计算indegree的和, 如果两个城市相连就再减1
    // Runtime: O(n^2), Space: O(n^2)
    public int maximalNetworkRank_permute(int n, int[][] roads) {
        if(roads.length < 1) return 0;
        if(roads.length == 1) return 1;
        int[] indegree = new int[n];
        boolean[][] connected = new boolean[n][n];
        for(int[] road : roads){
            indegree[road[0]]++;
            indegree[road[1]]++;
            connected[road[0]][road[1]] = true;
            connected[road[1]][road[0]] = true;
        }
        int res = 0;
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                res = Math.max(res, indegree[i] + indegree[j] + (connected[i][j] ? -1 : 0));
            }
        }
        return res;
    }




    int res = 0;
    public int maximalNetworkRank_2(int n, int[][] roads) {
        if(roads.length < 1) return 0;
        if(roads.length == 1) return 1;
        int[] indegree = new int[n];
        List<Integer> cities = new ArrayList<>();
        HashSet <String> pairs = new HashSet<>();
        for(int[] road : roads){
            indegree[road[0]]++;
            indegree[road[1]]++;
            if(!cities.contains(road[0])) cities.add(road[0]);
            if(!cities.contains(road[1])) cities.add(road[1]);
            pairs.add(road[0] + " " + road[1]);
        }
        backtrack(n, 0, indegree, cities, pairs);
        return res;
    }

    private void backtrack(int n, int i, int[] indegree, List<Integer> cities, HashSet<String> pairs){
        if(i == cities.size() - 1) return;
        int city1 = cities.get(i);
        for(int j = i + 1; j < cities.size(); j++){
            int city2 = cities.get(j);
            int rank = indegree[city1] + indegree[city2];
            int[] cur = new int[]{city1, city2};
            if(pairs.contains(city1 + " " + city2) || pairs.contains(city2 + " " + city1)) rank --;

            res = Math.max(res, rank);
            backtrack(n, j, indegree, cities, pairs);
        }
    }
}
