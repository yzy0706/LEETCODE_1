package binarySearch;

public class search2DMatrix_II {
    //二分法，用forloop浏览一个比较短的边， 并且增设一个boolean vertical来分别二分寻找垂直和水平的横或者列有没有target，只要一个有就return true
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if(m == 0) return false;
        int n = matrix[0].length;
        int shorter= Math.min(m, n);
        for(int i = 0; i < shorter; i++){
            boolean vert = search(matrix, target, true, i);
            boolean hori = search(matrix, target, false, i);
            if(vert || hori) return true;
        }
        return false;
    }

    public boolean search(int[][] matrix, int target, boolean vertical, int start){
        int lo = start;
        int hi = vertical? matrix.length-1 : matrix[0].length-1;

        while(lo <= hi){
            int mid = (lo + hi)/2;
            if(vertical){
                if(matrix[mid][start] < target) lo = mid+1;
                else if(matrix[mid][start] > target) hi = mid-1;
                else return true;
            }
            else{
                if(matrix[start][mid] < target) lo = mid+1;
                else if(matrix[start][mid] > target) hi = mid-1;
                else return true;
            }
        }

        return false;
    }






    //brute force：直接查
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if(m == 0) return false;
        int n = matrix[0].length;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == target) return true;
            }
        }

        return false;

    }

}
