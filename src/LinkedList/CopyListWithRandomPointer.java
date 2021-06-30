package LinkedList;

import java.util.HashMap;
import java.util.Map;

public class CopyListWithRandomPointer {
    // 做法: 新做法, 没有用到recursion, 但思路也是把所有的原来list上的node和对应的copy先记录一比啊安
    // 1. 先用临时指针cur浏览一遍list, 把copyMap放上<cur, copy>;
    // 2. 临时指针指向head, 重新浏览一遍, copy.next = copyMap.get(cur.next); copy.random = copyMap.get(cur.random);

    // Runtime: O(n), Space: O(n);

    Map<Node, Node> copyMap = new HashMap<Node, Node>();
    public Node copyRandomList_reviewed_twice(Node head){
        if(head == null) return null;
        Node cur = head;
        while(cur != null){
            copyMap.put(cur, new Node(cur.val, null, null));
            cur = cur.next;
        }
        cur = head;
        while(cur != null){
            Node copy = copyMap.get(cur);
            copy.next = copyMap.get(cur.next);
            copy.random = copyMap.get(cur.random);
            cur = cur.next;
        }
        return copyMap.get(head);
    }


    // DFS做法

    // 做法： 在方程里做recursion， 新建一个randomMap储存与head对应的， 已经copy好next和random的node
    // 1. 如果randomMap里已经有了head， 直接return 对应的node
    // 2. 否则新建一个new Node(head.val, null, null);, randomMap.put(head, node);
    // 3. 做recursion， 安排node的next和random：node.next = copyRandomList(head.next); node.random = copyRandomList(head.random);

    // Runtime: O(n), Space: O(n)

    public Node copyRandomList(Node head){
        if(head == null) return null;
        if(copyMap.containsKey(head) ) return copyMap.get(head);
        Node node  = new Node(head.val, null, null);
        copyMap.put(head, node);
        node.next = copyRandomList(head.next);
        node.random = copyRandomList(head.random);
        return node;
    }







    
    public Node copyRandomList_reviewed(Node head){
        if(head == null) return null;
        Node dummy = new Node(0, null, null), copyHead = new Node(head.val, null, null);
        dummy.next = copyHead;
        while(head != null){
            Node nextNode = null, randomNode = null;
            if(head.next != null){
                nextNode = new Node(head.next.val, null, null);
            }
            if(head.random != null){
                randomNode = new Node(head.random.val, null, null);
                if(copyMap.containsKey(randomNode)) randomNode.random = copyMap.get(randomNode);
                copyMap.put(copyHead, randomNode);
            }
            copyHead.next = nextNode;
            copyHead.random = randomNode;
            copyHead = copyHead.next;
            head = head.next;
        }
        return dummy.next;
    }



}

