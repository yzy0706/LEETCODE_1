package Graph.BFS.topologicalSort;

import java.util.LinkedList;
import java.util.Queue;

public class CourseScheduleII {
    // 做法: topolical(bfs)
    // 1. 先记录indegree, 找到indegree是0的位置, 加到queue里
    // 2. 做bfs, res[pos ++] = queue.poll(), forloop把当前在上的课作为prerequisite的课 cur, 每次indegree[cur] --;, 如果indegree[cur] == 0, queue.offer(cur);
    // Runtime: O(n^2), Space: O(n)

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] res = new int[numCourses];
        int[] indegree = new int[numCourses];

        for(int i = 0; i < prerequisites.length; i++){
            indegree[prerequisites[i][0]]++;
        } //每一门课需要prerequisite的数量都是indegree[i];

        Queue<Integer> bfs = new LinkedList<Integer>();

        for(int i = 0; i < numCourses; i++){
            if(indegree[i] == 0) bfs.offer(i);
        } //如果有一门课需要的prerequisite数量是0, 则加入到bfs里


        int numTaken = bfs.size(), pos = 0;

        while(!bfs.isEmpty()){
            int taking = bfs.poll();
            res[pos++] = taking;
            for(int i = 0; i < prerequisites.length; i++){
                if(prerequisites[i][1] == taking){
                    int cur = prerequisites[i][0];
                    indegree[cur]--;
                    if(indegree[cur] == 0){
                        bfs.offer(cur);
                        numTaken++;
                    }
                }
            }
        }


        if(numTaken == numCourses) return res;
        return new int[0];
    }
}
