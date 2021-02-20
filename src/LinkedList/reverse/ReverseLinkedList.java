package LinkedList.reverse;

import LinkedList.ListNode;

public class ReverseLinkedList {
    //做法： 就是用基础的reverse linkedlist的算法
    //Runtime: O(n), space: O(1)


    public ListNode reverseList_algorithm(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while(curr != null){
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        return prev;

    }

    //第二种用recursion

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }


}
