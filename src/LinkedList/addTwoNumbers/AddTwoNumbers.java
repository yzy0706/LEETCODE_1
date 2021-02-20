package LinkedList.addTwoNumbers;

import Math.ListNode;

public class AddTwoNumbers {
    //做法： 已经被反过来了所以其实很简单
    public ListNode addTwoNumbers (ListNode l1 , ListNode l2){
        ListNode dummy = new ListNode(0);
        ListNode track = dummy;
        int val1 = 0;
        int val2 = 0;
        int cnt = 0;

        while(l1 != null || l2 != null){
            if(l1 == null) val1 = 0;
            else val1 = l1.val;
            if(l2 == null) val2 = 0;
            else val2  = l2.val;

            int val = val1 + val2  + cnt;

            ListNode node = new ListNode(val%10);

            track.next = node;
            track = node;

            if(val >= 10) cnt = 1;
            else cnt = 0;

            if( l1 != null) l1 = l1.next;
            if( l2 != null) l2 = l2.next;
        }
        if( cnt != 0) track.next = new ListNode(cnt);
        return dummy.next;
    }

}
