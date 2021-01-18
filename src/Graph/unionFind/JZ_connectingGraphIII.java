package Graph.unionFind;

public class ConnectingGraphIII {
    /**
     * @param a: An integer
     * @param b: An integer
     * @return: nothing
     */
    public int[] f ;
    public int n;
    public ConnectingGraphIII(int n){
        f = new int[n+1];
        this.n = n;
        for(int  i = 0 ; i <= n ; i++){
            f[i] = i;
        }
    }

    public int find(int a){
        int fa , j;
        j = a;
        while (f[j] != j){
            j = f[j];
        }
        while (a != j){
            fa = f[a];
            f[a] = j;
            a = fa;
        }
        return j;
    }

    public void connect(int a, int b) {
        int fa = find(a);
        int fb = find(b);
        if(fa != fb) f[fa] = fb;
    }

    /**
     * @return: An integer
     */
    public int query() {
        int cnt = 0;
        System.out.println(n);
        for(int i = 1 ; i <= n ; i++){
            if(f[i] == i) cnt ++;
        }
        return cnt;
    }
}
