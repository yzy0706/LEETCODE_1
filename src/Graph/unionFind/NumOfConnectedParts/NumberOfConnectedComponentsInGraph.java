package Graph.unionFind.NumOfConnectedParts;

import java.util.HashSet;
import java.util.Set;

public class NumberOfConnectedComponentsInGraph {
    // 做法: 因为这题是要我判断连通性, 即几个edge的端点是不是最终都可以连接上同一个祖宗, 再加上我本来也暂时只比较了解拓扑排序和unf, 所以我第一时间想到了unf, 来通过对于每个edge两两的merge来检查一共有几个祖宗, 也就是互相相连的部分的总数
    // 1. 新建一个n大小的unf, foreach loop所有的edge , merge每个edge上两两的端点
    // 2. foreach loop结束了以后, 检查unf的int[] parents里面有几个father, 因为所有能够互相相连的点, 他们的father都会变成通过merge指向他们最终的father, 所以father的个数就是互相相连的部分的个数
    // Runtime: O(n), Space: O(n)
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
                parents[a] = parents[parents[a]];
                a = parents[a];
            }
            return a;
        }

        public void merge(int a, int b){
            int fa = find(a);
            int fb = find(b);
            if(fa != fb){
                parents[fb] = fa;
            }
        }
    }

    public int countComponents(int n, int[][] edges) {
        UNF unf = new UNF(n);
        for(int[] edge : edges){
            unf.merge(edge[0], edge[1]);
        }
        int res = 0;
        Set<Integer> fathers = new HashSet<>();
        for(int i = 0; i < n; i++){
            int fi = unf.find(i);
            if(!fathers.contains(fi)){
                fathers.add(fi);
                res ++;
            }
        }
        return res;
    }
}
