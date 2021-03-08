package Stack.implementDataStructure;


import java.util.Stack;

public class BinarySearchTreeIterator {
    // 做法: 就是用到了tree traversal里面的inorder的做法, 用stack来装在inorder里面浏览到的所有node
// 1. 当前如果是constructor BSTIterator(TreeNode root), 就先inorder到最左端, 把路径上碰到的所有node都放进去
// 2. 如果是求hasNext(); 就直接检查当前stack里面还有没有东西, 因为stack的peek()储存着我们下一个目的地
// 3. 如果是求next, 我们就把stack最顶端的node pop()出来, 但特别注意这里还没结束, 就像inorder一样我们是在pop()这个过程中找到了爹, 那么相当于pointer就到了这个爹, 但因为下一次pop()出来的就应该是爹的右边了, 所以我们应该先提前dfs(father)把inorder浏览的所有node都放到stack里去, 不然下一次就没法用当前这个爹浏览了, 当然你也可以用一个pointer记录这个爹, 但那样hasNext()就得同时判断stack是不是空的已经pointer是不是空的

// Runtime: O(h), Space: O(h), h是dfs这个过程里这整个树的高度

    class BSTIterator{
        Stack<TreeNode> fathers = new Stack<>();
        public BSTIterator(TreeNode root) {
            dfs(root);
        }

        public void dfs(TreeNode cur){
            if(cur == null) return;
            fathers.push(cur);
            dfs(cur.left); //dfs是inorder一样一直向左下走把所有信息都加进去
        }

        public int next() {
            if(fathers.isEmpty()) return 0;
            TreeNode father = fathers.pop();
            if(father.right != null){
                dfs(father.right);
            }
            return father.val;

        }

        public boolean hasNext() {
            return !fathers.isEmpty();
        }
    }


    class BSTIterator_completelyInorder {
        Stack<TreeNode> fathers = new Stack<>();
        TreeNode pointer;

        public BSTIterator(TreeNode root) {
            dfs(root);
        }

        public void dfs(TreeNode cur){
            if(cur == null) return;
            fathers.push(cur);
            pointer = cur;
            dfs(cur.left); //dfs是inorder一样一直向左下走把所有信息都加进去
        }

        public int next() {
            if(fathers.isEmpty() && pointer == null) return 0;
            if(pointer != null){
                dfs(pointer);
            }
            if(!fathers.isEmpty()){
                TreeNode father = fathers.pop();
                return father.val;
            }
            return 0;
            // if(fathers.isEmpty()){
            //     TreeNode cur = pointer;
            //     dfs(pointer);
            //     if(fathers.isEmpty()){
            //         pointer = null;
            //         return cur.val;
            //     }
            // }
            // TreeNode father = fathers.pop();
            // if
            // return father.val;

        }

        public boolean hasNext() {
            return !fathers.isEmpty() || pointer != null;
        }
    }





    //一开始自己写的

    TreeNode pointer;
    TreeNode origin;
    Stack<TreeNode> fathers;
    public void  BSTIterator(TreeNode root) {
        origin = root;
        pointer = root;
        fathers = new Stack<>();
    }

    public int next() {
        if(pointer == origin){
            return dfs(pointer);
        }
        if(pointer.right != null){
            pointer = pointer.right;
            return dfs(pointer.right);
        }
        TreeNode father;
        if(!fathers.isEmpty()) pointer = fathers.pop();
        return pointer.val;

    }

    public int dfs(TreeNode father){
        if(father.left == null) return father.val;
        TreeNode cur = father;
        fathers.add(cur);
        father = father.left;
        return dfs(father);
    }

    public boolean hasNext() {
        if(pointer == origin) return pointer.left == null;
        if(pointer.right != null) return true;
        return !fathers.isEmpty();
    }




}
