package LinkedList.reverse;

import java.util.Stack;

public class ReorderList {
    // 做法: discussion上给的解法, 先reverse后半部分, 再merge两个部分
    // 1. 先通过快慢指针找到中点slow, 第一部分的末尾prev, prev.next = null;
    // 2. 通过reverse listnode的算法reverse(slow)
    // 3. 合并head和slow

    // Runtime: O(n), Space: O(1)

    public void reorderList_reverse(ListNode head) {
        if(head == null || head.next == null) return;
        ListNode prev = new ListNode(), slow = head, fast = head;
        while(fast != null && fast.next != null){
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null;
        ListNode second = reverse(slow);
        ListNode first = head;
        merge(first, second);
    }

    private void merge(ListNode first, ListNode second){
        while(first != null){
            ListNode temp1 = first.next, temp2 = second.next;
            first.next = second;
            if(temp1 == null) break;
            second.next = temp1;
            first = temp1;
            second = temp2;
        }
    }

    private ListNode reverse(ListNode slow){
        ListNode prev = null, head = slow;
        while(head != null){
            ListNode temp = head.next;
            head.next = prev;
            prev = head;
            head = temp;
        }
        return prev;
    }




    // 做法: 用stack做的
    // 1. 先把所有的node都放到stack里, 记录整个LinkedList的长度cnt
    // 2. 记录当前新的长度len为1, 顺着原来的head, 用temp来记录head.next, 把stack最顶上的node都安插到head后面, 然后head再跳跃到temp, len += 2, 当len == cnt的时候终止whileloop
    // 3. 最后一位, 也就是whileloop结束时的head, head.next = null

    // Runtime: O(n), Space: O(n)
    public void reorderList(ListNode head) {
        if(head == null) return;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        Stack<ListNode> stack = new Stack<>();
        int cnt = 0;
        ListNode p = head;
        while(p != null){
            stack.push(p);
            p = p.next;
            cnt ++;
        }
        int len = 1;
        while(len < cnt){
            ListNode temp = head.next;
            head.next = stack.pop();
            head = head.next;
            head.next = temp;
            head = head.next;
            len += 2;
        }
        head.next = null;
    }
}
