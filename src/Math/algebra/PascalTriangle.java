package Math.algebra;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {
    // 做法: 类似dp
    // 1. 直接建立res, 一共建立numRows层, 每一层的长度从1开始叠加
    // 2. 第一层就是1, 从第二层开始, 除了开头和末尾都是1, 其它位置都是上一层的upLevel.get(j-1) + upLevel.get(j);

    // Runtime: O(n^2), Space: O(1)


    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if(numRows < 1) return res;
        int len = 1;
        List<Integer> first = new ArrayList<>();
        first.add(1);
        res.add(first);
        for(int i = 1; i < numRows; i++){
            len ++;
            List<Integer> curLevel = new ArrayList<>(), upLevel = res.get(i - 1);
            curLevel.add(1);
            for(int j = 1; j < len - 1; j ++){
                int last = upLevel.get(j-1);
                int next = upLevel.get(j);
                curLevel.add(last + next);
            }
            curLevel.add(1);
            res.add(curLevel);
        }
        return res;
    }
}
