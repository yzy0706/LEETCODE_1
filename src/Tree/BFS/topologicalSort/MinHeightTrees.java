package Tree.BFS.topologicalSort;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MinHeightTrees {
    // 做法: 这题主要是一个拓扑排序的做法, 只不过一般的拓扑排序都用indegree[i]计算有多少个邻居, 而这个题使用neighbors.get(i)来记录每一个node的邻居分别是哪几个
    // 1. 先根据每一个双向的edge记录每一个点的neighbor是哪些, 记录在一个hashset里
    // 2. 找出neighbors里面所有邻居只有一个的点, 放在一个List<Integer> leaves里面
    // 3. 用一个whileloop对于这个leaves做bfs, 当 n > 2的时候, n -= leaves.size(); ,
    //    a. 建立一个新的ArrayList<>() roots, 根据每一个leaf找到他们在neighbors里面的邻居leafneighbor, int leafNeighbor = leafNeighbors.iterator().next();
    //    b. 然后在这个邻居neighbor自己的邻居们neighbors里面删除掉这个leave, 看这个邻居是不是除了leaf只剩下一个邻居了, 是的话加到roots里.
    //    c. 更新leaves = roots

    // Runtime: O(V+E), Space: O(V+E), E是每个点的neighbor

    public List<Integer> findMinHeightTree_TOPO(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        if(n == 1){
            res.add(0);
            return res;
        }
        List<Set<Integer>> neighbors = new ArrayList<>();
        for(int i = 0; i < n; i++){
            neighbors.add(new HashSet<>());
        }

        for(int[] edge : edges){ //neighbors和indegree一样记录了每个点的邻居, 不同的是neighbors记录了邻居分别是谁而不只是个数
            neighbors.get(edge[0]).add(edge[1]);
            neighbors.get(edge[1]).add(edge[0]);
        }

        List<Integer> leaves = new ArrayList<>(); //这些都是邻居只有一个的点, 也就是叶子
        for(int i = 0; i < neighbors.size(); i++){
            if(neighbors.get(i).size() == 1){
                leaves.add(i);
            }
        }

        while(n > 2){
            n -= leaves.size();
            List<Integer> roots = new ArrayList<>();
            for(int i = 0; i < leaves.size(); i++){ //bfs, 把每一个neighbor只有1的leaves里的leave找出来, 看对方的neighbor是不是除了它之外只有一个
                int leaf = leaves.get(i);
                Set<Integer> leafNeighbors = neighbors.get(leaf); //当前这个leave的邻居的set, size只有1
                int leafNeighbor = leafNeighbors.iterator().next();  //当前这个leave的唯一的neighbor, 也是它与这个树相连的唯一的点
                neighbors.get(leafNeighbor).remove(leaf); //从大的indegree里面删掉当前的leaf
                if(neighbors.get(leafNeighbor).size() == 1) roots.add(leafNeighbor);
            }
            leaves = roots;
        }

        return leaves;
    }

















    //第一遍尝试用UNF做， 但UNF没法处理很多个不同tree的情况
    class UNF{
        int[] parents;

        public UNF(int n){
            this.parents = new int[n];
            for(int i = 0; i < n; i++){
                parents[i] = i;
            }
        }

        public int find(int a){
            while(parents[a] != a){
                a = parents[a];
                parents[a] = parents[parents[a]];
            }
            return a;
        }

        private void merge(int a, int b){
            int fa = find(a);
            int fb = find(b);
            if(fa != fb){
                parents[fb] = parents[fa];
            }
        }
    }

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        UNF unf = new UNF(n);
        List<Integer> res = new ArrayList<>();
        if(edges.length != n-1) return res;
        for(int[] edge : edges){
            int fa = unf.find(edge[0]);
            int fb = unf.find(edge[1]);
            if(fa == fb) return res;
            unf.merge(edge[0], edge[1]);
        }
        return res;
    }

}
