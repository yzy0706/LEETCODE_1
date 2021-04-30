package Temp;

public class CopyListWIthRandomPointer {
    //map和whileloop
    // 做法: 做法主要是在第一个whileloop中用一个map来记录所有的当前的node和一个只和当前node的val相同但是next和random都是null的map
    //cur归回为head之后， 在第二个whileloop中我们把map里每一个node的next和random都赋值为Map.get(cur.next)或者Map.get(cur.random)就好了，
    //这样我们可以把所有的map里的value都由于cur的中介关系相互牵扯上random和next的关系
    //Runtime： O(n), space也是map的大小O(n）
//
//    HashMap<Node, Node> randomMap = new HashMap<Node, Node>();
//
//    public Node copyRandomList_whileloop(Node head){
//        Node cur = head;
//        while(cur != null){
//            randomMap.put(cur, new Node(cur.val, null, null));
//            cur = cur.next;
//        }
//        cur = head;
//        while(cur != null){
//            randomMap.get(cur).random = randomMap.get(cur.random);
//            randomMap.get(cur).next = randomMap.get(cur.next);
//            cur = cur.next;
//        }
//
//        return randomMap.get(head);
//    }

    //leetcode上的recursion解法
//    class Node {
//        int val;
//        Node next;
//        Node random;
//
//        public Node(int val, Node next, Node random) {
//            this.val = val;
//            this.next = next;
//            this.random = random;
//        }
//    }
//
//    randomMap = new HashMap<Node, Node>();
//
//    public Node copyRandomList(Node head){
//        if(head == null) return head;
//        if(randomMap.containsKey(head) ) return randomMap.get(head);
//        Node node  = new Node(head.val, null, null);
//        randomMap.put(head, node);
//        node.next = copyRandomList(head.next);
//        node.random = copyRandomList(head.random);
//        return node;
//    }
}
