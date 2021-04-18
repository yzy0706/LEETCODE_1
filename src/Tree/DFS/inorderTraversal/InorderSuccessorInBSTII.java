package Tree.DFS.inorderTraversal;

public class InorderSuccessorInBSTII {
    //做法: 这题我做了半天, 但他只用了一个简单的inorder
    // 1. 如果当前右节点是空的, 相当于inorder到了当前node的位置需要继续往上return, 就直接往上一直找到第一个比当前node大的node
    // 2. 如果当前右节点不是空的, 就用右节点的left一直往下做inorder,  相当于inorder先把左节点浏览到底

    // Runtime: O(n), Space: O(1)
    public Node inorderSuccessor_2(Node node) {
        if (node.right == null) return up(node, node.val);
        return down(node.right);
    }

    private Node down(Node node) {
        if (node.left == null) return node;
        return down(node.left);
    }

    private Node up(Node node, int val) {
        if (node.parent == null || node.parent.val > val) return node.parent;
        return up(node.parent, val);
    }





    public Node inorderSuccessor_1(Node node) {
        int val = node.val;
        Node subMin = down(node, val, node);
        Node upMin = up(node.parent, node, val, node);
        if(subMin == node && upMin == node) return null;
        if(subMin == node || upMin == node) return subMin == node ? upMin : subMin;
        return subMin.val < upMin.val ? subMin : upMin;
    }

    public Node down(Node node, int val, Node min){
        if(node == null) return min;
        if(node.val == val + 1) return node;
        if(node.val > val && (node.val < min.val || min.val == val)) min = node;
        if(down(node.left, val, min).val == val || down(node.right, val, min).val == val)
            return down(node.left, val, min).val == val ? down(node.right, val, min) : down(node.left, val, min);

        return down(node.left, val, min).val < down(node.right, val, min).val ? down(node.left, val, min) : down(node.right, val, min);
    }

    public Node up(Node node , Node prev, int val, Node min){
        if(node == null) return min;
        if(node.val == val + 1) return node;
        if(node.val > val && (node.val < min.val || min.val == val)) min = node;
        min = prev == node.left ? down(node.right, val, min) : down(node.left, val, min);
        return up(node.parent, node, val, min);
    }
}
