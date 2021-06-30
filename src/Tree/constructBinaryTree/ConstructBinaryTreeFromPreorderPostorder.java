package Tree.constructBinaryTree;

public class ConstructBinaryTreeFromPreorderPostorder {
    // 做法: 这种题就是典型的给你两个浏览好的array让你建立bst的题, 中心做法就是
    // 1. 不管是什么样的浏览, 肯定在一个array里能找到最顶端的那个root的index, 记为rootIndex, 在另外一个postorder上设定一个找寻root的范围(0, post.length - 1), 开始recursion: construct(pre, post, 0, 0, pre.length - 1);
    // 2. 在helper里:
    //      a. 如果浏览的起点和终点start == end, 直接用当前的rootIndex对应的值建立一个TreeNode然后return它, 因为当前已经到了recursion的底部了
    //      b. 根据一个array找到当前TreeNode对应的子节点的值, 在这里, pre[rootIndex + 1]肯定就是左节点的值leftVal
    //      c. forloop [start, end) 范围内的post array, 找到leftVal对应的位置i以后, 就可以知道leftTreeSize = i - start + 1;
    //      d. 确定了左子树的大小以后:
    //      cur.left = construct(pre, post, rootPreIndex + 1, start, start + leftTreeSize - 1);
    //      cur.right = construct(pre, post, rightPreIndex, start + leftTreeSize, end - 1);

    // Runtime: O(log(n)), Space: O(1);


    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        return construct(pre, post, 0, 0, pre.length - 1);
    }

    private TreeNode construct(int[] pre, int[] post, int rootPreIndex, int start, int end){
        if(start > end){
            return null;
        }
        if(start == end){
            return new TreeNode(pre[rootPreIndex]);
        }
        TreeNode cur = new TreeNode(pre[rootPreIndex]);
        TreeNode left = new TreeNode();
        TreeNode right = new TreeNode();

        int leftVal = pre[rootPreIndex + 1];
        for(int i = start; i < end; i++){
            if(post[i] == leftVal){
                int leftTreeSize = i - start + 1;
                left = construct(pre, post, rootPreIndex + 1, start, start + leftTreeSize - 1);
                int rightPreIndex = rootPreIndex + 1 + leftTreeSize;
                right = construct(pre, post, rightPreIndex, start + leftTreeSize, end - 1);
            }
        }
        cur.left = left;
        cur.right = right;
        return cur;
    }
}
