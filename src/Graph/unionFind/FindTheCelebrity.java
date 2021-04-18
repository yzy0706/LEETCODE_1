package Graph.unionFind;




public class FindTheCelebrity {
    public int findCelebrity(int n) {
        UNF unf = new UNF(n);
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(i != j && knows(i, j)) unf.merge(i, j);
            }
        }
        int cele = -1;
        for(int i = 0; i < n; i++){
            if(cele == -1 && unf.parents[i] == i) cele = i;
        }
        return cele;
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

        }
    }

    public boolean knows(int a, int b){
        return false;
    }
}
