package Graph.DFS;

import java.util.LinkedList;
import java.util.Queue;

public class symmetricTree {
    // dfs的办法， 直接检查left.right, right.left并一直到最后的node
    public boolean isSymmetric_1(TreeNode root) {
        return check(root, root);

    }

    public boolean check(TreeNode left, TreeNode right){
        if(left == null && right == null) return true;
        if(left == null || right == null) return false;
        return (left.val == right.val) && check(left.right, right.left) && check(left.left, right.right);
    }



    //答案里queue的办法
    public boolean isSymmetric_queue(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        q.offer(root);
        while(!q.isEmpty()){
            TreeNode left = q.poll();
            TreeNode right = q.poll();
            if(left == null && right == null) continue;
            if(left == null || right == null) return false;
            if(left.val != right.val) return false;
            q.offer(left.left);
            q.offer(right.right);
            q.offer(left.right);
            q.offer(right.left);
        }

        return true;

    }





    //自己写的queue的办法
    public boolean isSymmetric_2(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root.left);
        q.add(root.right);
        while (!q.isEmpty()) {
            TreeNode tempLeft = q.remove();
            TreeNode tempRight = q.remove();
            if (tempLeft == null && tempRight == null) {
                continue;
            }
            if ((tempLeft == null && tempRight != null) ||
                    (tempLeft != null && tempRight == null)) {
                return false;
            }
            if (tempLeft.val != tempRight.val) {
                return false;
            }
            q.add(tempLeft.left);
            q.add(tempRight.right);
            q.add(tempLeft.right);
            q.add(tempRight.left);
        }
        return true;
    }



    public boolean isSymmetric(TreeNode root) {
        if(root.left!=null&&root.right!=null){
            if(root.left.val==root.right.val){
                return check(root.left,root.right);
            }
            else return false;
        }

        else return false;
    }

//    public boolean check(TreeNode p, TreeNode q){
//        if(p==null&&q==null) return true;
//
//        if(p.left!=null&&p.right!=null&&q.left!=null&&q.right!=null&&p.left.val==q.right.val&&p.right.val==q.left.val){
//            return check(p.left,q.right)&&check(p.right,q.left);
//        }
//        else return false;

}
