package Tree.DFS;

public class TwoSameTree {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    //做法: 用的dfs做的, 假如当前比较的两个都是null代表两个都到底了直接return true, 假如两个的val不等于或者其中有一个 == null 直接return false, 因为之前已经判断了两个都是null的情况了. 最后return 子节点们的recursion
    //Runtime: O(n), 每个叶子结点浏览一次, Space: O(1)
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        if(p == null || q == null || p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
