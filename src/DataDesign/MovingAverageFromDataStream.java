package DataDesign;

import java.util.LinkedList;

public class MovingAverageFromDataStream {
    // 做法: 因为linkedlist可以同时修改开头和结尾的元素, 所以我用linkedList做的
    // Runtime: O(n), Space: O(n)

    LinkedList<Integer> list;
    int curSum = 0, maxSize = 0;
    /** Initialize your data structure here. */
    public void MovingAverage(int size) {
        list = new LinkedList<>();
        maxSize = size;
    }

    public double next(int val) {
        list.addLast(val);
        curSum += val;
        if(list.size() > maxSize){
            int first = list.removeFirst();
            curSum -= first;
        }
        int len = list.size();
        if(len == 0) return 0;
        return (double)curSum / len;
    }
}
