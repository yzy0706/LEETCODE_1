package Graph.DFS.preorderTraversal;

import Graph.BFS.levelorderTraversal.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class BinaryTreeVerticalOrderTraversal {
    //做法: 用了preorder和TreeMap, 主要是在TreeMap里排列每一个column和对应所有node, 然后在同一个column里按照他们row(level)来排列所有的node
    // 1. 建立一个TreeMap<Integer, List<int[]>> map = new TreeMap<>();
    // 2. 把root的i设置为0, j设置为0, 开始做 preorder(root, 0, 0); 收集所有的node到map里
    // 3. 浏览map里的每一个按照i排列好的list, 我们把每一个list都按照(a, b) -> a[0] - b[0]的comparator来sort一下, 就变成了按照level来sort同一个column的node, 用一个List<Integer> temp储存所有排列好的node的val, res.add(temp);
    // Runtime: O(nlog(n)), Space: O(nlog(n))

      TreeMap<Integer, List<int[]>> map;
    public List<List<Integer>> verticalOrder(TreeNode root) {
        if(root == null) return new ArrayList<>();
        map = new TreeMap<>();
        preorder(root, 0, 0);
        List<List<Integer>> res = new ArrayList<>();
        for(int key : map.keySet()){
            List<int[]> cur = map.get(key);
            cur.sort((a, b) -> a[0] - b[0]);
            List<Integer> temp = new ArrayList<>();
            for(int[] element : cur) temp.add(element[1]);
            res.add(temp);
        }
        return res;
    }

    public void preorder(TreeNode root, int i, int j){
        if(root == null) return;
        map.putIfAbsent(i, new ArrayList<>());
        map.get(i).add(new int[]{j, root.val});
        preorder(root.left, i - 1, j + 1);
        preorder(root.right, i + 1, j + 1);
    }


}
