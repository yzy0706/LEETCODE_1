package Tree.DFS.inorderTraversal;

import java.util.ArrayList;
import java.util.List;

public class ConvertBSTToSortedDoublyLinkedList {
    //做法: discussion给的解法
    // 1. 用一个dummy来充当root的前一个prev
    // 2. 然后用prev来记录inorder的过程中碰到的上一个node, 并且持续让prev.right = cur; cur.left = prev;
    // 3. 最后再把最后一个node和跟第一个node连接的dummy, 通过dummy连接起来:
    // dummy.right.left = prev; (prev就是inorder浏览到的树里面的最后一个点)
    // prev.right = dummy.right;

    // Runtime: O(n), Space: O(1)


    Node prev;

    public Node treeToDoublyList_dummy(Node root) {
        if(root == null) return null;
        Node dummy = new Node();
        prev = dummy;
        inorder(root);
        dummy.right.left = prev;
        prev.right = dummy.right;
        return prev.right;
    }

    private void inorder(Node root){
        if(root == null) return;
        inorder(root.left);
        prev.right = root;
        root.left = prev;
        prev = root;
        inorder(root.right);
    }





    //做法: 我自己写的是list的解法:
    // 1. 用一个list记录inorder过程中碰到的所有node, 每一个都与前一个相连,
    // 2. 最后list的第一个node再与最后一个node相连: first.left = last; last.right = first;

    // Runtime: O(n), Space: O(n)

    public Node treeToDoublyList_list(Node root) {
        if(root == null) return null;
        // if(root.left == null && root.right == null) return root;
        List<Node> list = new ArrayList<>();
        inorder_list(root, list);
        Node first = list.get(0), last = list.get(list.size() - 1);
        first.left = last;
        last.right = first;
        return first;
    }

    private void inorder_list(Node root, List<Node> list){
        if(root == null) return;
        inorder_list(root.left, list);
        if(list.size() > 0){
            Node last = list.get(list.size() - 1);
            root.left = last;
            last.right = root;
        }
        list.add(root);
        inorder(root.right, list);
    }











    // 这是我准备用list做的， 理论上来说应该是没错的
    int min;
    Node res, origin;

    public Node treeToDoublyList(Node root) {
        if(root == null) return null;
        res = new Node();
        origin = root;
        List<Node> list = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        inorder(root, list);
        return res;
    }

    private void inorder(Node root, List<Node> list){
        if(root == null) return;
        if(root.val < min){
            res = root;
            min = root.val;
        }
        inorder(root.left, list);
        if(list.size() > 0){
            Node last = list.get(list.size() - 1);
            root.left = last;
            last.right = root;
        }
        list.add(root);
        inorder(root.right, list);
        if(root.val == origin.val){
            if(list.size() == 1) return;
            Node first = list.get(0), last = list.get(list.size() - 1);
            first.left = last;
            last.right = first;
        }
    }
}
