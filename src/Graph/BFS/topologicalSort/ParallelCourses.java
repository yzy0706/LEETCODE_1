package Graph.BFS.topologicalSort;

import java.util.*;

public class ParallelCourses {
    // 做法: 这题用的拓扑排序的做法, 用一个int[] indegree来代表一门课需要的pre的个数,
    // 用一个HashMap<Integer, List<Integer>>来记录每一门课和它作为pre对应的所有课程
    // 1. forloop所有的relation, 把map和indegree都填满
    // 2. 把indegree里面所有是0的course都放到queue里面去, 如果从1到n都没有indegree是0的, 证明成环了, 直接return -1;
    // 3. 正常的bfs流程, 每次把queue里面的course全部浏览完并加入indegree变为0的新课程, 然后浏览完一次queue就证明过了一个学期

    // Runtime: O(n), Space: O(n), 无论怎么样只会浏览完所有的课程
    public int minimumSemesters_topo(int n, int[][] relations) {
        int[] indegree = new int[n+1];
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for(int[] relation : relations){
            int pre = relation[0], cour = relation[1];
            map.putIfAbsent(pre, new ArrayList<>());
            map.get(pre).add(cour);
            indegree[cour] ++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 1; i <= n; i++){
            if(indegree[i] == 0) queue.offer(i);
        }
        if(queue.size() == 0) return -1;
        int res = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                int cur = queue.poll();
                if(!map.containsKey(cur)) continue;
                for(int cour : map.get(cur)){
                    indegree[cour] --;
                    if(indegree[cour] == 0){
                        queue.offer(cour);
                    }
                }
            }
            res ++;
        }
        for(int i = 1; i <= n; i++){
            if(indegree[i] != 0) return -1;
        }
        return res;
    }



    public int minimumSemesters(int n, int[][] relations) {
        int res = 0;
        int[] dp = new int[n+1];
        for(int[] relation : relations){
            int pre = relation[0];
            int cur = relation[1];
            if(dp[pre] == 0){
                dp[pre] = 1;
                dp[cur] = Math.max(dp[cur], 2);
            }
            else{
                if(dp[cur] == 0){
                    dp[cur] = dp[pre] + 1;
                }
                else{
                    if(dp[cur] <= dp[pre]){
                        return -1;
                    }
                }
            }
            res = Math.max(res, dp[cur]);
        }
        return res;
    }
}
