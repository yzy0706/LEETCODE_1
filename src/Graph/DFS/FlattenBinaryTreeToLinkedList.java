package Graph.DFS;

public class FlattenBinaryTreeToLinkedList {
    //做法: 用一个recursion把每一个根节点的左右子树记录并且拉平(left, right), 清空左子树. 然后把记录好并且拉平了的左子树left连在root根节点上, 再用一个pointer cur一直浏览到这个右子树的底端并接上之前记录好并且拉平了的右子树right
    //Runtime: 每一个subtree中都需要拉平左右子树并且随着cur一直浏览完左子树的长度再接右子树, 我们假设这个root的右子树都是空的, 就是最差的情况O(n^2), space: O(n), 需要left, right来记录左右子树
    public void flatten(TreeNode root) {
        if(root == null) return;
        TreeNode left = root.left;
        TreeNode right = root.right;  //用两个指针来记录左右叶子节点
        root.left = null; //这里必须先把root.left清空
        flatten(left);
        flatten(right);

        TreeNode cur = root;
        cur.right = left;  //cur来代替root的指针, 并且cur的右边接上拉平的left
        while(cur.right != null) cur = cur.right; //下降到最后一个右叶子节点
        cur.right = right; //到了最底端的cur把一开始root的拉平的右子树right接上

    }
}
