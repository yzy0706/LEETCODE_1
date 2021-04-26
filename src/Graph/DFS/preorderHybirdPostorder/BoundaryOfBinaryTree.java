package Graph.DFS.preorderHybirdPostorder;

import java.util.ArrayList;
import java.util.List;

public class BoundaryOfBinaryTree {


    // 做法： 重新做了一遍， 其实就是在root的左边做preorder, 在root的右边做postorder，
    // 而且要记载当前的node是不是isBoundary, 如果它自己不是的话它的儿子们也不会是, 直接带着isBoundary一起dfs下去
    // 注意： postorder和preorder都用dfs recursion把， 唯一不同的是在dfs之前还是之后使用val
    // Runtime: O(n), Space: O(1)

    public List<Integer> boundaryOfBinaryTree_reviewed(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        res.add(root.val);
        leftPre(root.left, true, res);
        rightPost(root.right, true, res);
        return res;
    }

    public boolean isLeaf_2(TreeNode root){
        return root.left == null && root.right == null;
    }

    public void leftPre(TreeNode root, boolean isBoundary, List<Integer> res){
        if(root == null){
            return;
        }
        if(isBoundary || isLeaf(root)){
            res.add(root.val);
        }
        if(root.left != null){
            leftPre(root.left, isBoundary, res);
            leftPre(root.right, false, res);
        }
        else{
            leftPre(root.right, isBoundary, res);
        }
    }

    public void rightPost(TreeNode root, boolean isBoundary, List<Integer> res){
        if(root == null){
            return;
        }
        if(root.right != null){
            rightPost(root.left, false, res);
            rightPost(root.right, isBoundary, res);
        }
        else{
            rightPost(root.left, isBoundary, res);
        }
        if(isBoundary || isLeaf(root)){
            res.add(root.val);
        }
    }





    public List<Integer> boundaryOfBinaryTree_discussion(TreeNode root) {
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







//    //第一遍自己写的， 杂交了preorder和postorder， 其实思路没错
//    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
//        Stack<TreeNode> stack = new Stack<>();
//        List<Integer> list = new ArrayList<>();
//        TreeNode head = root;
//        stack.push(head);
//        while(head != null || !stack.isEmpty()){ //这两个要放在一起
//            if(head != null){
//                list.add(head.val);
//                head = head.left;
//                stack.push(head);
//            }
//            else{
//                TreeNode last = stack.pop();
//                head = last.right;
//                postOrder(head, list);
//                head = null;
//            }
//        }
//    }

//    public void postOrder(TreeNode head, List<Integer> list){
//        postOrder(head.left);
//        postOrder(head.right);
//        if(head.left == null && head.right == null){
//            list.add(head.val);
//        }
//    }
}
