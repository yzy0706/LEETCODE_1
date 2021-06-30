package Graph.DFS.dfsLeftRightTree;

public class RecoverBinaryTree {
    // 做法: 用的dfs来寻找每一个root左子树的最大node和右子树的最小node, 如果不是当前的root那么就swap
    // 1. 建立寻找最大node和最小node的dfs helper, 建立swap两个TreeeNode的helper
    // 2. 对于当前的root, 找到leftMax和rightMin:
    //    a. 如果leftMax和rightMin都不等于root, 那么swap他们两个, 代表他们两个需要交换
    //    b. 如果leftMax和rightMin之中有一个不是root, 那么有一个不符合bst的条件, 交换root和这个不符合条件的
    //    c. 如果两个都是root, 那么证明对于当前的root来说左右子树都没问题, 那么就往下用主方程dfs他的右节点和左节点, 看看他的左右节点作为root, 左右子树有没有需要swap的

    // Runtime: O(n^2), Space: O(1)

    public void recoverTree(TreeNode root) {
        if(root == null) return;
        TreeNode leftMax = findMax(root.left, root), rightMin = findMin(root.right, root);
        if(leftMax != root && rightMin != root){
            swap(leftMax, rightMin);
            return;
        }
        else if(leftMax != root){
            swap(root, leftMax);
            return;
        }
        else if(rightMin != root){
            swap(root, rightMin);
            return;
        }
        else{
            recoverTree(root.left);
            recoverTree(root.right);
        }
    }

    public TreeNode findMax(TreeNode root, TreeNode maxNode){
        if(root == null) return maxNode;
        if(root.val > maxNode.val) maxNode = root;
        TreeNode left = findMax(root.left, maxNode);
        TreeNode right = findMax(root.right, maxNode);
        return left.val > right.val ? left : right;
    }

    public TreeNode findMin(TreeNode root, TreeNode minNode){
        if(root == null) return minNode;
        if(root.val < minNode.val) minNode = root;
        TreeNode left = findMin(root.left, minNode);
        TreeNode right = findMin(root.right, minNode);
        return left.val < right.val ? left : right;
    }

    public void swap(TreeNode a, TreeNode b){
        int temp = a.val;
        a.val = b.val;
        b.val = temp;
    }
}
