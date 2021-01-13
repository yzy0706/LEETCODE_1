package graph.DFS;

import javax.swing.tree.TreeNode;

public class twoSameTree {
    public boolean twoSameTree(TreeNode p , TreeNode q){
        if(p==null&&q==null) return true;
        if(p!=null&&q!=null&&p.val=q.val) return twoSameTree(p.left,q.left) && twoSameTree(p.right,q.right);
        else return false;
    }
}
