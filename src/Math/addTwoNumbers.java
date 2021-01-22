package Math;

import java.util.LinkedList;

public class addTwoNumbers {
    private LinkedList result = new LinkedList();

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode first= new ListNode(0);
        ListNode result = first;
        int higher = 0;
        while(l1!=null && l2!=null){
            result.next= new ListNode((l1.val+l2.val+higher)%10);
            higher = l1.val+l2.val+higher>=10?1:0;
            l1=l1.next;
            l2=l2.next;
            result=result.next;
        }
        while (l1!=null){
            result.next= new ListNode((l1.val+higher)%10);
            higher = l1.val+higher>=10?1:0;
            result = result.next;
            l1=l1.next;
        }
        while (l2!=null){
            result.next= new ListNode((l2.val+higher)%10);
            higher =l2.val+higher>=10? 1:0;
            result=result.next;
            l2=l2.next;
        }
        if(higher!=0)
            result.next= new ListNode(higher);
        return first.next;
    }

}

/**
 public math.ListNode math.addTwoNumbers(math.ListNode l1, math.ListNode l2) {
 math.ListNode head = new math.ListNode(0);
 math.ListNode result = head;
 int carry = 0;
 while(l1!=null && l2!=null){
 result.next = new math.ListNode((l1.val+l2.val+carry)%10);
 carry = l1.val+l2.val+carry>=10?1:0;
 l1=l1.next;
 l2=l2.next;
 result = result.next;
 }
 while(l1!=null){
 result.next = new math.ListNode((l1.val+carry)%10);
 carry = l1.val+carry>=10?1:0;
 result = result.next;
 l1=l1.next;
 }
 while(l2!=null){
 result.next = new math.ListNode((l2.val+carry)%10);
 carry = l2.val+carry>=10?1:0;
 result = result.next;
 l2=l2.next;
 }
 if(carry!=0)
 result.next = new math.ListNode(carry);
 return head.next;
 }
 **/


public class ListNode {

    int val;

    ListNode next;


    public ListNode(int x) {
        val = x;
    }
}
/**
 * Definition for singly-linked list.
 * public class math.ListNode {
 *     int val;
 *     math.ListNode next;
 *     math.ListNode(int x) { val = x; }
 * }
 */

