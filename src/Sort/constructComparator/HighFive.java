package Sort.constructComparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HighFive {
    // 做法: 用的sort, 先把成绩按照同学id从小到大排列, 相同同学的成绩从大到小排列, 然后再一个个处理每个同学的成绩
    // Runtime: O(nlog(n)), Space: O(n)

    public int[][] highFive(int[][] items) {
        Arrays.sort(items, (a, b) -> {
            if(a[0] == b[0]) return b[1] - a[1];
            return a[0] - b[0];
        });
        int i = 0;
        List<int[]> res = new ArrayList<>();
        while(i < items.length){
            int sum = 0, id = items[i][0], end = i + 5;
            while(i < items.length && items[i][0] == id && i < end) sum += items[i++][1];
            res.add(new int[]{id, sum/5});
            while(i < items.length && items[i][0] == id) i++;
        }
        int[][] ans = new int[res.size()][2];
        int pos = 0;
        for(int[] stu : res) ans[pos++] = stu;
        return ans;
    }
}
