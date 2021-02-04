package TwoPointer;

import Math.ListNode;

public class removeNthNodeFromEnd {
    /**
     * Definition for singly-linked list.
     * public class math.ListNode {
     *     int val;
     *     math.ListNode next;
     *     math.ListNode(int x) { val = x; }
     * }
     */


        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode node  = new ListNode(0);
            node.next = head;
            head =  node;
            ListNode mark = null;
            int i = 0;
            while(node.next!=null){
                node =node.next;
                i++;
                if(i==n){
                    mark = head ;
                }
                if(i > n && node != null){
                    mark=mark.next;
                }
            }

            if (mark != null&& mark.next != null) mark.next = mark.next.next;
            return head.next ;

        }
}
