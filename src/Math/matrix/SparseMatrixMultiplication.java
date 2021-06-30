package Math.matrix;

import java.util.ArrayList;
import java.util.List;

public class SparseMatrixMultiplication {
    // 做法: 还有一个做法是用List[] ARows去储存A的每一个row里面非0的值的column index和val, 再根据每一个row的每一个colIndex去乘对应的B[colIndex][j];
    // 1. 先forloop A的所有row, 把所有row里面的非0的index和val加入到ARows[i]的list里
    // 2. forloop ARows里面的每一个list, 我们找到A里面每一个row里面的非0的位置, A[i][colIndex], forloop j 从0到n, 也就是B的每一个column, 对应的B的值bVal就是B[colIndex][j], 然后 res[i][j] += aVal * bVal;

    // Runtime: O(mn), Space: O(mn);

    public int[][] multiply_store_row_vals(int[][] A, int[][] B) {
        int m = A.length, k = A[0].length, n = B[0].length;
        int[][] res = new int[m][n];
        List[] ARows = new List[m];
        for(int i = 0; i < m; i++){
            List<Integer> curRow = new ArrayList<>();
            for(int j = 0; j < k; j++){
                if(A[i][j] != 0){
                    curRow.add(j);
                    curRow.add(A[i][j]);
                }
            }
            ARows[i] = curRow;
        }

        for(int i = 0; i < m; i++){
            List<Integer> ARow = ARows[i];
            for(int a = 0; a < ARow.size()-1; a += 2){
                int aCol = ARow.get(a);
                int aVal = ARow.get(a + 1);
                for(int j = 0; j < n; j ++){
                    int bVal = B[aCol][j];
                    if(bVal != 0) res[i][j] += aVal * bVal;
                }
            }
        }
        return res;
    }



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
