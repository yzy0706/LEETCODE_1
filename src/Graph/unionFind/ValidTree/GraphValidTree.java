package Graph.unionFind.ValidTree;

public class GraphValidTree {
    // 做法: 这题我用的是unf
    // 1. 先检查edges.length != n-1的话就不可能成为valid tree, return false;
    // 2. 正常建立一个新的unf
    // 3. 根据每一段edges里的edge我们做foreach loop:
    //      a. 先unf.find(edge[0])和unf.find(edge[1]), 如果两个find的祖宗相等, 因为edge[0]和edge[1]也是相连的, 这个graph就成环了, 不可能是tree, return false;
    //      b. 紧接着我们再unf.merge(edge[0], edge[1]);
    // 4. 如果能够跑完这个foreach loop, 证明这个graph没有成环, return true;

    // Runtime: O(n), Space: O(n); parents的大小
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
            int fatherA = find(a);
            int fatherB = find(b);
            if(fatherA != fatherB)
                parents[fatherB] = fatherA;
        }
    }

    public boolean validTree(int n, int[][] edges) {
        if(edges.length != n-1) return false;
        UNF unf = new UNF(n);
        for(int[] edge : edges){
            int f0 = unf.find(edge[0]);
            int f1 = unf.find(edge[1]);
            if(f0 == f1) return false;
            unf.merge(edge[0], edge[1]);
        }
        return true;
    }
}
