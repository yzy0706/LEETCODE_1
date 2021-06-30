package DataDesign;

import java.util.LinkedList;

public class DesignCircularQueue {
    // 做法: 第二遍用two pointer的做法, 就是first到last的最大距离是k, last和 first一直在int[]上循环移动, 用一个parameter num记录当前queue的数量
    // 1. 判断queue里面的数量就用num == 0; num == k来判断
    // 2. 如果要删东西, first = num == 1 ? last : (first + 1) % k; num --;
    // 3. 如果要添东西, last = num == 0 ? last : (last + 1) % k;  num ++;

    // Runtime: O(1), Space: O(n)

    int[] main;
    int first, last, num, k;

    public void MyCircularQueue_2(int k) {
        this.main = new int[k];
        this.k = k;
        this.first = 0;
        this.last = 0;
        this.num = 0;
    }

    public boolean enQueue_2(int value) {
        if(isFull()) return false;
        last = (num == 0) ? last : (last + 1) % k;
        main[last] = value;
        num ++;
        return true;
    }

    public boolean deQueue_2() {
        if(isEmpty()) return false;
        first = num == 1 ? last : (first + 1) % k;
        num --;
        return true;
    }

    public int Front_2() {
        return num == 0 ? -1 : main[first];
    }

    public int Rear_2() {
        return num == 0 ? -1 : main[last];
    }

    public boolean isEmpty_2() {
        return num == 0;
    }

    public boolean isFull_2() {
        return num == k;
    }




    // 做法: 我这里用了两个LinkedList, 一个表示当前的queue, 一个表示当前的queue前面的那个存储val的queue, 可能用ListNode更好
    // 注意: 错误的点是每次dequeue()以后前面储存val的那个queue并不会补上来, 而是继续正常的删减

    // Runtime: O(n), Space: O(n)
    LinkedList<Integer> main1, backup;
    int max;

    public void MyCircularQueue(int k) {
        max = k;
        main1 = new LinkedList<>();
        backup = new LinkedList<>();
    }

    public boolean enQueue(int value) {
        if(isFull()){
            backup.add(value);
            return false;
        }
        else{
            main1.add(value);
        }
        return true;
    }

    public boolean deQueue() {
        if(isEmpty()) return false;
        main1.poll();
        // if(!backup.isEmpty()) main.add(backup.pollFirst());
        return true;
    }

    public int Front() {
        return isEmpty() ? -1 : main1.peek();
    }

    public int Rear() {
        return isEmpty() ? -1 : main1.peekLast();
    }

    public boolean isEmpty() {
        return main1.isEmpty() && backup.isEmpty();
    }

    public boolean isFull() {
        return main1.size() == max;
    }
}
