package Graph.DFS.preorderHybirdPostorder;

public class BoundaryOfBinaryTree {
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) return result;
        result.add(root.val);
        preorder(root.left, true, result);
        postorder(root.right, true, result);
        return result;

    }

    private boolean isLeaf(TreeNode root){
        return root.left == null && root.right == null;
    }

    private void preorder(TreeNode root, Boolean isBoundary, List<Integer> result){
        if(root == null) return;
        if(isBoundary || isLeaf(root)) result.add(root.val);

        if(root.left != null){ //判断一下左叶子是不是null决定boundary在哪
            preorder(root.left, true, result);
            preorder(root.right, false, result);
        }else{
            preorder(root.right, true, result);
        }
    }

    private void postorder(TreeNode root, Boolean isBoundary, List<Integer> result){
        if(root == null) return;

        if(root.right != null){
            postorder(root.right, true, result);
            postorder(root.left, false, result);
        }else{
            postorder(root.left, true, result); //postorder要先dfs再使用当前root的值
        }

        if(isBoundary || isLeaf(root)) result.add(root.val);
    }







    //第一遍自己写的， 杂交了preorder和postorder， 其实思路没错
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        TreeNode head = root;
        stack.push(head);
        while(head != null || !stack.isEmpty()){ //这两个要放在一起
            if(!head == null){
                list.add(head.val);
                head = head.left;
                stack.push(head);
            }
            else{
                TreeNode last = stack.pop();
                head = last.right;
                postOrder(head, list);
                head = null;
            }
        }
    }

    public void postOrder(TreeNode head, List<Integer> list){
        postOrder(head.left);
        postOrder(head.right);
        if(head.left == null && head.right == null){
            list.add(head.val);
        }
    }
}
