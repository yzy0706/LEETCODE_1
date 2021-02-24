package Math.addTwoNumbers;

import java.util.Stack;

public class AddTwoNumbersII {
    // 做法: 做法主要就是用stack做的
    // 1. stack会把最后一位存储起来放在stack1并且等待两外一个长一点的把所有数字都放在stack2里
    // 2. 再把两个stack里面的数字由上而下两两相加, 在这里要注意ListNode从后往前建立的方法
    // 3. 如果到最后cnt还大于0, 代表最后两个数之和大于10, 所以还要多一位, 所以最后一个cur.val = cnt;

    // Runtime: O(n), l1, l2里面比较长的那个; Space: O(n)
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack<>(), stack2 = new Stack<>();

        while(l1 != null || l2 != null){
            if(l1 != null){
                stack1.push(l1.val);
                l1 = l1.next;
            }
            if(l2 != null){
                stack2.push(l2.val);
                l2 = l2.next;
            }
        }

        int cnt = 0;
        ListNode cur = new ListNode(0);

        while(!stack1.isEmpty() || !stack2.isEmpty()){
            int val1 = !stack1.isEmpty()? stack1.pop() : 0;
            int val2 = !stack2.isEmpty()? stack2.pop() : 0;
            int sum = val1 + val2 + cnt;
            if(cnt > 0) {
                cnt--;
            }
            if(sum >= 10){
                sum %= 10;
                cnt ++;
            }
            cur.val = sum;
            ListNode pre = new ListNode(0);
            pre.next = cur;
            cur = pre;
        }

        if(cnt > 0) cur.val += cnt;
        return cur.val == 0 ? cur.next : cur;
    }







    //自己写的， 想先reverse再直接add， 写的有点复杂, 而且
    public ListNode addTwoNumbers_1(ListNode l1, ListNode l2) {
        ListNode pre1 = null, cur1 = l1, pre2 = null, cur2 = l2;
        while(cur1 != null){
            ListNode temp = cur1.next;
            cur1.next = pre1;
            pre1 = cur1;
            cur1 = temp;
        }
        while(cur2 != null){
            ListNode temp = cur2.next;
            cur2.next = pre2;
            pre2 = cur2;
            cur2 = temp;
        }
        int cnt = 0;
        ListNode tail1 = pre1, tail2 = pre2;
        ListNode dummy = new ListNode(0), head = dummy;

        while(tail1 != null && tail2 != null){
            int val = tail1.val + tail2.val + cnt;
            if(cnt > 0) cnt --;
            if(val >= 10){
                cnt ++;
                val %= 10;
            }
            head.next = new ListNode(val);
            head = head.next;
            tail1 = tail1.next;
            tail2 = tail2.next;
        }

        while(tail1 != null){
            int val = tail1.val + cnt;
            if(cnt > 0) cnt --;
            if(val >= 10){
                cnt ++;
                val %= 10;
            }
            head.next = new ListNode(val);
            head = head.next;
            tail1 = tail1.next;
        }

        while(tail2 != null){
            int val = tail2.val + cnt;
            if(cnt > 0) cnt --;
            if(val >= 10){
                cnt ++;
                val %= 10;
            }
            head.next = new ListNode(val);
            head = head.next;
            tail2 = tail2.next;
        }

        ListNode pre = null, cur = dummy.next;
        while(cur != null){
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return dummy.next;
    }
}
