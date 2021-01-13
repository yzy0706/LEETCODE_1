package graph.DFS;

public class allNodesDistanceKInBinaryTree {
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
