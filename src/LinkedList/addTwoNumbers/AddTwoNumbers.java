package LinkedList.addTwoNumbers;


public class AddTwoNumbers {
    //做法: 因为是两个listnode已经reverse了, 所以只要单纯的让两个listnode的val相加再加上cnt, 用cur来记录结果,并且用cnt记录下进位就可以了
    // 1. 当l1和l2都不是null的时候, 一直加上两个node的val + cnt, 判断一下他们的和是不是 >= 10, 是的话要进位且val = val % 10;
    // 2. 出了上一个whileloop, 跑完l1或者l2里面不是null的那个
    // 3. 如果现在cnt还 == 1, 证明最后还进了一位, cur.next = new ListNode(1);

    // Runtime: O(n), Space: O(n);


    public ListNode addTwoNumbers_reviewed(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0), cur = new ListNode(0);
        dummy.next = cur;
        int cnt = 0;
        while(l1 != null && l2 != null){
            int val = l1.val + l2.val + cnt;
            cnt = 0;
            if(val >= 10){
                val = val % 10;
                cnt = 1;
            }
            cur.val = val;
            l1 = l1.next;
            l2 = l2.next;
            if(l1 == null || l2 == null) break;
            cur.next = new ListNode(0);
            cur = cur.next;
        }
        // System.out.println(l2);
        while(l1 != null){
            int val = l1.val + cnt;
            cnt = 0;
            if(val >= 10){
                val = val % 10;
                cnt = 1;
            }
            cur.next = new ListNode(val);
            cur = cur.next;
            l1 = l1.next;
        }
        while(l2 != null){
            int val = l2.val + cnt;
            cnt = 0;
            if(val >= 10){
                val = val % 10;
                cnt = 1;
            }
            cur.next = new ListNode(val);
            cur = cur.next;
            l2 = l2.next;
        }

        if(cnt == 1){
            cur.next = new ListNode(1);
        }
        return dummy.next;
    }




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
