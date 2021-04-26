package Graph.BFS.levelorderTraversal;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class PopulatingNextRightPointers {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    //BFS用queue的解法， 我们只会对每一个node做一步的操作， 不会一直到底

    public Node connect_BFS(Node root){
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur != null) {
                if (cur.right != null) {
                    cur.left.next = cur.right;
                    if (cur.next != null) {
                        cur.right.next = cur.next.left;
                    }
                }
                queue.offer(cur.left);
                queue.offer(cur.right);
            }
        }
        return root;
    }











    //Recursion非stack的解法
    public Node connect_recursion(Node root){
        populate(root);
        return root;
    }
    public void populate(Node root) {
        if(root != null){
            if(root.right != null){
                root.left.next = root.right; //连接左右叶子节点
                if(root.next != null){ //连接左右邻居的交界叶子
                    root.right.next = root.next.left;
                }
            }
            populate(root.left);
            populate(root.right);
        }
    }





    //第二次看leetcode解法的

    //做法: 这题主要用stack去装载每一层的node, 当这个node不是null且right也不是null的时候, 连接左右叶子节点; 再在这个基础上判断有没有next节点,
    // 有next的话证明他在这层的右边还有个邻居, 再让他的cur.right.next = cur.next.left, 把他们右儿子和邻居的左儿子连起来,
    // 然后在pushstack的时候先进去右儿子再进去左儿子,这样下一层浏览的时候我们会先用左儿子, 因为stack是LIFO
    //Runtime: 所有的node浏览了一遍是O(N), space最快的情况也是O(n), 假如只有一层
    public Node connect_stackLeetcode(Node root) {
        dfsStack(root);
        return root;
    }

    public void dfsStack(Node root){
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            Node cur = stack.pop();
            if(cur != null){ //dfs一定要考虑cur是不是null的情况
                if(cur.right != null){
                    cur.left.next = cur.right;
                    if(cur.next != null) {
                        cur.right.next = cur.next.left; //当前的右叶子节点.next等于下一个同层的node的左叶子节点
                    }
                    stack.push(cur.right); //因为一直都是从倒数第二个开始浏览所以一直要先push right再left
                    stack.push(cur.left);
                }
            }
        }
    }



    //第一遍自己写的
    public Node connect(Node root) {
        if(root == null) return null;
        Stack<Node> stack = new Stack<>();
        Node head = root;
        stack.push(head);
        while(!stack.isEmpty()){
            int size = stack.size(); //浏览每一层的常规用法
            Node rightEnd = stack.pop();
            if(rightEnd.right != null) stack.push(rightEnd.right);
            if(rightEnd.left != null) stack.push(rightEnd.left);
            for(int i = 0; i < size-1; i++){
                Node last = stack.pop();
                last.next = rightEnd;
                rightEnd = last;
                if(rightEnd.right != null) stack.push(rightEnd.right);
                if(rightEnd.left != null) stack.push(rightEnd.left);
            }
        }
        return root;
    }
}
