package LinkedList;

public class mergeTwoSortedLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        if(l1==null) return l2;
        if(l2==null) return l1;
        if(l1.val<l2.val){
            head=l1;
            l1=l1.next;
        }
        else{
            head=l2;
            l2=l2.next;
        }
        head.next=mergeTwoLists(l1,l2);
        return head;
    }


    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null && l2 == null) return null;
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        ListNode head = new ListNode(0);
        // ListNode dummy = new ListNode(0);
        // dummy.next = head;
        expand(l1,l2,head);
        return head.next;



    }










    //第二种解法
    public void expand_2(ListNode l1, ListNode l2, ListNode head){
        if(l1 == null && l2 == null) return ;

        else {
            if(l1 == null){
                head.next = new ListNode(l2.val);
                head = head.next;
                l2 = l2.next;
                expand(l1,l2,head);
            }
            else if(l2 == null){
                head.next = new ListNode(l1.val);
                l1 = l1.next;
                head = head.next;
                expand(l1,l2,head);

            }
            else if(l1.val >= l2.val){
                head.next = new ListNode(l2.val);
                head = head.next;
                l2 = l2.next;
                expand(l1,l2,head);
            }
            else if(l2.val > l1.val){
                head.next = new ListNode(l1.val);
                head = head.next;
                l1 = l1.next;
                expand(l1,l2,head);
            }

        }

    }

}
