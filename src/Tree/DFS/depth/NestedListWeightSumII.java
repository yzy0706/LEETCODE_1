package Tree.DFS.depth;

public class NestedListWeightSumII {
    // 做法: 这题是dfs的题
    // 1. 我是先用一个dfs方程把当前最深的深度求出来了
    // 2. 然后再用另外一个DFS, 从最原始的nestedList开始, 带着最深的深度和当前的NestedInteger去更新res,
    //      a. 如果当前NI是一个数字, res就加上当前的深度 * 数字;
    //      b. 如果当前NI是一个list, 就继续往下dfs, 而且深度也逐渐变小
    // Runtime: O(depth * n), n是nestlist里面ni的个数, space: O(1);


//    private int res;
//    public int depthSumInverse(List<NestedInteger> nestedList) {
//        if(nestedList.size() < 1) return 0;
//        int depth = 0;
//        for(NestedInteger ni : nestedList){
//            depth = Math.max(depth, depthDFS(ni));
//        }
//        for(NestedInteger ni : nestedList){
//            update(ni, depth);
//        }
//        return res;
//    }
//
//    public int depthDFS(NestedInteger ni){
//        if(ni.isInteger()){
//            return 1;
//        }
//        int depth = 0;
//        for(NestedInteger next : ni.getList()){
//            depth = Math.max(depth, depthDFS(next) + 1);
//        }
//        return depth;
//    }
//
//    public void update(NestedInteger ni, int depth){
//        if(ni.isInteger()){
//            res += ni.getInteger() * depth;
//            return;
//        }
//        else{
//            for(NestedInteger nextNI : ni.getList()){
//                update(nextNI, depth-1);
//            }
//        }
//    }
}
