package Tree.constructBinaryTree;

public class ConstructBinaryTreeFromInorderPostorder {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        //做法： 还是跟 preorder inorder一样的方法， 就是在inorder上确定当前postorder的那个根的inIndex, 并在inIndex的左右的区间分别建立
        //左子树和右子树， 唯一不同的是在postorder上总的根节点肯定是最后一位， 然后往左顺推应该是右子树再是左子树， 所以我们要估算出右子树的叶子总数
        //(inEnd-inIndex+1)并在recursion的时候用上一个根节点跳过整个右子树来找到左子树的头
        //Runtime： 总的时间肯定是O(N^2), 因为我们每次recursion要在inorder上浏览inStart到inEnd来寻找postIndex即根的位置, space: O(1)
        public TreeNode buildTree(int[] inorder, int[] postorder) {
            return build(postorder.length - 1, 0, inorder.length - 1, inorder, postorder);
        }

        public TreeNode build(int postIndex, int inStart, int inEnd, int[] inorder, int[] postorder) {
            if (postIndex < 0 || inStart > inEnd) return null;
            TreeNode root = new TreeNode(postorder[postIndex]);
            int inIndex = 0;
            for (int i = inStart; i <= inEnd; i++) {
                if (inorder[i] == postorder[postIndex]) inIndex = i;
            }
            root.left = build(postIndex - (inEnd - inIndex + 1), inStart, inIndex - 1, inorder, postorder);
            root.right = build(postIndex - 1, inIndex + 1, inEnd, inorder, postorder);
            return root;
        }
    }
}
