package Graph.DFS.path;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllNodesDistanceKInBinaryTree {
//做法: 先用一个dfs方程 findTarget 从root左右子树向下开始找寻target, 如果找到了target就会从return 0开始一层层的加一上来, 就得到了root到target的距离, 然后map里就包含了当前所有root到target路径上的TreeNode以及他们到target的距离;
    // 在这个基础上我们再建立一个方程去从root开始dfs左右叶子节点并且距离从map.get(root)开始每次dfs都+1,
    //但是如果当前map里本来就有这个TreeNode的话我们应该把当前的distance换成map.get(root), 再跟着正常步骤一起判断 distance == K的情况
    //Runtime: 找target最多就是O(n)了, 然后我们还需要从root开始dfs一遍所有的node, 所以看作O(n), space最多也是O(n)

    public List<Integer> distanceK_1(TreeNode root, TreeNode target, int K) {
        List<Integer> res = new ArrayList<>();
        Map<TreeNode, Integer> map = new HashMap<>();
        findTarget(root, target, map);
        findQualified(root, target, map.get(root), K, map, res);
        return res;
    }

    public int findTarget(TreeNode root, TreeNode target, Map<TreeNode, Integer> map){
        if(root == null) return -1;
        if(root == target){
            map.put(root, 0);
            return 0;
        }
        int left = findTarget(root.left, target, map);
        if(left >= 0){
            map.put(root, left+1);
            return left+1;
        }

        int right = findTarget(root.right, target, map);
        if(right >= 0){
            map.put(root, right+1);
            return right+1;
        }
        return -1;
    }


    private void findQualified(TreeNode root, TreeNode target, int distance, int K, Map<TreeNode, Integer> map, List<Integer> res){
        if(root == null) return;
        if(map.containsKey(root)) distance = map.get(root); //如果map里本来就有当前的node, 那么证明他肯定在root到target的路径上, 直接把distance置换一下
        if(distance == K) res.add(root.val); //如果不是路径上的再去判断distance是不是等于K
        findQualified(root.left, target, distance+1, K, map, res);
        findQualified(root.right, target, distance+1, K, map, res);  //再往左右dfs去看下面的其他TreeNode在map里还是distance == k都行
    }







    //主要用dfs的做法
    int keyLevel = -1 ;
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        Map<Integer, List<Integer>> map = new HashMap<>();
        expand(root, map, target, 0);

        if(keyLevel != -1){
            int back = keyLevel-K;
            int foward = keyLevel+K;

            if(back >= 0) res.addAll(map.get(back));

            else if(back < 0){
                int abs = Math.abs(back);
                if(map.containsKey(abs)){
                    res.addAll(map.get(abs));
                }
            }

            if(map.containsKey(foward)){
                res.addAll(map.get(foward));
            }
        }
        // System.out.println(map.values());
        return res;
    }




    public void expand(TreeNode root, Map<Integer, List<Integer>> map, TreeNode target, int level){
        if(root == null) return;

        if(root != null) {

            if(root.val == target.val){
                keyLevel = level;
                if(root.left != null) expand(root.left, map, target, level+1);
                if(root.right != null) expand(root.right, map, target, level+1);
                return;
            }

            if(map.containsKey(level)){
                if(root.val == target.val) {
                    keyLevel = level;

                }
                List<Integer> cur = map.get(level);
                cur.add(root.val);
                map.replace(level, cur);
            }
            else{
                List<Integer> tmp = new ArrayList<>();
                tmp.add(root.val);
                map.put(level, tmp);
            }
        }


        if(root.left != null) expand(root.left, map, target, level+1);
        if(root.right != null) expand(root.right, map, target, level+1);
    }
}
