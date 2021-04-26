package LinkedList.reverse;

public class ReverseLinkedList {

    // 做法： 就是基本的reverse linkedList 的算法
    // 用prev记录倒过来的上一位的node， 最开始prev == null, 最后一个prev就是最后一个node， 然后head就变成null了
    // 1. 设置一个whileloop， 当head ！= null的时候往后浏览
    // 2. 在head浏览每一个node的时候：
    //  a. 先用temp记录head.next
    //  b. head.next = prev, 把prev接上
    //  c. prev = node, prev又移动到node这里来
    //  d. head = temp； head跑到之前记录的temp， 也就是之前head还没有接上prev的时候的head.next

    // Runtime: O(n) Space: O(1)

    public ListNode reverseList_reviewed(ListNode head) {
        if(head == null) return head;
        ListNode prev = null;
        while(head != null){
            ListNode temp = head.next;
            head.next = prev;
            prev = head;
            head = temp;
        }
        return prev;
    }



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
