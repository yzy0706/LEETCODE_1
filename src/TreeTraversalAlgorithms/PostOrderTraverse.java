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

    //递归版本， 先右后左， 暂时不写stack版本
    private void postOrderTraverse(TreeNode root){
        postOrderTraverse(root.right);
        postOrderTraverse(root.left);
        System.out.println(root.val + " ");
    }
}
