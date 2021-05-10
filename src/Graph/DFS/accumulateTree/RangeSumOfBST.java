package Graph.DFS.accumulateTree;

public class RangeSumOfBST {
    // 做法: 直接拿当前的主方程做dfs, 一直到底部才会逐步叠加回来,
    // 1. 如果当前root.val在low和high的范围内则加入当前的root.val
    // 2. 否则直接return root当前左子树和右子树的recursion的结果的和
    // Runtime: O(n), Space: O(1);

    public int rangeSumBST(TreeNode root, int low, int high) {
        if(root == null) return 0;
        if(root.val <= high && root.val >= low) return root.val + rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);
        return rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);
    }

    public int rangeSumBST_reviewed(TreeNode root, int low, int high) {
        if(root == null) return 0;
        if(root.val > high) return rangeSumBST(root.left, low, high);
        if(root.val < low) return rangeSumBST(root.right, low, high);
        //修改了, 因为这个bst是有序排放的, 所以如果当前root.val < low, 只看右子树; root.val > high, 只看左子树
        return root.val + rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);
    }
}
