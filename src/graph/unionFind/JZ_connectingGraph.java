package graph.unionFind;

public class JZ_connectingGraph {
    /*
     * @param n: An integer
     */
    int[] f;
    public JZ_connectingGraph(int n) {
        f = new int[n+1];
        for(int  i = 0 ; i <= n; i++){
            f[i] = i;
        }
    }

    public int find(int a , int[] f){
        int j , fa;
        j = a;
        while(f[j] != j) {
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
        int fa ,fb;
        fa = find(a,f);
        fb = find(b,f);
        if(fa != fb){
            f[fa] = b;
        }
    }

    /*
     * @param a: An integer
     * @param b: An integer
     * @return: A boolean
     */
    public boolean query(int a, int b) {

        int fa, fb;
        fa = find(a,f);
        fb = find(b,f);
        return (fa == fb);
    }



}