package Array.string;

import java.util.ArrayList;
import java.util.List;

public class PartitionLabels {
    // 做法: 类似于一种greedy的方法
    // 1. 用一个int[] l = new int[26]; 记录S上每一个char最后出现的位置
    // 2. 再Forloop一遍S, anchor = 0, maxLast = 0; 分别代表当前这一段截取的起点和之前浏览过的所有char最后出现的位置
    //      a. 根据每一个char :  curMaxLast = Math.max(curMaxLast, l[cl[i] - 'a']);
    //      b. 如果i == curMaxLast: res.add(curMaxLast - anchor + 1); anchor = i + 1;
    // Runtime: O(n), Space: O(n)

    public List<Integer> partitionLabels(String S) {
        List<Integer> res = new ArrayList<>();
        if (S.length() < 1) return res;
        int[] l = new int[26];
        char[] cl = S.toCharArray();
        for (int i = 0; i < cl.length; i++) {
            l[cl[i] - 'a'] = i; // last appearance of characte
        }
        int curMaxLast = 0, anchor = 0;
        for(int i = 0; i < cl.length; i++){
            curMaxLast = Math.max(curMaxLast, l[cl[i] - 'a']); // find the last appearance of every char
            if (curMaxLast == i) {
                res.add(curMaxLast - anchor + 1);
                anchor = i + 1;
            }
        }
        return res;
    }
}
