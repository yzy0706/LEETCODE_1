package Tree.DFS;

public class maxDepthOfBinaryTree {
    //做法： 直接拿主方程做dfs的recursion， 到了底层root == null会return 0， 再一步步往上叠加一个节点左右子树里最深的深度
    // Runtime: O(n) Space: O(1)

    public int maxDepthOfBinaryTree(TreeNode root){
        if(root == null) return 0;
        return Math.max(maxDepthOfBinaryTree(root.left)+1, maxDepthOfBinaryTree(root.right)+1);
    }







    //自己写的
    int res;
    public int maxDepth(TreeNode root) {
        res = 0;
        if(root == null) return res;
        search(root,1);
        return res;

    }


    public void search(TreeNode root,int cnt){
        if(root.left == null && root.right == null){
            if(cnt >= res ) res = cnt;
            return;
        }
        if(root.left != null) search(root.left, cnt+1);
        if(root.right != null) search(root.right, cnt+1);

    }
}
