package TreeTraversalAlgorithms;

import java.util.LinkedList;

public class PreorderTraverse {
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

    //Recursion的做法
    public void preOrderRecursive(TreeNode root){
        if(root == null) return;
        System.out.println(root.val + " ");
        preOrderRecursive(root.left);
        preOrderRecursive(root.right);
    }


    //Stack的做法: 用一个stack来装载listNode， cur = root， 首先沿着cur.left一直往下找， 如果找到null了， 就pop出他的爸爸，
    // 也就是最后一个加到stack里去的，然后cur就转换为他爸爸的右儿子

    public void preOrderStack(TreeNode root){
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode head = root;
        while(head != null || !stack.isEmpty()){
            if(head != null){
                System.out.println(head.val + " ");
                stack.push(head);
                head = head.left;
            }
            else{
                TreeNode father = stack.pop(); // 退回到他的爹
                head = father.right; //浏览他爹的右儿子
            }
        }

    }
}
