package LinkedList.reverse;

import java.util.ArrayDeque;
import java.util.Deque;

public class reverseNodesKGroup {


    //第一遍自己写的
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || head.next == null || k <= 1) return head;
        Deque<Integer> stack = new ArrayDeque<>(k);
        ListNode dummy = head;
        // ListNode tail = dummy.next;
        for(int i = 0 ; i < k ; i++){
            stack.push(head.val);
            head = head.next;
        }

        for(int i = 0; i < k; i++){
            int cur = stack.pop();
            dummy.next = new ListNode(cur);
            dummy = dummy.next;
        }

        dummy.next = reverseKGroup(dummy,k);
        return dummy;
    }





    //一个印度人的写法
    public ListNode reverseKGroup_2(ListNode head, int k) {
        if(head == null || head.next == null || k == 1 || k== 0) return head;

        ListNode kthNode, kthNodeNext;
        ListNode finalHead = new ListNode(0);
        ListNode prevTail = finalHead;
        // Listnode lastFinal = finalHead;

        while(head != null && head.next != null){

            ListNode currTail = head;
            kthNode = getKthNode(head, k);
            if(kthNode == null) break;
            kthNodeNext = getKthNode(head,k+1);
            // kthNodeNext = kthNode.next;
            // head = kthNodeNext;
            ListNode currHead = reverseKNodes(head,k);
            prevTail.next = currHead;
            currTail.next = kthNodeNext;
            head = kthNodeNext;
            prevTail = currTail;

        }

        return finalHead.next;

    }

    public ListNode getKthNode(ListNode head, int k){
        ListNode kthNode = head;
        for(int i = 1; i < k ;i++){
            if(kthNode == null) return null;
            kthNode = kthNode.next;
        }
        return kthNode;
    }


    public ListNode reverseKNodes(ListNode head, int k){
        if(head == null || head.next == null || k == 1 || k == 0){
            return head;
        }

        ListNode dummy = head.next;
        ListNode finalHead = reverseKNodes(head.next,k-1);
        dummy.next = head;
        head.next = null;


        return finalHead;

    }




    //
}
