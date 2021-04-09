package Tree.DFS.path;

public class CountGoodNodesInBinaryTree {
        //做法: 就是基础的dfs, 我们每次都记载当前这个path最大的值, 然后拿当前node的val和他做比较
        //Runtime: O(n), Space: O(1);
        int res = 0;
        public int goodNodes(TreeNode root) {
            if(root == null) return 0;
            dfs(root, root.val);
            return res;
        }

        public void dfs(TreeNode root, int largest){
            if(root == null) return;
            if(root.val >= largest){
                res ++;
                largest = Math.max(largest, root.val);
            }
            dfs(root.left, largest);
            dfs(root.right, largest);
        }
}
