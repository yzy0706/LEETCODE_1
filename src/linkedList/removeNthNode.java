package linkedList;

import math.ListNode;

public class removeNthNode {
    public ListNode removeNthFromEnd(ListNode head, int n) {


        ListNode dummy = new ListNode(0), lMark = dummy, rMark = dummy;
        dummy.next = head;

        for(int i = 0 ; i <= n ; i++){
            rMark = rMark.next;
        }

        // ListNode cur = head.next;
        // head.next = rMark;
        // rMark.next = cur;

        while(rMark != null){
            lMark = lMark.next;
            rMark = rMark.next;
        }

        lMark.next = lMark.next.next;
        return dummy.next;




    }
}
