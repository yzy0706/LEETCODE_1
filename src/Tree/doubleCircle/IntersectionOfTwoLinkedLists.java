package Tree.doubleCircle;

import java.util.HashSet;
import java.util.Set;

public class IntersectionOfTwoLinkedLists {
    //做法: 跟1650是同样的双循环做法, 先跑一个Node跑到底, 再跑另一个Node, return第一个重复的
    //Runtime: O(n), 因为跑第一个重复的路径的时候就会return, 所以最多跑O(n), Space: O(n)

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> visited = new HashSet<>();

        while(headA != null){
            if(visited.contains(headA)) return headA;
            visited.add(headA);
            headA = headA.next;
            ListNode temp = headA;
            headA = headB;
            headB = temp;
        }

        while(headB != null){ //headB还没跑完的话就继续跑, 跑到headA跑过的路径为止
            if(visited.contains(headB)) return headB;
            headB = headB.next;
        }

        return null;
    }


}
