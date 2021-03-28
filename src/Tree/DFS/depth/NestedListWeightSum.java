package Tree.DFS.depth;

public class NestedListWeightSum {

    // 做法: 我用的dfs的做法, 当前给我的是一个list, 那么我就每次浏览每一个NestInteger, 拿它做dfs
    // 1. 如果当前NestedInteger里面到底了只剩下一个数字了, 就乘以当前depth, 加到res上
    // 2. 如果他还是一个list, 就对于list里面的每一个NestedInteger继续做dfs, 因为每一个NestedInteger有可能是list也有可能是数字

    // Runtime: O(n), n是最深的depth

    //答案没错， 只是这个题给的helper有问题， 所以我直接全部注释了


//    private int res;
//
//    public int depthSum(List<NestedInteger> nestedList) {
//        if (nestedList.size() < 1) return 0;
//        res = 0;
//        for (NestedInteger ni : nestedList) {
//            dfs(ni, 1);
//        }
//        return res;
//    }
//
//    public void dfs(NestedInteger ni, int depth) {
//        if (ni.isInteger()) {
//            res += ni.getInteger() * depth;
//            return;
//        }
//        List<NestedInteger> niList = ni.getList();
//        for (NestedInteger next : niList) {
//            dfs(next, depth + 1);
//        }
//    }
//
//
//    public class NestedInteger {
//        public NestedInteger();
//
//
//        public NestedInteger(int value);
//
//        // @return true if this NestedInteger holds a single integer, rather than a nested list.
//        public boolean isInteger();
//
//        // @return the single integer that this NestedInteger holds, if it holds a single integer
//        // Return null if this NestedInteger holds a nested list
//        public Integer getInteger();
//
//        // Set this NestedInteger to hold a single integer.
//        public void setInteger(int value);
//
//        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
//        public void add(NestedInteger ni);
//
//        // @return the nested list that this NestedInteger holds, if it holds a nested list
//        // Return empty list if this NestedInteger holds a single integer
//        public List<NestedInteger> getList();
//    }
}



