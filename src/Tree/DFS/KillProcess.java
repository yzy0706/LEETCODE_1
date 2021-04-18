package Tree.DFS;

import java.util.*;

public class KillProcess {
    // 做法: 第一遍用dfs做的, 就是从kill那一层开始每一次找到一个儿子, 就以当前的儿子作为parent继续进行recursion, 一直到当前儿子没法作为parent为止
    // Runtime: O(n^2), Space: O(n)
    List<Integer> res;
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        res = new ArrayList<>();
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        if(pid.size() != ppid.size()) return res;
        for(int i = 0; i < ppid.size(); i++){
            if(pid.get(i) == kill && ppid.get(i) == 0) return pid;
        }
        for(int i = 0; i < ppid.size(); i++){
            map.putIfAbsent(ppid.get(i), new ArrayList<>());
            map.get(ppid.get(i)).add(pid.get(i));
        }
        res.add(kill);
        dfs(pid, ppid, map, kill);
        return res;
    }


    public void dfs(List<Integer> pid, List<Integer> ppid, HashMap<Integer, List<Integer>> map, int p){
        if(!map.containsKey(p)) return;
        for(int i : map.get(p)){
            res.add(i);
            dfs(pid, ppid, map, i);
        }
    }



    // 做法: 第一遍用dfs做的, 就是从kill那一层开始每一次找到一个儿子, 就以当前的儿子作为parent继续进行recursion, 一直到当前儿子没法作为parent为止
    // Runtime: O(n^2), Space: O(n)
    List<Integer> res;
    public List<Integer> killProcess_dfs(List<Integer> pid, List<Integer> ppid, int kill) {
        res = new ArrayList<>();
        for(int i = 0; i < ppid.size(); i++)
            if(ppid.get(i) == 0 && pid.get(i) == kill) return pid;
        res.add(kill);
        HashSet<Integer> pSet = new HashSet<>(ppid);
        dfs(pid, ppid, pSet, kill);
        return res;
    }


    public void dfs(List<Integer> pid, List<Integer> ppid, HashSet<Integer> pSet, int p){
        if(!pSet.contains(p)) return;
        for(int i = 0; i < ppid.size(); i++){
            if(ppid.get(i) == p){
                res.add(pid.get(i));
                dfs(pid, ppid, pSet, pid.get(i));
            }
        }
    }



    // 做法: 第二遍用bfs做的， 每次把每一层的儿子都加进去， 其实也有点慢
    // Runtime: O(n^2), Space: O(n)

    public List<Integer> killProcess_bfs(List<Integer> pid, List<Integer> ppid, int kill) {
        res = new ArrayList<>();
        if(pid.size() != ppid.size()) return res;
        for(int i = 0; i < ppid.size(); i++){
            if(pid.get(i) == kill && ppid.get(i) == 0) return pid;
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(kill);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                int cur = queue.poll();
                res.add(cur);
                for(int ind = 0; ind < ppid.size(); ind++){
                    if(ppid.get(ind) == cur) queue.offer(pid.get(ind));
                }
            }
        }
        return res;
    }
}
