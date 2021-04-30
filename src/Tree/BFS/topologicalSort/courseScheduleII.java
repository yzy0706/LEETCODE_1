package Tree.BFS.topologicalSort;

import java.util.LinkedList;
import java.util.Queue;

public class courseScheduleII {
    //跟courseSchdule一样, 只是每当indegree[taking]等于0的时候要设置res[pos++]= taking; 这里pos是先使用了再++

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
        return res;
    }
}
