package matrix;

import java.util.ArrayList;
import java.util.List;

public class spiralMatrix {
    // solution给的第一种解法是用两个int[] {0, 1, 0, -1}, {1, 0, -1, 0}和一个标记 dir来分别代表纵轴和横轴的移动方向，
    // 在一个 i  <= l*w 的 forloop中如果预先检查到到了纵横的边界则 (dir + 1) % 4.
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if(matrix.length < 1) return res;
        int w = matrix.length, l = matrix[0].length;
        int[] vert = new int[]{0, 1, 0, -1}, hori = new int[]{1, 0, -1, 0};
        boolean[][] visited = new boolean[w][l];
        int i = 0, j = 0, dir = 0;
        for(int a = 0; a < w*l; a++){
            res.add(matrix[i][j]);
            visited[i][j] = true;
            int tmpI = i + vert[dir], tmpJ = j + hori[dir];
            if(tmpI >= 0 && tmpI < w && tmpJ >= 0 && tmpJ < l && !visited[tmpI][tmpJ]){
                i = tmpI;
                j = tmpJ;
            }
            else{
                dir = (dir + 1) % 4;
                i += vert[dir];
                j += hori[dir];
            }
        }

        return res;
    }
}
}
