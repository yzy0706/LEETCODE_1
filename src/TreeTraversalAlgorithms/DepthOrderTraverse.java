package TreeTraversalAlgorithms;

import java.util.LinkedList;

public class DepthOrderTraverse {
    class TreeNode{
        TreeNode left;
        TreeNode right;
        TreeNode next;
        int val;

        public TreeNode(int val){
            this.val = val;
            this.left = null;
            this.right = null;
            this.next = null;
        }
    }

    //Stack做法： 直接用stack承载所有的TreeNode并浏览，浏览到每一个cur的时候使用他并把他不是null的自节点加到stack里
    private void depthOrder(TreeNode root){
        if(root == null) return;
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.offer(root);
        while(!stack.isEmpty()){
            TreeNode cur = stack.pop();
            System.out.println(cur.val + " ");
            if(cur.left != null) stack.push(cur.left);
            if(cur.right != null) stack.push(cur.right);
        }

    }
}
