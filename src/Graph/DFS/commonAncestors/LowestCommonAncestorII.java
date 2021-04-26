package Graph.DFS.commonAncestors;

public class LowestCommonAncestorII {

    // 做法: 总的做法跟LCA I其实差不多, 但是我们不能再在postorder中碰到p、q中的一个就直接return, 而是应该用两个boolean承载pFound和qFound
    // 1. 建立一个helper, 这个postOrder return的TreeNode必须在pFound && qFound才能用, 开始构建helper
    // 2. 首先如果root == null直接return
    // 3. dfs一下root.left和root.right, return一个left和一个right, 这里跟LCA不同的是先进行下面的DFS再判断root是不是p,q之中单一一个, 因为这个题我们需要先完成所有的浏览
    // 4. 如果root是p、q中的一个哪个boolean就变成true, 而且还return root, 那么p、q就在最顶上的root这
    // 5. 如果不是其中的一个那么我们就判断left和right是不是有一个是null, 如果另外一个不是就return另外一个, 证明p、q都在左子树或者都在右子树
    // 6. 如果两个都不是空的就return root, 证明p、q分别在左右子树

    // Runtime: O(n), Space: O(1)
    boolean pFound = false;
    boolean qFound = false;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode LCA = postOrder(root, p, q);
        return pFound && qFound ? LCA : null;
    }

    public TreeNode postOrder(TreeNode root, TreeNode p, TreeNode q){
        if(root == null) return root;
        TreeNode left = postOrder(root.left, p, q); //检查左子树
        TreeNode right = postOrder(root.right, p, q); //检查右子树
        if(root == p){
            pFound = true;
            return root;
        }
        if(root == q){
            qFound = true;
            return root;
        }

        return left == null ? right : ( right == null ? left : root);
    }












    //第一遍自己根据 LowestCommonAncestorI写的， 写的太复杂了
    public TreeNode lowestCommonAncestor_self(TreeNode root, TreeNode p, TreeNode q) {
        return postOrder(root, p, q, false);
    }

    public TreeNode postOrder(TreeNode root, TreeNode p, TreeNode q, boolean both){
        if(root == null) return root;
        if((root == p || root == q) && !both){
            both = checkSubtree(root.left, p, q) && checkSubtree(root.right, p, q); //另外一个就在他的子树里
            if(both) return root;
        }

        if(checkSubtree(root.left, p, q) && checkSubtree(root.right, p, q)) return root; //两边都有p、q中的一个

        TreeNode left = postOrder(root.left, p, q, false); //检查左子树
        TreeNode right = postOrder(root.right, p, q, false); //检查右子树

        if(left == null && right == null) return null; //两边都找不到

        return left == null ? right : left;
    }

    public boolean checkSubtree(TreeNode root, TreeNode p, TreeNode q){ //一个小型的postorder
        if(root == null) return false;
        if(root == p || root == q) return true;
        boolean left = checkSubtree(root.left, p, q);
        boolean right = checkSubtree(root.right, p, q);
        if(left || right) return true;
        return false;
    }
}
