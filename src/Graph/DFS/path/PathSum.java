package Graph.DFS.path;

public class PathSum {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        //做法:用一个curSum去承载到上一层的所有和, 然后直接dfs到root == null; 也可以直接用本来的sum -= root.val
        //Runtime: O(n), Space: O(1)
        public boolean hasPathSum(TreeNode root, int sum) {
            return dfs(root, sum, 0);
        }

        public boolean dfs(TreeNode root, int sum, int curSum) {
            if (root == null) return false;
            curSum += root.val;
            if (root.left == null && root.right == null) return curSum == sum;
            return dfs(root.left, sum, curSum) || dfs(root.right, sum, curSum);

        }


        public boolean hasPathSum_clearer(TreeNode root, int sum) {
            if (root == null) return false;
            if (root.left == null && root.right == null && sum - root.val == 0) return true;
            return hasPathSum_clearer(root.left, sum - root.val) || hasPathSum_clearer(root.right, sum - root.val);
        }
    }
}
