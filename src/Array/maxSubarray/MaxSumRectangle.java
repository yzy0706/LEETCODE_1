package Array.maxSubarray;

public class MaxSumRectangle {
    //做法： 这题主要是用三个forloop， 来变化这个长方形的体积
    //1. leftCol从0到l-1， 代表这个长方形左边从matrix左往右移动, 每移动一次建立一个新的rowSums[w]来记录这个长方形每一个row的sum的和
    //2. rightCol从leftCol到l-1，代表这个长方形右边从leftCol的位置往右边移动
    //3. i从0到w-1， 代表这个长方形的顶部从0到w-1， 在这个forloop结束以后我们得到所有row分别的数字的和: rowSums[i] += Array.matrix[i][rightCol];
    //4. kadane(tmp), 就会得到这个tmp上sum最大的一段的起始点和sum， 这就是这个长方形的宽， 然后我们再更新res, left和right

    //Runtime: O(wl^2), l是这个长方形的长度， space: O(l)

    public int findMaxSubMatrix(int[][] matrix){
        int left = 0, right = 0, top = 0, bot = 0;
        int res = Integer.MIN_VALUE;
        int w = matrix.length, l = matrix[0].length;

        for(int leftCol = 0; leftCol < l; leftCol++){
            int[] rowSums = new int[w]; //从每一行的元素的和, 随着rightCol的增加增加
            for(int rightCol = leftCol; rightCol < l; rightCol++){
                for(int i = 0; i < w; i++){
                    rowSums[i] += matrix[i][rightCol]; //根据rightCol的移动慢慢增加长方形的边长
                }
                int[] result = kadane(rowSums); //找到[leftCol, rightCol]这个长度里sum最大的宽度[top, down]
                if(result[2] > res){
                    left = leftCol;
                    right = rightCol;
                    top = result[0];
                    bot = result[1];
                    res = result[2];
                }
            }
        }

        System.out.println("The rectangle length from" + left + "to" + right + ", width from" + top + "to" + bot + ", and the sum is" + res);
        return res;
    }

    public int[] kadane(int[] rowSums){
        int[] res = new int[]{0, 0, rowSums[0]};
        int maxBefore = rowSums[0];
        int curStart = 0, curEnd = 0;

        for(int i = 1; i < rowSums.length; i++){
            if(maxBefore <= 0){
                maxBefore = rowSums[i];
                curStart = i;
                curEnd = i;
            }
            else{
                maxBefore += rowSums[i];
                curEnd = i;
            }
            if(maxBefore > res[2]){
                res[2] = maxBefore;
                res[0] = curStart;
                res[1] = curEnd;
            }
        }
        return res;
    }
}
