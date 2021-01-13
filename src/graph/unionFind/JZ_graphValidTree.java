package graph.unionFind;

public class JZ_graphValidTree {
    public boolean validTree(int n, int[][] edges) {
        UNF unf = new UNF(n);
        for (int[] edge : edges) {
            if (unf.find(edge[0]) == unf.find(edge[1])) return false;
            else unf.union(edge[0], edge[1]);
        }

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (unf.f[i] == i) cnt++;
        }
        if (cnt > 1) return false;
        else return true;
        // return unf.checkRoot(n);
    }

    public class UNF {
        int[] f;

        public UNF(int n) {
            f = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                f[i] = i;
            }
        }

        public int find(int x) {
            int fx, j;
            j = f[x];
            while (j != f[j]) {
                j = f[j];
            }
            while (x != j) {
                fx = f[x];
                f[x] = j;
                x = fx;
            }
            return j;
        }

        public void union(int x, int y) {
            int fx = find(x);
            int fy = find(y);
            if (fx != fy) {
                f[f[x]] = fy;
                // f[f[y]] = fx;
            }
        }

        public boolean checkRoot(int n) {
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                if (f[i] == i) cnt++;
            }
            if (cnt > 1) return false;
            else return true;
        }
    }
}


