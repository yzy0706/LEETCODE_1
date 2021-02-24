package Graph.DFS;

public class BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return (Math.abs(DFS(root.left, 0) - DFS(root.right, 0)) <= 1 && isBalanced(root.left) && isBalanced(root.right));


    }

    public int DFS(TreeNode node, int n) {
        if (node == null) return n;
        n++;
        return Math.max(DFS(node.left, n), DFS(node.right, n));
    }
}
