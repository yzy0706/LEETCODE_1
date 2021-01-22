package TreeTraversalAlgorithms;

import java.util.LinkedList;
import java.util.Queue;

public class LevelOrderTraverse {
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

    //Queue做法： 用queue来承载每一层
    private void levelorder(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode cur = queue.poll();
            System.out.println(cur.val + " ");
            if(cur.left != null) queue.offer(cur.left);
            if(cur.right != null) queue.offer(cur.right);
        }
    }
}
