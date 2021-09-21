package DynamicProgramming.accumulate;

public class UniqueBinaryTrees {
    // 最tricky的地方在于  F(i,n) = G(i - 1) * G (n - i);
    // 意思是以i为root的binary tree的数量等于他左右两边的数字个数的G值的乘积，然后G[0] = 1, G[1] = 1;
    public int numTrees(int n) {
        int[] G = new int[n+1];
        G[0] = 1;
        G[1] = 1;


        //这是C(j, i)的求法
        for(int i = 2; i <= n; i++){
            for(int j = 1; j <= i; j++){
                G[i] += G[j - 1] * G[i - j];
            }
        }

        return G[n];
    }
}
