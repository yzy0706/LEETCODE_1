package Tree.DFS;
public class SubTreeOfAnotherTree {
    public class TreeNode {

        TreeNode left;
        int val;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    //善用大程序的recursion

        public boolean isSubTree(TreeNode s, TreeNode t) {
            if (s == null) return false;
            if (check(s, t)) return true;
            return isSubTree(s.right, t) || isSubTree(s.left, t);
        }

        public boolean check(TreeNode s, TreeNode t) {
            if (s == null && t == null) return true;
            if (s == null || t == null) return false;
            if (s.val != t.val) return false;
            return check(s.left, t.left) && check(s.right, t.right);
        }
    }