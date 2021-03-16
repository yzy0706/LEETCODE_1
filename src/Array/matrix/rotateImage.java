package Array.matrix;

import java.util.HashMap;
import java.util.Map;

public class rotateImage {
    // 做法: 对于这种图像的旋转是有套路的:
    // 1. 如果是顺时针旋转, 对这个图先上下替换, 再沿着对角线替换;
    // 2. 如果是逆时针, 对这个图先左右替换, 再沿着对角线替换
    // 特别注意上下替换i到n/2就可以了, 对角线替换j要从i+1开始, 也就是只操作对角线的右半部分,
    // 如果对于整个matrix都操作的话那一开始换过去, 然后浏览到换过去的位置又换回来了, 等于什么都没干
    // Runtime: O(n^2), Space:O(1)
    public void rotate_reviewed(int[][] matrix) {
        int n = matrix.length;

        for(int i = 0; i < n/2; i++){
            for(int j = 0; j < n; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n-1-i][j];
                matrix[n-1-i][j] = temp;
            }
        }

        for(int i = 0; i < n; i ++){
            for(int j = i + 1; j < n; j ++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

    }




    // 先上下对调，再对角线对调
    public void rotate(int[][] matrix) {
        int l = matrix.length, w = matrix[0].length;
        if(l < 1 || w < 1) return;

        for(int i = 0; i < l/2; i++){
            for(int j = 0; j < w; j++){
                int tmp = matrix[l-1-i][j];
                matrix[l-1-i][j] = matrix[i][j];
                matrix[i][j] = tmp;
            }
        }


        for(int i = 0; i < l; i ++){
            for(int j = i; j < w; j++){
                int tmp = matrix[j][i];
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = tmp;
            }
        }



        return;
    }


    //第一遍用map写的
    public void rotate_map(int[][] matrix) {
        int l = matrix.length, w = matrix[0].length;
        if (l < 1 || w < 1) return;
        Map<int[], Integer> map = new HashMap<>();

        for (int i = 0; i < l; i++) {
            for (int j = 0; j < w; j++) {
                int[] pos = new int[]{j, l - 1 - i};
                int target = matrix[j][l - 1 - i];
                map.put(pos, target);
            }
        }

        for (Map.Entry<int[], Integer> e : map.entrySet()) {
            int[] pos = e.getKey();
            int target = e.getValue();
            System.out.println(target);
            matrix[pos[0]][pos[1]] = target;
        }

        return;
    }
}
