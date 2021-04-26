package Graph.DFS;

public class SubtreeOfAnotherTree {
    // 做法: 用的简单的dfs去检查每一个s上和t的val相等的TreeNode, 它和t的check(s, t)是不是 true;
    // Runtime: O(n), Space: O(1)
    public boolean isSubtree_reviewed(TreeNode s, TreeNode t) {
        if(s == null && t == null) return true;
        if(s == null || t == null) return false;
        if(s.val == t.val && check(s, t)) return true;
        return isSubtree_reviewed(s.left, t) || isSubtree_reviewed(s.right, t);
    }

    public boolean check(TreeNode s, TreeNode t){
        if(s == null && t == null) return true;
        if(s == null || t == null) return false;
        if(s.val == t.val) return check(s.left, t.left) && check(s.right, t.right);
        return false;
    }




    //第一次做的
    //善用主程序的recursion

        public boolean isSubTree(TreeNode s, TreeNode t) {
            if (s == null) return false;
            if (check(s, t)) return true;
            return isSubTree(s.right, t) || isSubTree(s.left, t);
        }

        public boolean check_1(TreeNode s, TreeNode t) {
            if (s == null && t == null) return true;
            if (s == null || t == null) return false;
            if (s.val != t.val) return false;
            return check(s.left, t.left) && check(s.right, t.right);
        }
    }
