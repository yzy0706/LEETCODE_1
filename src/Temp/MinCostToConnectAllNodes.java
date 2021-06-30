package Temp;

import java.util.Arrays;

public class MinCostToConnectAllNodes {

        // represent dis-joint set using an array
        private int[] parent;
        private int connectionLength;

        private void union(int v1, int v2) {
            // check if both vertices are descendants of same parent i.e. they belong to same ultimate parent/root
            int parent1 = this.parent[v1];
            int parent2 = this.parent[v2];
            if (parent1 == parent2) {
                return;
            }
            connectionLength--;
            this.parent[v2] = parent1;
        }

        private int findSet(int v) {
            int temp = v;
            while(parent[temp] != temp){
                temp = parent[temp];
            }
            while(v != temp){
                int pv = parent[v];
                parent[v] = temp;
                v = pv;
            }
            return v;
        }

        public int minCostToConnectNodes(int n, int[][] edges, int[][] newEdges) {
            this.connectionLength = n;
            this.parent = new int[n + 1];
            // Initialization - make every node parent of its own
            for (int i = 0; i <= n; i++) {
                this.parent[i] = i;
            }
            for (int[] edge : edges) {
                union(edge[0], edge[1]);
            }

            Arrays.sort(newEdges, (a, b) -> a[2] - b[2]);
            int minCost = 0;
            for(int[] newEdge : newEdges) {
                // check if new edge vertices are already connected
                if (findSet(newEdge[0]) == findSet(newEdge[1])) {
                    continue;
                }
                union(newEdge[0], newEdge[1]);
                minCost = minCost + newEdge[2];
                if (connectionLength == 1) {
                    return minCost;
                }
            }
            return -1;
        }
}
