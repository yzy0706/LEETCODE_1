package graph.kruskal;

import java.util.Arrays;
// Kruskal主要是去用一个unf去链接connection的各个点， 并且各个vertice中间应该是双向的， 我们用一个unf来记录各个点的
//祖宗和他们所对应的祖宗的叠加距离， 如果找到两个点祖宗一样， 直接continue， 反之， 如果哪个祖宗的叠加距离大， 那这个
//祖宗就成为另外一个祖宗的祖宗， 并把他们的叠加距离继续叠加，而且我们一定要记得把connections从大到小先排列了再去forloop。

class UNF{
    private int[] parents;
    private int[] weights;

    public UNF(int N){
        parents = new int[N+1];
        weights = new int[N+1];

        for(int i = 0; i < N; i++){
            parents[i] = i;
            weights[i] = 1;
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
        int rootA = find(a);
        int rootB = find(b);
        if(rootA == rootB) return;
        //比较两个root的weight
        if(weights[rootA] > weights[rootB]){
            //合并他们的祖宗
            parents[rootB] = parents[rootA];
            //weight加一下
            weights[rootA] += parents[rootB];
        }
        else{
            parents[rootA] = parents[rootB];
            weights[rootB] += weights[rootA];
        }
    }

    public boolean isSameTree(int a, int b) {
        return find(a) == find(b);
    }
}


public class connectingCitiesWithMinCost {
    public int minimumCost(int N, int[][] connections) {
        UNF unf = new UNF(N);
        Arrays.sort(connections, (a, b) -> a[2] - b[2]);
        int checkNum =  0, cost = 0;
        for(int[] connection : connections){
            if(unf.isSameTree(connection[0], connection[1])) continue;
            unf.merge(connection[0], connection[1]);
            cost += connection[2];
            checkNum++;
        }
        if(checkNum == N-1) return cost;
        return -1;
    }
}
