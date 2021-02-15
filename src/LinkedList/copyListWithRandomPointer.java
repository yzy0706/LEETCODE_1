package LinkedList;

import java.util.HashMap;
import java.util.Map;

class Node{
    int val;
    Node next;
    Node random;

    public Node(int val, Node next, Node random){
        this.val = val;
        this.next = next;
        this.random = random;
    }
}

public class copyListWithRandomPointer {
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

