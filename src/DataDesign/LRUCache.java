package DataDesign;

import java.util.HashMap;

public class LRUCache {
    // 第三遍用了Node, 直接在LinkedList中改变自己前后的Node就是O(1)
    //做法: 这道题就是用了一个最开始由head和tail组成的LinkedList来记录所有cache的出现顺序, 用HashMap记录他们的key和对应的Node
    // 1. 建立class Node, 里面的变量有他的prev, next, key;
    // 2. 建立两个可以改变linkedlist的helper:
    //      a. void insert(node) {可以把当前的node塞到head后面, 就代表最近用到的那个node; map里面添加key,node}
    //      b. void remove(node) {把当前node前面和后面相连, 当前的node直接消失; map里面remove(key)
    // 3. 初始化class, 记得要把head和tail先相连, 不然后面会有null pointer的错误
    // 4. 建立get(key)方程:
    //      a. 如果map里有key, 把当前对应key的cur提取出来, remove(cur); 再 insert(cur); cur就变成了最近使用的那个, return cur.val;
    //      b. 如果没有key直接return -1;
    //  5. 建立put(key)方程:
    //      a. 如果map里有key, remove(cur); 再 insert(cur); cur就变成了最近使用的那个
    //      b. 如果map的size达到最大值max了, remove(tail.prev); 就是LRU, 然后insert(cur)
    //      c. 以上情况都不是, 就直接insert(cur);


    // 注意: 插入一个node的时候记得next和prev都要设置
    // Runtime: O(1), Space: O(n)

    class Node{
        Node next, prev;
        int key, val;

        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }

    HashMap<Integer, Node> map = new HashMap<>();
    Node head = new Node(0, 0);
    Node tail = new Node(0, 0);
    int max;

    public LRUCache(int capacity) {
        max = capacity;
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if(map.containsKey(key)){
            Node cur = map.get(key);
            remove(cur);
            insert(cur);
            return cur.val;
        }
        return -1;

    }

    public void put(int key, int value) {
        Node cur = new Node(key, value);
        if(map.containsKey(key)){
            Node last = map.get(key);
            remove(last);
            insert(cur);
        }
        else if(map.size() == max){
            Node LRU = tail.prev;
            remove(LRU);
            insert(cur);
        }
        else{
            insert(cur);
        }
    }

    public void insert(Node node){
        Node headNext = head.next;
        head.next = node;
        node.prev = head;
        node.next = headNext;
        headNext.prev = node;
        map.put(node.key, node);
    }

    public void remove(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
        map.remove(node.key);
    }


//
//    //第二遍用pq来排列使用的时间， 用int time来计时， 过了但是很慢
//
//    HashMap<Integer, int[]> map;
//    PriorityQueue<Integer> pq;
//    int size, time;
//
//    public LRUCache(int capacity) {
//        map = new HashMap<>();
//        pq = new PriorityQueue<>((a, b) -> map.get(a)[0] - map.get(b)[0]);
//        size = capacity;
//        time = 0;
//    }
//
//    public int get(int key) {
//        if(!map.containsKey(key)) return -1;
//        time ++;
//        map.get(key)[0] = time;
//        pq.remove(key);
//        pq.offer(key);
//        return map.get(key)[1];
//    }
//
//    public void put(int key, int value) {
//        time ++;
//        if(map.containsKey(key)){
//            map.get(key)[0] = time;
//            map.get(key)[1] = value;
//            pq.remove(key);
//            pq.offer(key);
//            return;
//        }
//        if(map.size() == size){
//            int LRU = pq.poll();
//            map.remove(LRU);
//            pq.remove(LRU);
//        }
//        map.put(key , new int[]{time, value});
//        pq.offer(key);
//    }






//    // 第一遍想用linkedlist做, 但没法根据一个node来删改（因为没建立class Node)
//    LinkedList<Integer> useRecord;
//    int size;
//
//    public LRUCache(int capacity) {
//        useRecord = new LinkedList<>();
//        map = new HashMap<>();
//        size = capacity;
//    }
//
//    public int get(int key) {
//        while(useRecord.size() > 0 && (useRecord.peekLast() == key || !map.containsKey(useRecord.peekLast()))) useRecord.removeLast();
//        if(!map.containsKey(key)) return -1;
//        if(useRecord.size() == 0 || useRecord.peekFirst() != key) useRecord.addFirst(key);
//        return map.get(key);
//    }
//
//    public void put(int key, int value) {
//        if(map.containsKey(key)){
//            map.replace(key, value);
//            while(useRecord.size() > 0 && (useRecord.peekLast() == key || !map.containsKey(useRecord.peekLast()))) useRecord.removeLast();
//            if(useRecord.size() == 0 || useRecord.peekFirst() != key) useRecord.addFirst(key);
//            return;
//        }
//        if(map.size() == size){
//            while(!map.containsKey(useRecord.peekLast())) useRecord.removeLast();
//            int LRU = useRecord.removeLast();
//            map.remove(LRU);
//            map.put(key, value);
//            if(useRecord.size() == 0 || useRecord.peekFirst() != key) useRecord.addFirst(key);
//            return;
//        }
//        while(useRecord.size() > 0 && (useRecord.peekLast() == key || !map.containsKey(useRecord.peekLast()))) useRecord.removeLast();
//        map.put(key, value);
//        if(useRecord.size() == 0 || useRecord.peekFirst() != key) useRecord.addFirst(key);
//    }
}
