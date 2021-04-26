package LinkedList;


import java.util.PriorityQueue;

public class MergeKSortedLists {

    //做法: 用pq来做的, 相当于是一个加上pq的bfs
    // 1. 当!pq.isEmpty()的时候:
    // a. 用pq装载lists里所有的ListNode, 每次都把val最小的ListNode poll()出来加到head的后面, head = head.next;
    // b. 如果当前cur.next != null; 继续把cur.next放到pq里
    // Runtime: O(nlog(n)), Space: O(nlog(n)); n是lists的大小

    public ListNode mergeKLists_reviewed(ListNode[] lists) {
        int len = lists.length;
        if(len < 1) return null;
        ListNode dummy = new ListNode(0), head = dummy;
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a,b) -> a.val - b.val);
        for(ListNode n : lists) if(n != null) pq.offer(n);
        while(!pq.isEmpty()){
            ListNode cur = pq.poll();
            head.next = cur;
            head = head.next;
            if(cur.next != null) pq.offer(cur.next);
        }
        return dummy.next;
    }







    //dummy的作用主要就是去标记结果的每一步， 然后一步步转成dummy.next， 最后return的是第一个dummy之后的（head.next)



    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length < 1) return null;
        // if(lists[0] == null) return null;


        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>((a, b) -> (a.val - b.val));
        for(ListNode l : lists){
            if(l != null)
                queue.offer(l);
        }
        ListNode dummy = new ListNode(0), head = dummy;
        while(!queue.isEmpty()){
            ListNode temp = queue.poll();
            dummy.next = temp;
            temp = temp.next;
            dummy = dummy.next;
            if(temp != null) queue.offer(temp);
        }

        return head.next;



    }
}
