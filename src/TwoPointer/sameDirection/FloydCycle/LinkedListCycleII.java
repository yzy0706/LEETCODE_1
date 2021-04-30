package TwoPointer.sameDirection.FloydCycle;

public class LinkedListCycleII {
    // 做法: 用到了Floyd's Cycle Detection Algorithm
    // 1. 先把fast和slow都指向head, 当fast和fast.next都不是null的时候,
    // fast每次走两步, slow走一步, 一直到fast == slow, 如果fast或者fast.next是null的话证明到头了, 直接return null, 代表没有cycle, 也没有接入点
    // 2. 当fast == slow的时候, fast调回到head, 然后fast和slow用一样的速度跑, 当fast和slow是一样的时候就return slow, 这时候的交汇点就是cycle的起始点
    // Runtime: O(n), LinkedList的长度, Space: O(1)

    public ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(slow == fast){
                fast = head;
                while(slow != fast){
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }


}
