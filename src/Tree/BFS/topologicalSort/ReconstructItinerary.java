package Tree.BFS.topologicalSort;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class ReconstructItinerary {
    // 做法: 因为给了一个确定的起点"JFK", 其实就相当于拿到了一个tropological bfs里面indegree[i] == 0的点.
    // 1. 根据每一张ticket把每一个起点和对应的 PriorityQueue<String>终点们放在一个map里
    // 2. 建立一个dfs的helper:
    //      a. 得到当前起点origin的所有目的地, 而且用whileloop来bfs这个PriorityQueue, dfs每一个得到的起点
    //      b. dfs完所有的目的地以后, 再把当前的起点用addFirst加在第一个

    // Runtime: O(n), Space: O(n)
    public List<String> findItinerary(List<List<String>> tickets) {
        LinkedList<String> res = new LinkedList<>();
        HashMap<String, PriorityQueue<String>> map = new HashMap<>();
        for(List<String> edge : tickets){
            map.putIfAbsent(edge.get(0), new PriorityQueue<String>());
            map.get(edge.get(0)).offer(edge.get(1));
        }
        dfs(res, map, "JFK");
        return res;

    }


    public void dfs(LinkedList<String> res, HashMap<String, PriorityQueue<String>> map, String origin){
        PriorityQueue<String> destinations = map.get(origin);
        while( destinations != null && !destinations.isEmpty()){
            dfs(res, map, destinations.poll());
        }
        res.addFirst(origin); //用addFirst可以保证在dfs了以后, 当前这个起点的名字还是排在第一位, 而且不用reverse res
    }
}
