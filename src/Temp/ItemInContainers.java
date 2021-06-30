package Temp;

import java.util.ArrayList;
import java.util.List;

public class ItemInContainers {
    // 做法：简单的string问题
    // 1. 记录每一个'|'的位置之前有多少个'*'
    // 2. 根据startIndices和endIndices的每一对start和end（都要-1， 因为list里面不是0-indexed）
    //   a. 把start和end都分别移动到右边和左边的'|'
    //   b. ans.add(mem[end] - mem[start]);

    // Runtime: O(n), Space: O(n)

    public static List<Integer> numberOfItems(String s, List<Integer> startIndices, List<Integer> endIndices) {
        int[] mem = new int[s.length()];
        int count = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '|') {
                mem[i] = count;
            } else {
                ++count;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < startIndices.size(); ++i) {
            int start = startIndices.get(i) - 1;
            int end = endIndices.get(i) - 1;

            while (s.charAt(start) != '|') ++start;
            while (s.charAt(end) != '|') --end;

            ans.add(mem[end] - mem[start]);
        }
        return ans;
    }
}
