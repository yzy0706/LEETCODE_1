package LinkedList;

import LinkedList.reverse.reverseNodesKGroup;

import java.util.Comparator;
import java.util.PriorityQueue;

public class mergeKSortedLists {
    //dummy的作用主要就是去标记结果的每一步， 然后一步步转成dummy.next， 最后return的是第一个dummy之后的（head.next)

    class com implements Comparator<swapPairs.ListNode> {
        public int compare(swapPairs.ListNode a, swapPairs.ListNode b){
            return a.val - b.val;
        }


    }



    public reverseNodesKGroup.ListNode mergeKLists(reverseNodesKGroup.ListNode[] lists) {
        if(lists.length < 1) return null;
        // if(lists[0] == null) return null;


        PriorityQueue<reverseNodesKGroup.ListNode> queue = new PriorityQueue<reverseNodesKGroup.ListNode>(new com());
        for(reverseNodesKGroup.ListNode l : lists){
            if(l != null)
                queue.offer(l);
        }
        reverseNodesKGroup.ListNode dummy = new reverseNodesKGroup.ListNode(0), head = dummy;
        while(!queue.isEmpty()){
            reverseNodesKGroup.ListNode temp = queue.poll();
            dummy.next = temp;
            temp = temp.next;
            dummy = dummy.next;
            if(temp != null) queue.offer(temp);
        }

        return head.next;



    }
}
