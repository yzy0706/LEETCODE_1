package LinkedList.reverse;

public class ReverseLinkedListII {
    // 做法: 跟reverseLinkedList 差不多
    // 1. 移动prev1和cur1到m :前面加了一个prev1和cur1一起移动到 cur1的位置正好是第m个数, prev1在他前面的步骤; 而且要建立一个dummy在head前面
    // 2. reverse: 到了 cur1 = 第m个node的时候, 用一个cur2承载cur1, prev2承载prev1, 然后在[m, n]之间forloop进行正常的reverse操作
    // 3. 结束了reverse以后, prev1.next = prev2; cur1.next = cur2;  然后直接return dummy.next

    // Runtime: O(n), Space: O(1);
    class Solution {
        public ListNode reverseBetween(ListNode head, int m, int n) {
            ListNode dummy = new ListNode(0);
            dummy.next = head;
            ListNode cur1 = dummy;
            ListNode prev = null;

            for (int i = 0; i < m; i++) {
                prev = cur1;
                cur1 = cur1.next;
            }

            ListNode prev2 = prev;
            ListNode cur2 = cur1;


            //正常的reverse
            for (int i = m; i <= n; i++) {
                ListNode tmp = cur2.next;
                cur2.next = prev2;
                prev2 = cur2;
                cur2 = tmp;
            }


            prev.next = prev2;
            cur1.next = cur2;


            return dummy.next;
        }
    }



    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
