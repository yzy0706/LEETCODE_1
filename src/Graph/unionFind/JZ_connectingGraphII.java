package Graph.unionFind;

public class ConnectingGraph2 {
    /*
     * @param n: An integer
     */
    int[] f;
    int[] size;
    Set<Integer>  checked =  new HashSet<>();
    public ConnectingGraph2(int n) {
        f = new int[n+1];
        size = new int[n+1];
        for(int  i = 0 ; i <= n ; i++){
            f[i] = i;
            size[i] = 1;
        }
    }

    public int find(int a , int[] f){
        int j,fa;
        j = a;
        while(f[j] != j)  {
            j = f[j];
        }
        while(a != j){
            fa = f[a];
            f[a] = j;
            a = fa;
        }

        return j;
    }
    /*
     * @param a: An integer
     * @param b: An integer
     * @return: nothing
     */
    public void connect(int a, int b) {
        int fa = find(a,f);
        int fb = find(b,f);
        if(fa != fb) {
            f[fa] = b;
            size[fb] += size[fa];
        }
    }

    /*
     * @param a: An integer
     * @return: An integer
     */
    public int query(int a) {
        int z ;
        z = find(a,f);
        return size[z];
    }
}
