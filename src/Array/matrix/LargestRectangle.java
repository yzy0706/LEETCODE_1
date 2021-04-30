package Array.matrix;

public class LargestRectangle {

        //做法: 这题主要分为两部分
        //1. 浏览每一行的第一个开始, 记录左边最多了连续递增了几个matrix[i][j] == '1';
        //2. 浏览每一个matrix上面的单元,如果matrix[i][j]大于0, 就代表他左边有几个连续的1, 也就是当前得到的长方形的最短长度
        //由matrix[i][j]开始顺着column往上浏览, 一直到碰到0为止, 在这个往上浏览的forloop之中我们随时根据matrix[row][j]更新最短的长度,
        //并且更新最大的面积 maxArea = Math.max(maxArea, minLength * (i - row + 1));

        //Runtime: O(m^2*n), 往上寻找最短宽度的过程可能会直接浏览所有的row; space: O(1), 并没有新建别的数据结构

        public int maximalRectangle(char[][] matrix) {
            if(matrix.length < 1) return 0;
            int w = matrix.length, l = matrix[0].length;
            for(int i = 0; i < w; i++){
                for(int j = 1; j < l; j ++){
                    if(matrix[i][j] == '1'){
                        matrix[i][j] = (char)(matrix[i][j-1]+ 1); //先把每行连续的1标上数量
                    }
                }
            }

            int maxArea = 0;

            for(int i = 0; i < w; i++){
                for(int j = 0; j < l; j++){
                    if(matrix[i][j] - '0' > 0){
                        int minLength = matrix[i][j] - '0';
                        for(int row = i; row >= 0 && matrix[row][j] != '0'; row --){
                            minLength = Math.min(minLength, matrix[row][j] - '0');  //更新一下最小宽度
                            maxArea = Math.max(maxArea, minLength * (i - row + 1));
                            //根据当前最小长度, 起始的行数i和终止的行数row的宽度计算面积
                        }
                    }
                }
            }
            return maxArea;
        }
}
