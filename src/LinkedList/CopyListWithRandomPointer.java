package LinkedList;

import java.util.HashMap;
import java.util.Map;

public class CopyListWithRandomPointer {
    // 做法： 在方程里做recursion， 新建一个randomMap储存与head对应的， 已经copy好next和random的node
    // 1. 如果randomMap里已经有了head， 直接return 对应的node
    // 2. 否则新建一个new Node(head.val, null, null);, randomMap.put(head, node);
    // 3. 做recursion， 安排node的next和random：node.next = copyRandomList(head.next); node.random = copyRandomList(head.random);

    // Runtime: O(n), Space: O(n)

    Map<Node, Node> randomMap = new HashMap<Node, Node>();

    public Node copyRandomList(Node head){
        if(head == null) return head;
        if(randomMap.containsKey(head) ) return randomMap.get(head);
        Node node  = new Node(head.val, null, null);
        randomMap.put(head, node);
        node.next = copyRandomList(head.next);
        node.random = copyRandomList(head.random);
        return node;
    }


}

