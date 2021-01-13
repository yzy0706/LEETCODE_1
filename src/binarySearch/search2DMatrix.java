package binarySearch;

public class search2DMatrix {
    //做法:二分法,用left和right的two pointer方法从两边开始两分法找有没有target, left和right分别是0, m*n-1, 每一个特定的value是matrix[cur/n][cur%n]
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if(m == 0) return false;
        int n = matrix[0].length;
        int left = 0, right = m*n-1;
        int curPos, cur;
        while(left <= right){
            curPos = (left + right)/2;
            cur = matrix[curPos / n][curPos % n];
            if(cur == target) return true;
            else{
                if(target < cur) right = curPos - 1;
                else left = curPos + 1;
            }
        }
        return false;
    }
}
