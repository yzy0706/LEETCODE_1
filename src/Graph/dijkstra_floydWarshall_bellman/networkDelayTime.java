package Graph.dijkstra_floydWarshall_bellman;

import java.util.*;

public class networkDelayTime {
    // Floyd-warshall解法, 主要就是用dp[][]循环N次
    public int networkDelayTime_Floyd(int[][] times, int N, int K) {
        int[][] dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j] = i == j ? Integer.MAX_VALUE : 0;
            }
        }
        for (int[] time : times) {
            dp[time[0] - 1][time[1] - 1] = time[2];
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (dp[i][k] + dp[k][j] < dp[i][j]) {
                        dp[i][j] = dp[i][k] + dp[k][j];
                    }
                }
            }
        }

        int res = 0;
        for (int i = 0; i < N; i++) {
            if (dp[K - 1][i] == Integer.MAX_VALUE) return -1;
            if (dp[K - 1][i] > res) res = dp[K - 1][i];
        }
        return res;
    }


    //狄杰斯特拉： forloop解法，每次whileloop循环还是会去找所有目的地里面当前距离start最近的那个
    Map<Integer, Integer> distances;

    public int networkDelayTime_forloop(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int[] time : times) {
            if (!map.containsKey(time[0])) map.put(time[0], new ArrayList<int[]>());
            map.get(time[0]).add(new int[]{time[1], time[2]});
        }

        distances = new HashMap<>();
        for (int i = 1; i < N + 1; i++) {
            distances.put(i, Integer.MAX_VALUE);
        }
        distances.put(K, 0);
        boolean[] visited = new boolean[N + 1];
        PriorityQueue<Integer> curStops = new PriorityQueue<>(N, Comparator.comparing(i -> distances.get(i)));

        for (int i : distances.keySet()) {
            curStops.offer(i);
        }

        while (true) {
            int candNode = -1;
            int candDist = Integer.MAX_VALUE;
            for (int i = 1; i <= N; ++i) {
                if (!visited[i] && distances.get(i) < candDist) {
                    candDist = distances.get(i);
                    candNode = i;
                }
            }

            if (candNode < 0) break;
            visited[candNode] = true;
            if (map.containsKey(candNode))
                for (int[] info : map.get(candNode))
                    distances.put(info[0],
                            Math.min(distances.get(info[0]), distances.get(candNode) + info[1]));
        }

        int res = 0;
        for (int i : distances.values()) {
            if (i == Integer.MAX_VALUE) return -1;
            if (i > res) res = i;
        }


        return res;

    }


    //狄杰斯特拉： PriorityQueue的做法， 直接放在priorityQueue里面每次去比较当前所有可用的中转站到start的距离

    public int n;

    class Point {
        private int pos;
        private double prob;
        private List<Point> destinations;
        private double[] probs;

        public Point(int pos) {
            this.pos = pos;
            prob = 0.0;
            destinations = new ArrayList<>();
            probs = new double[n + 1];
        }

        private void addDes(Point des, double prob) {
            this.destinations.add(des);
            this.probs[des.pos] = prob;
        }
    }


    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        Map<Integer, Point> map = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            Point cur0 = new Point(edge[0]);
            Point cur1 = new Point(edge[1]);

            cur0.addDes(cur1, succProb[i]);
            map.put(edge[0], cur0);

            cur1.addDes(cur0, succProb[i]);
            map.put(edge[1], cur1);
        }

        Point startPoint;
        if (map.containsKey(start)) startPoint = map.get(start);
        else return 0;

        Comparator<Point> com = (p1, p2) -> {
            return (int) startPoint.probs[p2.pos] - (int) startPoint.probs[p1.pos];
        };

        PriorityQueue<Point> stops = new PriorityQueue<>(com);

        boolean[] visited = new boolean[n + 1];
        stops.offer(startPoint);
        return 0;
//        while (!stops.isEmpty()) {
//            Point curStop = stops.poll();
//            visited[curStop.pos] = true;
//            for (Point des : curStop.destinations) {
//                if (probs.get(des) < probs.get(curStop) * edge[1]) {
//                    probs.put(des, probs.get(curStop) * edge[1]);
//                    if (!visited[des]) stops.offer(des);
//                }
//            }
//        }
//
//        int res = 0;
//        for (int i : probs.values()) {
//            if (i == Integer.MIN_VALUE) return 0;
//            if (i > res) res = i;
//        }
//
//        return res;
    }


    // Bellman-Ford 解法

    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] i : times) {
            graph.putIfAbsent(i[0], new ArrayList<>());
            graph.get(i[0]).add(new int[]{i[1], i[2]});
        }
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[K] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.offer(K);
        while (!q.isEmpty()) {
            int curr = q.poll();
            int t = dist[curr];
            if (!graph.containsKey(curr))
                continue;
            for (int[] i : graph.get(curr)) {
                if (dist[i[0]] > t + i[1]) {
                    dist[i[0]] = t + i[1];
                    q.offer(i[0]);
                }
            }
        }
        int res = 0;
        for (int i = 1; i < dist.length; i++) {
            if (dist[i] == Integer.MAX_VALUE)
                return -1;
            res = Math.max(res, dist[i]);
        }
        return res;
    }

}



    //刀写的
//        public:
//        int networkDelayTime(vector<vector<int>>& times, int N, int K) {
//            unordered_map<int, vector<pair<int, int>>> graph;
//            vector<int> dist(N + 1, INT_MAX);
//            unordered_set<int> visited;
//            priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
//
//            for (vector<int> edge: times) {
//                graph[edge[0]].push_back(make_pair(edge[1], edge[2]));
//            }
//
//            pq.push(make_pair(0, K));
//            dist[K] = 0;
//            while (!pq.empty()) {
//                int node = pq.top().second;
//                pq.pop();
//                if (visited.find(node) != visited.end()) continue;
//                visited.insert(node);
//                for (pair<int, int> edge: graph[node]) {
//                    int next = edge.first, time = edge.second;
//                    if (dist[node] + time < dist[next]) {
//                        dist[next] = dist[node] + time;
//                        pq.push(make_pair(dist[next], next));
//                    }
//                }
//            }
//            int max_dist = 0;
//            for (int i = 1; i < dist.size(); ++i) {
//                max_dist = max(dist[i], max_dist);
//            }
//            return max_dist != INT_MAX ? max_dist : -1;
//        }
//    }

