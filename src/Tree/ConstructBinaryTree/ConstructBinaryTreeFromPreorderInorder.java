package Tree.ConstructBinaryTree;


public class ConstructBinaryTreeFromPreorderInorder {
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

    //做法: 在preorder上的顺序是root, 左边的子树的所有点, 右边的子树的所有点; 在inorder上的舒徐是左子树所有点, 根节点, 右子树, 所以我们知道在inorder上, 如果root是inorder[i]的话, 0到i-1就是他的左子树, i+1到inorder.length-1就是他的右子树, 所以我们就这么循环下去建立整个binary tree
    //Runtime: 从Root开始找他所有的子节点并且每个浏览一遍, 这期间又得浏览inorder, 我猜一个O(n^2), 假设只有一边有子树的话. Space O(1)
    public TreeNode buildTree_recursive(int[] preorder, int[] inorder) {
        return buildTree(0, 0, inorder.length-1, preorder, inorder);
    }

    private TreeNode buildTree(int preIndex, int inStart, int inEnd, int[] preorder, int[] inorder){
        if(preIndex > preorder.length-1 || inEnd < inStart) return null;
        TreeNode root = new TreeNode(preorder[preIndex]);
        int inIndex = 0;
        for(int i = inStart; i <= inEnd; i++){
            if(inorder[i] == preorder[preIndex]) inIndex = i; //找到inorder的区间里的跟根节点在preorder的位置
        }
        root.left = buildTree(preIndex+1, inStart, inIndex-1, preorder, inorder); //inIndex的左边建左树
        root.right = buildTree(preIndex+inIndex-inStart+1, inIndex+1, inEnd, preorder, inorder); //inIndex的右边建右子树, preorder里右子树的根节点应该是preIndex+整个左子树的大小才能越过左子树
        return root;

    }





    //第一遍自己做的， 但有Memory Error
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        TreeNode root = new TreeNode(preorder[0]);
        TreeNode head = root;
        expand(head, preorder, inorder);
        return root;
    }

    private void expand(TreeNode head, int[] preorder, int[] inorder){
        for(int i = 0; i < inorder.length; i++){
            if(inorder[i] == head.val){
                if(i-1 >= 0) {
                    head.left = new TreeNode(inorder[i-1]);
                    expand(head.left, preorder, inorder);
                }
            }
        }
        for(int i = 0; i < preorder.length; i++){
            if(preorder[i] == head.val){
                if(i+1 < preorder.length){
                    head.right = new TreeNode(preorder[i+1]);
                    expand(head.right, preorder, inorder);
                }
            }
        }
    }
}
