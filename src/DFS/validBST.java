package DFS;

public class validBST {
    // DFS做法，主要就是在用一个记录lower和upper的helper来递归root的val和他左边、右边的node的val
    public boolean isValidBST(TreeNode root) {
        return  check(root, null, null);
    }

    public boolean check(TreeNode root, Integer lower, Integer upper){
        if(root == null) return true;
        int val = root.val;
        if(lower != null && val <= lower) return false;
        if(upper != null && val >= upper) return false;
        if(!check(root.right, val, upper)) return false;
        if(!check(root.left, lower, val)) return false;
        return true;
    }
}
