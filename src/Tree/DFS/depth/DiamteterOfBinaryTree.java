package Tree.DFS.depth;

public class DiamteterOfBinaryTree {
//做法: 这题看似简单, 只是简单的dfs, 但其实涉及到了一个关键的数据怎么在dfs里叠加的问题,
    //  1. 首先, 一个return type是int的方程, 只要方程里面涉及到修改是可以单独存在的
    //  2. 当我们碰到极限情况, 我们肯定return 0
    //  3. 但如果是深度的叠加, 我们不应该去在找left和right的代码里叠加深度, 也不应该在paramter里, 因为这是个dfs, 我们要在碰到底端以后一步步叠加回来, 所以我们应该在最后一步return的时候 return Math.max(left, right) + 1;

    // Runtime: O(N) , Space: O(1)
    int res = 0;
    public int diameterOfBinaryTree_DFS(TreeNode root) {
        dfs(root);
        return res;
    }


    public int dfs(TreeNode root){
        if(root == null) return 0;
        int left = dfs(root.left);
        int right = dfs(root.right);
        res = Math.max(res, left + right);
        return Math.max(left, right) + 1;
    }






    //第一遍自己写的
    public int diameterOfBinaryTree(TreeNode root) {
        return res;
    }


    public int dfs(TreeNode root, int depth){
        if(root == null) return 0;
        int left = root.left == null ? 0 : dfs(root.left, depth + 1) - depth;
        int right = root.right == null ? 0 : dfs(root.right, depth + 1) - depth;
        res = Math.max(res, left + right);
        return depth;
    }
}
