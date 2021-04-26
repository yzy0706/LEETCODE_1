package Graph.DFS.commonAncestors;

public class LowestCommonAncestors {
    // 做法: lowestCommonAncestor的题基本都用到了一个postorder的dfs的方法, 善用recursion
    // 1. 用recursion检查root的左右路径, left = lowestCommonAncestor(root.left, p, q); right做相同的recursion,
    // 如果当前root是p、q中的一个或者是null就return当前的root
    // 2. p、q在不同的子树里: 如果left、 right都不是null的, 代表左右子树都找了p,q中的一个,
    // 代表当前root就是他们最小的common ancestor,
    // 3. p、q在同一个子树里: 如果left、 right中有一个是null, 代表从哪个当前root开始找,
    // p、q都在左子树或者右子树里, 所以p、q总有一个先出现而且p、q是一个先后出现的祖宗与子孙的关系,
    // 所以return root和right里那个不是null的treenode就是先出现的那个

    // Runtime: O(n). Space: O(1);
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left != null && right != null) return root; //如果当前root的左右都找到p、q了就直接return当前的root
        return left == null ? right : left; //如果只有一边找到p、q证明p、q在一个subtree上, return这个subtree
    }
}
