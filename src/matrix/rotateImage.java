package matrix;

public class rotateImage {
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


    //第一遍用map写的，但是不应该有
    public void rotate(int[][] matrix) {
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
