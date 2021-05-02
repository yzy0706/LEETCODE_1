package Greedy;

import java.util.Arrays;
import java.util.HashSet;

public class LineReflection {
    // 做法: HashSet<>的解法, 其实跟我一开始想的hashmap解法的思路差不多,
    // 1. foreach loop这个array points, 以String的形式记录每一个点的x和y值, 并且找到x的最大值和最小值, 他们的和记录为sum
    // 2. 再foreach loop一遍, 如果对于某一个点来说, set里找不到 (sum - point[0]) + “ ” + point[1], 那么证明当前的point就没有对称的点, return false;

    // Runtime: O(n), Space: O(n);

    public boolean isReflected_set(int[][] points) {
        if(points.length == 1) return true;
        HashSet<String> set = new HashSet<>();
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for(int[] point : points){
            min = Math.min(min, point[0]);
            max = Math.max(max, point[0]);
            set.add(point[0] + " " + point[1]);
        }
        // System.out.println(min + " " + max);
        int sum = min + max;
        for(int[] point : points){
            if(!set.contains((sum - point[0]) + " " + point[1])) return false;
        }
        return true;
    }




    // 做法: 这是第一种sort + twopointer的做法
    // 1. 高度相同的点按照x的大小排列, 高度不同的按照高度排列, 并且找出这个array的最大的x值和最小的x值
    // 2. 按照最小值和最大值找出整个数列的对称轴line
    // 3. 设置两个pointer l和r, 从0开始:
    //      a. r每次都移动到当前高度相等的点的最后一个
    //      b. 用i标记l, j标记r, 然后用i, j反向two pointer来一个个检查当前的l到r的点们是不是都对称(i, j都要先移动过相同的点)
    //      c. 检查完以后没问题那么r++, l = r;

    // Runtime: O(nlog(n)), Space: O(1);

    public boolean isReflected_sort(int[][] points) {
        int len = points.length;
        if(len == 1) return true;
        Arrays.sort(points, (a, b) -> {
            if(a[1] == b[1]) return a[0] - b[0];
            return a[1] - b[1];
        });
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for(int[] point : points){
            min = Math.min(min, point[0]);
            max = Math.max(max, point[0]);
        }
        // System.out.println(max + " " + min);
        double line = (double)(max + min)/2;
        int l = 0, r = 0;
        while(r < len){
            while(r + 1 < len && points[r+1][1] == points[r][1]) r++;
            if(l == r && points[l][0] != line) return false;
            int i = l, j = r;
            while(i <= j){
                while(i < j && points[i][0] == points[i+1][0] && points[i][1] == points[i+1][1]) i++;
                while(i < j && points[j][0] == points[j-1][0] && points[j][1] == points[j-1][1]) j--;
                double mid = (double)(points[i][0] + points[j][0]) / 2;
                if(mid != line) return false;
                i++;
                j--;
            }
            r++;
            l = r;
        }
        return true;
    }





    public boolean isReflected(int[][] points) {
        int len = points.length;
        if(len == 1) return true;
        Arrays.sort(points, (a, b) -> (a[0] - b[0]));
        int l = 0, r = len - 1;
        int line = len % 2 == 0 ? (points[l][0] + points[r][0])/2 : points[(l + r)/2][0];
        while(l <= r){
            if(points[l][1] != points[r][1]) return false;
            int curL = (points[l][0] + points[r][0])/2;
            if(curL != line) return false;
            l ++;
            r --;
        }
        return true;
    }


    public boolean isReflected_2(int[][] points) {
        int len = points.length;
        if(len == 1) return true;
        Arrays.sort(points, (a, b) -> (a[0] - b[0]));
        int l = 0, r = len - 1;
        int line = (points[l][0] + points[r][0])/2;
        while(l <= r){
            if(points[l][1] != points[r][1]) return false;
            int curL = (points[l][0] + points[r][0])/2;
            if(curL != line) return false;
            // System.out.println(l + " " + r);
            while(l < r && points[l+1][0] == points[l][0] && points[l+1][1] == points[l][1]) l ++;
            while(l < r && points[r-1][0] == points[r][0] && points[r-1][1] == points[r][1]) r --;
            // System.out.println(points[r-2][0] + " " + points[r-2][1]);
            l ++;
            r --;
        }
        return true;
    }
}
