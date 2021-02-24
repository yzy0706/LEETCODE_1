package Backtrack;

public class swapPairs {
    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      public ListNode(int val) { this.val = val; }
      public ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode dummy1 = head;
        ListNode dummy2 = dummy1.next;

        dummy1.next = swapPairs(dummy2.next);
        dummy2.next = dummy1;

        return dummy2;

//             dummy1.next = dummy2;
//             reverse(head,dummy1,dummy2);
// //             while(head.next != null){
// //                 dummy1 = new ListNode(head.next.val);
// //                 dummy1 = dummy1.next;
// //                 dummy1 = dummy1.next;
// //                 dummy2.next = new ListNode(head.val);

//             }

    }
}
