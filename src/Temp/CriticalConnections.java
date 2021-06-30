package Temp;

import java.util.*;

public class CriticalConnections {
    // 做法: 主要的方法是用int[] depths来记录每一个节点在dfs的过程中被找到的最浅的深度, 然后检查它的邻居, 如果有一个邻居的depth比它还小, 那证明之前已经有别的node跟这个邻居相连了, 那么就删除当前node和邻居的connection
    // 1. 先根据所有的connection建立双向的graph, HashMap<Integer, List<Integer>>, 建立一个HashSet<List<Integer>> connectionsSet = new HashSet<>(connections); 方便之后删除non-critical connection
    // 2. 建立一个int[] depths, 初始值一定要是-2, 而不是-1, 因为depth的0的时候要排查depths[neighbour] = depth - 1;
    // 3. 建立一个helper, 从node = 0, depth = 0开始做dfs:
    //      a. 如果depths[cur] >= 0, 证明当前cur被访问过, return depth[cur];
    //      b. 如果还没有浏览到这一层, depths[cur] = depth;
    //      c. 访问cur的每一个邻居neighbour, int minDepthFound = depth, 记录当前cur能找到的最小的depth:
    //          i. 如果depths[neighbour] = depth - 1; 那就是cur的上一层, 直接略过
    //          ii. int neighbourDepth = dfs(neighbours, connectionsSet, neighbour, depth + 1, depths); 更新minDepthFound, 如果邻居的深度  < depth, 删除掉当前的connection
    //          iii. return minDepthFound;

    // Runtime: O(n), Space: O(n^2)

    public List<List<Integer>> criticalConnections_LeetCode(int n, List<List<Integer>> connections) {
        HashMap<Integer, List<Integer>> neighbours = new HashMap<>();
        for(List<Integer> connection : connections){
            int a = connection.get(0), b = connection.get(1);
            neighbours.putIfAbsent(a, new ArrayList<>());
            neighbours.putIfAbsent(b, new ArrayList<>());
            neighbours.get(a).add(b);
            neighbours.get(b).add(a);
        }
        // System.out.println(neighbours.get(0).size());
        int[] depths = new int[n];
        Arrays.fill(depths, -2);
        HashSet<List<Integer>> connectionsSet = new HashSet<>(connections);
        dfs(neighbours, connectionsSet, 0, 0, depths);
        return new ArrayList<>(connectionsSet);
    }


    public int dfs(HashMap<Integer, List<Integer>> neighbours, HashSet<List<Integer>> connectionsSet, int cur, int depth, int[] depths){
        if(depths[cur] >= 0) return depths[cur];
        depths[cur] = depth;
        int minDepthFound = depth;
        for(int neighbour : neighbours.get(cur)){
            if(depths[neighbour] == depth - 1){
                continue; //parents
            }
            int neighbourDepthFound = dfs(neighbours, connectionsSet, neighbour, depth + 1, depths);
            minDepthFound = Math.min(minDepthFound, neighbourDepthFound);
            if(neighbourDepthFound <= depth){
                connectionsSet.remove(Arrays.asList(cur, neighbour));
                connectionsSet.remove(Arrays.asList(neighbour, cur));
            }
        }
        return minDepthFound;
    }

    public List<List<Integer>> criticalConnections_reviewed(int n, List<List<Integer>> connections) {
        HashMap<Integer, List<Integer>> neighbours = new HashMap<>();
        for(List<Integer> connection : connections){
            int a = connection.get(0), b = connection.get(1);
            neighbours.putIfAbsent(a, new ArrayList<>());
            neighbours.putIfAbsent(b, new ArrayList<>());
            neighbours.get(a).add(b);
            neighbours.get(b).add(a);
        }
        int[] depths = new int[n];
        Arrays.fill(depths, -1);
        HashSet<List<Integer>> connectionsSet = new HashSet<>(connections);
        return new ArrayList<>(connectionsSet);
    }


    public int dfs_2(HashMap<Integer, List<Integer>> neighbours, HashSet<List<Integer>> connectionsSet, int cur, int depth, int[] depths){
        if(depths[cur] >= 0) return depths[cur];
        int minDepthFound = depth;
        depths[cur] = depth;
        for(int neighbour : neighbours.get(cur)){
            if(depths[neighbour] == depth - 1) continue; //parents
            int curDepthFound = dfs(neighbours, connectionsSet, neighbour, depth + 1, depths);
            minDepthFound = Math.min(minDepthFound, curDepthFound);
            if(minDepthFound <= depth){
                connectionsSet.remove(Arrays.asList(cur, neighbour));
                connectionsSet.remove(Arrays.asList(neighbour, cur));
            }
        }
        return minDepthFound;
    }


//
//    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
//        HashMap<Integer, List<Integer>> neighbours = new HashMap<>();
//        for(List<Integer> connection : connections){
//            int a = connection.get(0), b = connection.get(1);
//            neighbours.putIfAbsent(a, new ArrayList<>());
//            neighbours.putIfAbsent(b, new ArrayList<>());
//            neighbours.get(a).add(b);
//            neighbours.get(b).add(a);
//        }
//        List<List<Integer>> res = new ArrayList<>();
//        for(int start : neighbours.keySet()){
//            List<Integer> neighbour = neighbours.get(start);
//
//        }
//    }
//
//
//    public void dfs_1(HashMap<Integer, List<Integer>> neighbours, HashSet<Integer> visited, int cur){
//        List<Integer> curNeighbour = neighbours.get(cur);
//    }
}
