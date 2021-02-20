package DynamicProgramming.twoDimensionalRelations;

public class OutOfBoundsPaths {






    public int findPaths(int m, int n, int N, int i, int j) {
        int res = 0;
        for(int r = Math.max(0, i - N); r < Math.min(m, i + N); r++){
            for(int c = Math.max(0, j - N); c < Math.min(n, j + N); c++){
                if(Math.abs(r - i) + Math.abs(c - j) <= N){
                    if(r == 0 || r == m - 1 || c == 0 || c == n - 1){
                        res++;
                    }
                }
            }
        }
        return res;
    }
}
