package TwoPointer.sameDirection;

public class RemoveNthNode {
    // 做法： 相当于是一个滑动窗口,
    // 1. rMark先从dummy起步走n+1步， 所以lMark和rMark中间隔了n个node，
    // 2. lMark和rMark一起往后走， 那么当rMark = null的时候， lMark正好是倒数第n+1个
    // 3. lMark.next = lMark.next.next; 把倒数第n个跳过

    //Runtime: O(n), Space: O(1)
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0), lMark = dummy, rMark = dummy;
        dummy.next = head;

        for(int i = 0 ; i <= n ; i++){
            rMark = rMark.next;
        }

        while(rMark != null){
            lMark = lMark.next;
            rMark = rMark.next;
        }

        lMark.next = lMark.next.next;
        return dummy.next;




    }
}
