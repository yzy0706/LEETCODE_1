package TreeTraversalAlgorithms;

public class PostOrderTraverse {
    class TreeNode{
        TreeNode left;
        TreeNode right;
        int val;
        TreeNode next;

        public TreeNode(int val){
            this.val = val;
            this.left = null;
            this.right = null;
            this.next = null;
        }
    }

    //递归版本， 先遍历左子树再遍历右子树， 浏览完右子树以后再退回到根节点， 暂时不写stack版本
    private void postOrderTraverse(TreeNode root){
        postOrderTraverse(root.left);
        postOrderTraverse(root.right);
        System.out.println(root.val + " ");
    }
}
