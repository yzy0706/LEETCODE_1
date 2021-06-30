package Temp;

import java.util.*;
public class DistanceBetweenTwoNodesInBST {
    public int bstDistance_leetcode(TreeNode root, int node1, int node2) {
        if (root == null) return -1;
        TreeNode lca = lca(root, node1, node2);
        return getDistance(lca, node1) + getDistance(lca, node2);
    }

    private int getDistance(TreeNode src, int dest) {
        if (src.val == dest) return 0;
        return src.val < dest ? 1 + getDistance(src.right, dest) : 1 + getDistance(src.left, dest);
        // 在bst里面往左子树更小， 右子树更大
    }

    private TreeNode lca(TreeNode root, int node1, int node2) {
        while (true) {
            if (root.val > node1 && root.val > node2) {
                root = root.left;
            } else if (root.val < node1 && root.val < node2) {
                root = root.right;
            } else {
                return root;
            }
        }
    }


    //
    HashMap<Integer, Integer> map;
    int res;
    public int distance(TreeNode root, int node1, int node2){
        if(root == null) return 0;
        map = new HashMap<>();
        res = -1;
        dfs(root, new HashSet<>(), 0, node1, node2);
        return res;
    }

    public boolean dfs(TreeNode root, Set<Integer> path, int depth, int node1, int node2){
        if(root == null){
            return false;
        }
        if(root.val == node1 && path.contains(node2)) {
            res = depth - map.get(node2);
            return false;
        }
        if(root.val == node2 && path.contains(node1)) {
            res = depth - map.get(node1);
            return false;
        }
        map.put(root.val, depth);
        path.add(root.val);
        boolean left = dfs(root.left, new HashSet<>(path), depth + 1, node1, node2);
        boolean right = dfs(root.right, new HashSet<>(path), depth + 1, node1, node2);
        if(res != -1){
            return false;
        }
        if(root.val == node1 || root.val == node2){
            return true;
        }
        else {
            if(left && right){
                res = map.get(node1) - depth + map.get(node2) - depth;
                return false;
            }
        }
        return false;

    }
}
