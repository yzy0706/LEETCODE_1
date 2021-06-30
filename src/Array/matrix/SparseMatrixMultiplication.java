package Array.matrix;

public class SparseMatrixMultiplication {
    // 做法: 我直接用的matrix相乘的公式去求结果: mat1的每一个row和mat2的每一个column的dot product
    // 1. forloop i 从0到m, forloop l 从0到n, 代表着每浏览mat1的一行, 都会浏览mat2的所有列
    // 2. 然后因为mat1的col数肯定是和mat2的row数相等的, 所以当mat1的i row对应mat2的l column的时候, forloop j从0到k,
    // 相当于浏览了i row和 l column上的每一个数, 我们加上他们的乘积就是dot product: res[i][l] += mat1[i][j] + mat2[j][k];

    // Runtime: O(mnk), Space: O(mn);

    public int[][] multiply(int[][] mat1, int[][] mat2) {
        int m = mat1.length, k = mat1[0].length, n = mat2[0].length;
        int[][] res = new int[m][n];
        for(int i = 0; i < m; i ++){
            for(int l = 0; l < n; l ++){
                for(int j = 0; j < k; j ++){
                    res[i][l] += mat1[i][j] * mat2[j][l];
                }
            }
        }
        return res;
    }
}
