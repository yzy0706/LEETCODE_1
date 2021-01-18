package TreeTraversalAlgorithms;

import java.util.LinkedList;

public class InOrderTraverse {
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

    //递归版本
    private void inOrderTraverseRecursive(TreeNode root){
        inOrderTraverseRecursive(root.left);
        System.out.println(root.val);
        inOrderTraverseRecursive(root.right);
    }

    //Stack版本： 也是按照左边走到底， 但跟前序相反的是不是一边往下走一边使用， 而是走到头以后在回头找爹的这个过程中再用爹
    private void inOrderTraversalStack(TreeNode root){
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode head = root;
        while(head != null || !stack.isEmpty()){
            if(head != null){
                stack.push(head);
                head = head.left;
            }
            else{
                TreeNode cur = stack.pop(); //找爹
                System.out.println(cur.val + " "); //用他爹
                head = cur.right; //浏览右儿子
            }
        }

    }
}
