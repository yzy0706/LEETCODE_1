package TwoPointer.sameDirection.FloydCycle;

public class LinkedListCycle {
    // 做法: 就是用floyd的快慢指针, 但是去掉了第一次重合以后兔子就归零那一步, 因为我们不需要找环的接入点在哪里
    // Runtime: O(n), Space: O(1)
    public boolean hasCycle_duplicateOnly(ListNode head) { //龟兔重合的话就直接return true；
        if (head == null) return false;
        ListNode turtle = head, rabbit = head;
        while (rabbit != null && rabbit.next != null) {
            turtle = turtle.next;
            rabbit = rabbit.next.next;
            if (turtle == rabbit) return true;
        }
        return false;
    }


        public boolean hasCycle (ListNode head){ //检查兔子有没有到头
            if (head == null) return false;
            ListNode turtle = head, rabbit = head;
            while (rabbit != null && rabbit.next != null) {
                turtle = turtle.next;
                rabbit = rabbit.next.next;
                if (turtle == rabbit) break;
            }
            return rabbit != null && rabbit.next != null;
        }
    }
