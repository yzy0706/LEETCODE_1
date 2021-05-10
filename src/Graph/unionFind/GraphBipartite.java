package Graph.unionFind;

import java.util.LinkedList;
import java.util.Queue;

public class GraphBipartite {
    // 做法: 这题用dfs做的, 我想的太复杂了
    // 1. 就是每次从每个点出发去dfs它所有相连的点, 并且用1或者2来标记两组不同的node, 如果碰到一个mark[j]不是0的node, 而且它还与上一个与它相邻的点的颜色一样, 直接return false
    // 2. 如果从0到n-1做了dfs的每一个点都没有出现相邻的非0mark一样的情况, 那么整个题的就是true

    // Runtime: O(n), Space: O(n)

    public boolean isBipartite_dfs(int[][] graph) {
        int n = graph.length;
        int[] mark = new int[n];
        for(int i = 0; i < n; i++){
            if(mark[i] == 0 && !dfs(graph, i, 2, mark)) return false;
        }
        return true;
    }

    private boolean dfs(int[][] graph, int i, int cur, int[] mark){
        if(mark[i] != 0) return mark[i] == cur;
        mark[i] = cur;
        int[] edge = graph[i];
        for(int j : edge){
            if(!dfs(graph, j, 3 - cur, mark)) return false;
        }
        return true;
    }

    // 做法: 第二个是bfs的做法
    // 1. forloop 0到n, 如果mark[i] != 0到话, 以i为queue的最开始值做bfs
    // 2. bfs的过程中, 正常的bfs流程, 在每一层中poll()出来当前queue里面的每一个node, 且每一层的mark都来回变换
    //      a. 如果当前mark[cur] != 0 && mark[cur] != curMark; curMark是它应该有的标记, 证明出现了两个相同mark的node互相相连, 直接return false
    //      b. 否则如果mark[cur] != 0, 证明它与cumMark一样, 它就不用进入bfs了, 直接poll()
    //      c. 如果程序能读到这一步, 证明mark[cur] == 0, 那么就根据graph[cur]比所有能跟他相连的点放到queue里去
    // 浏览并标记完一层的所有node以后,  curMark = 3 - mark; 下一层所有邻居的mark应该不一样

    // Runtime: O(n), Space: O(n)

    public boolean isBipartite_bfs(int[][] graph) {
        int n = graph.length;
        int[] mark = new int[n];
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < n; i++){
            if(mark[i] == 0){
                queue.offer(i);
                int curMark = 1;
                while(!queue.isEmpty()){
                    int size = queue.size();
                    for(int j = 0; j < size; j++){
                        int cur = queue.poll();
                        if(mark[cur] != 0 && mark[cur] != curMark) return false;
                        else if(mark[cur] == curMark) continue;
                        else{
                            mark[cur] = curMark;
                            for(int next : graph[cur]) queue.offer(next);
                        }
                    }
                    curMark = 3 - curMark;
                }
            }
        }
        return true;
    }


    //UNF, 但如果是都追溯到一个祖宗就没有意义了， 以后想到unf都可以想一下dfs
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        UNF unf = new UNF(n);
        for(int i = 0; i < n; i++){
            int[] edge = graph[i];
            for(int j : edge){
                if(unf.find(i) == unf.find(j)) return false;
                unf.merge(i, j);
            }
        }
        return true;
    }

    class UNF{
        int[] parents;

        public UNF(int n){
            this.parents = new int[n];
            for(int i = 0; i < n; i++){
                parents[i] = i;
            }
        }

        public int find(int a){
            while(a != parents[a]){
                parents[a] = parents[parents[a]];
                a = parents[a];
            }
            return a;
        }

        public void merge(int a, int b){
            int fa = find(a), fb = find(b);
            if(fa != fb) parents[fb] = fa;
        }
    }
}
