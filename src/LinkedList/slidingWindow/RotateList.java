package LinkedList.slidingWindow;

public class RotateList {
    //做法： 也是相当于一个滑动窗口的做法去截断后半段长度为（len - （k % len)）的链表， 并把他们挪到前面来
    // 1. 用dummy.next记录一开始链表的头， 记录链表长度len， 记录后半段长度remainder = k % len;
    // 2. 用head1记录后半段链表的头， prev记录前半段链表的尾， 挪动remainder下， prev.next = null;
    // 3. dummy1.next = head1; , head1挪到链表尾部， head1.next = dummy.next; , return dummy1.next;
    // Runtime: O(n), Space: O(n)

    public ListNode rotateRight(ListNode head, int k) {
        if(head == null){
            return null;
        }
        if(k == 0){
            return head;
        }
        ListNode dummy = new ListNode();
        dummy.next = head;
        int len = 0;
        while(head != null){
            len ++;
            head = head.next;
        }
        ListNode head1 = dummy.next, prev = dummy;
        int remainder = k % len, cnt = 1;
        if(remainder == 0){
            return head1;
        }
        while(cnt <= len - remainder){
            ListNode temp = head1;
            head1 = head1.next;
            prev = temp;
            cnt ++;
        }
        prev.next = null;
        ListNode dummy1 = new ListNode();
        dummy1.next = head1;
        while(head1.next != null){
            head1 = head1.next;
        }
        head1.next = dummy.next;
        return dummy1.next;
    }
}
