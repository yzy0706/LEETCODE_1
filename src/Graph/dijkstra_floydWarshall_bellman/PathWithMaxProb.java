package Graph.dijkstra_floydWarshall_bellman;

import java.util.*;

public class PathWithMaxProb {
    //Floyd-Warshall方法
    public double maxProbability_floyd_warshall(int n, int[][] edges, double[] succProb, int start, int end) {
        double[][] dp = new double[n][n];
        for(int i = 0; i < edges.length; i++){
            int[] edge = edges[i];
            dp[edge[0]][edge[1]] = dp[edge[1]][edge[0]] = succProb[i];
        }
        for(int k = 0; k < n; k++){
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] * dp[k][j]);
                }
            }
        }
        return dp[start][end];
    }



    // 最后一遍复习的graph方法:
    // 做法: 用一个HashMap<Integer, double[]> map来建立graph, 用一个double[] startProbs来记录start到各个点的大的概率
    // 1. 建立graph和startProbs 注意: graph这里是双向的, 所以要记录两个方向的概率
    // 2. 因为现在startProbs还是空的, 所以要用 double[2]{start, 1.0}; 来记录每个城市当前的id和start到它的概率, 并且随时更新start到当前这个城市的neighbour的概率
    // Runtime: O(n), Space: O(n)
    public double maxProbability_reviewedNoCity(int n, int[][] edges, double[] succProb, int start, int end) {
        HashMap<Integer, List<double[]>> map = new HashMap<>();
        double[] startProbs = new double[n];
        for(int i = 0; i < edges.length; i++){
            int[] edge = edges[i];
            int s = edge[0], e = edge[1];
            map.putIfAbsent(s, new ArrayList<>());
            map.putIfAbsent(e, new ArrayList<>());
            map.get(s).add(new double[]{e, succProb[i]});
            map.get(e).add(new double[]{s, succProb[i]}); //双向的
        }
        // startProbs[start] = 1.0;
        Queue<double[]> queue = new LinkedList<>();
        queue.offer(new double[]{start, 1.0});
        while(!queue.isEmpty()){
            double[] cur = queue.poll();
            int mid = (int)cur[0];
            double prob = cur[1];
            if(!map.containsKey(mid)) continue;
            List<double[]> neighbours = map.get(mid);
            for(int j = 0; j < neighbours.size(); j++){
                int neighbour = (int)neighbours.get(j)[0];
                double nextProb = neighbours.get(j)[1];
                if(startProbs[neighbour] >= prob * nextProb) continue;
                startProbs[neighbour] = prob * nextProb;
                queue.offer(new double[]{neighbour, prob * nextProb});
            }
        }
        return startProbs[end];
    }




    class City{
        int id;
        double prob;

        public City(int id, double prob){
            this.id = id;
            this.prob = prob;
        }
    }
    public double maxProbability_city(int n, int[][] edges, double[] succProb, int start, int end) {
        double res = 0;
        HashMap<Integer, List<double[]>> map = new HashMap<>();
        double[] startProbs = new double[n];
        for(int i = 0; i < edges.length; i++){
            int[] edge = edges[i];
            int s = edge[0], e = edge[1];
            map.putIfAbsent(s, new ArrayList<>());
            map.putIfAbsent(e, new ArrayList<>());
            map.get(s).add(new double[]{e, succProb[i]});
            map.get(e).add(new double[]{s, succProb[i]}); //双向的
            // if(s == start) startProbs[e] = succProb[i];
            // if(e == start) startProbs[s] = succProb[i];
        }
        // startProbs[start] = 1.0;
        Queue<City> queue = new LinkedList<>();
        queue.offer(new City(start, 1.0));
        while(!queue.isEmpty()){
            City cur = queue.poll();
            int mid = cur.id;
            double prob = cur.prob;
            if(!map.containsKey(mid)) continue;
            List<double[]> neighbours = map.get(mid);
            for(int j = 0; j < neighbours.size(); j++){
                int neighbour = (int)neighbours.get(j)[0];
                double nextProb = neighbours.get(j)[1];
                if(startProbs[neighbour] >= prob * nextProb) continue;
                startProbs[neighbour] = prob * nextProb;
                queue.offer(new City(neighbour, prob * nextProb));
            }
        }
        return startProbs[end];
    }





    public double maxProbability_double(int n, int[][] edges, double[] succProb, int start, int end) {
        double res = 0;
        HashMap<Integer, List<double[]>> map = new HashMap<>();
        double[] startProbs = new double[n];
        for(int i = 0; i < edges.length; i++){
            int[] edge = edges[i];
            int s = edge[0], e = edge[1];
            map.putIfAbsent(s, new ArrayList<>());
            map.putIfAbsent(e, new ArrayList<>());
            map.get(s).add(new double[]{e, succProb[i]});
            map.get(e).add(new double[]{s, succProb[i]}); //双向的
            if(s == start) startProbs[e] = succProb[i];
            if(e == start) startProbs[s] = succProb[i];
        }
        startProbs[start] = 1.0;
        Queue<double[]> queue = new LinkedList<>();
        queue.offer(new double[]{start, 1.0});
        while(!queue.isEmpty()){
            double[] cur = queue.poll();
            int mid = (int)cur[0];
            double prob = cur[1];
            if(!map.containsKey(mid)) continue;
            List<double[]> neighbours = map.get(mid);
            for(int j = 0; j < neighbours.size(); j++){
                int neighbour = (int)neighbours.get(j)[0];
                double nextProb = neighbours.get(j)[1];
                if(startProbs[neighbour] > prob * nextProb) continue;
                startProbs[neighbour] = prob * nextProb;
                queue.offer(new double[]{neighbour, prob * nextProb});
            }
        }
        return startProbs[end];
    }




    // 做法： 重新写了一遍， 没有新建class pair， 但是用了 HashMap<Integer, double[]> map 来组建graph
    // 注意： 是双向的， 所以浏览edge的时候两个方向的距离都要记录一下
    // Runtime: O(n), Space: O(n)

    public double maxProbability_reviewed(int n, int[][] edges, double[] succProb, int start, int end) {
        double res = 0;
        HashMap<Integer, double[]> map = new HashMap<>();
        double[] startProbs = new double[n];
        for(int i = 0; i < edges.length; i++){
            int[] edge = edges[i];
            int s = edge[0], e = edge[1];
            map.putIfAbsent(s, new double[n]);
            map.putIfAbsent(e, new double[n]);
            map.get(s)[e] = succProb[i];
            map.get(e)[s] = succProb[i]; //双向的
            if(s == start) startProbs[e] = succProb[i];
            if(e == start) startProbs[s] = succProb[i];
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < queue.size(); i++){
                int mid = queue.poll();
                if(!map.containsKey(mid)) continue;
                double[] midProbs = map.get(mid);
                for(int j = 0; j < midProbs.length; j++){
                    if(startProbs[mid] * midProbs[j] > startProbs[j]) startProbs[j] = startProbs[mid] * midProbs[j];
                    if(j == end) res = Math.max(res, startProbs[j]);
                    else queue.offer(i);
                }
            }
        }
        return res;
    }





     //用了pair， 但没用log，过了， 但runtime很慢, 这个
        class Pair {
            private int des;
            private double prob;

            public Pair(int des, double prob){
                this.des = des;
                this.prob = prob;
            }

        }





        public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
            Map<Integer, List<Pair>> map = new HashMap<>();

            for (int i = 0; i < edges.length; i++) {
                int[] edge = edges[i];
                int pos0 = edge[0];
                int pos1 = edge[1];
                Pair pair0 = new Pair(pos0, succProb[i]);
                Pair pair1 = new Pair(pos1, succProb[i]);
                if(!map.containsKey(pos0)) map.put(pos0, new ArrayList<Pair>());
                if(!map.containsKey(pos1)) map.put(pos1, new ArrayList<Pair>());
                map.get(pos0).add(pair1);
                map.get(pos1).add(pair0);
            }

            boolean[] visited = new boolean[n+1];
            if(!map.containsKey(start)) return 0;
            List<Pair> distList = map.get(start);
            double[] dists = new double[n];
            for(Pair p : distList){
                dists[p.des] = p.prob;
            }
            PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
                return dists[b] < dists[a] ? -1 : 1;
            });
            dists[start] = 1;
            pq.offer(start);

            while (!pq.isEmpty()) {
                int curStop = pq.poll();
                double curProb = dists[curStop];
                visited[curStop] = true;
                List<Pair> dests = map.get(curStop);
                for(Pair p : dests){
                    int nextDest = p.des;
                    double midProb = p.prob;
                    double nextProb = dists[nextDest];
                    if(nextProb < curProb * midProb){
                        nextProb = curProb * midProb;
                        dists[nextDest] = nextProb;
                    }
                    if(!visited[nextDest]) pq.offer(nextDest);
                }
            }

            return dists[end];
        }





    class pair{
        int des;
        double prob;

        public pair(int des, double prob){
            this.des = des;
            this.prob = prob;
        }
    }

    public double maxProbability_reviewedByPair(int n, int[][] edges, double[] succProb, int start, int end) {
        double res = 0;
        HashMap<Integer, List<pair>> map = new HashMap<>();
        double[] startProbs = new double[n];
        for(int i = 0; i < edges.length; i++){
            int[] edge = edges[i];
            int s = edge[0], e = edge[1];
            map.putIfAbsent(s, new ArrayList<>());
            map.putIfAbsent(e, new ArrayList<>());
            map.get(s).add(new pair(e, succProb[i]));
            map.get(e).add(new pair(s, succProb[i])); //双向的
            if(s == start) startProbs[e] = succProb[i];
            if(e == start) startProbs[s] = succProb[i];
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < queue.size(); i++){
                int mid = queue.poll();
                if(!map.containsKey(mid)) continue;
                List<pair> midProbs = map.get(mid);
                for(int j = 0; j < midProbs.size(); j++){
                    pair cur = midProbs.get(i);
                    int next = cur.des;
                    double midProb = cur.prob;
                    if(startProbs[mid] * midProb > startProbs[next]) startProbs[next] = startProbs[mid] * midProb;
                    if(next == end) res = Math.max(res, startProbs[next]);
                    else queue.offer(next);
                }
            }
        }
        return res;
    }


        //刀要我用的pair和log同时使用的解法，没过
//    class Pair {
//        private int des;
//        private double prob;
//
//        public Pair(int des, double prob){
//            this.des = des;
//            this.prob = prob;
//        }
//
//    }
//
//    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
//        // create nodes
//        Map<Integer, List<Pair>> map = new HashMap<>();
//
//        for (int i = 0; i < edges.length; i++) {
//            int[] edge = edges[i];
//            int pos0 = edge[0];
//            int pos1 = edge[1];
//            Pair pair0 = new Pair(pos0, Math.log(succProb[i]));
//            Pair pair1 = new Pair(pos1, Math.log(succProb[i]));
//            if(!map.containsKey(pos0)) map.put(pos0, new ArrayList<Pair>());
//            if(!map.containsKey(pos1)) map.put(pos1, new ArrayList<Pair>());
//            map.get(pos0).add(pair1);
//            map.get(pos1).add(pair0);
//        }
//
//        // dijkstra_floydWarshall_bellman
//        boolean[] visited = new boolean[n+1];
//        if(!map.containsKey(start)) return 0;
//        List<Pair> distList = map.get(start);
//        double[] dists = new double[n+1];
//        for(Pair p : distList){
//            dists[p.des] = p.prob;
//        }
//        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
//            return dists[b] < dists[a] ? -1 : 1;
//        });
//        pq.offer(start);
//
//        while (!pq.isEmpty()) {
//            int curStop = pq.poll();
//            double curProb = dists[curStop];
//            visited[curStop] = true;
//            List<Pair> dests = map.get(curStop);
//            for(Pair p : dests){
//                int nextDest = p.des;
//                double midProb = p.prob;
//                double nextProb = dists[nextDest];
//                if(nextProb < curProb + midProb){
//                    nextProb = curProb + midProb;
//                    dists[nextDest] = nextProb;
//                }
//                if(!visited[nextDest]) pq.offer(nextDest);
//            }
//        }
//
//        return Math.pow(10, dists[end]);
//    }
//
//
//
//
//    //discussion里面给的dijkstra的解法，建一个node，很复杂不建议用
//    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
//        class Node {
//            private int id;
//            private double prob;
//            private List<Node> children;
//            private double[] probList;
//
//            public Node(int id) {
//                this.id = id;
//                this.prob = 0.0;
//                this.children = new ArrayList<>();
//                this.probList = new double[n+1];
//            }
//
//            public void addChild(Node node, double prob) {
//                this.children.add(node);
//                this.probList[node.id] = prob;
//            }
//        }
//        // create nodes
//        Map<Integer, Node> nodeMap = new HashMap<>();
//        for (int i = 0; i < n; i++) {
//            nodeMap.put(i, new Node(i));
//        }
//
//        // add children
//        for (int i = 0; i < edges.length; i++) {
//            Node node1 = nodeMap.get(edges[i][0]);
//            Node node2 = nodeMap.get(edges[i][1]);
//            node1.addChild(node2, succProb[i]);
//            node2.addChild(node1, succProb[i]);
//        }
//
//        // dijkstra_floydWarshall_bellman
//        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> {
//            return b.prob < a.prob ? -1 : 1;
//        });
//        Node src = nodeMap.get(start);
//        src.prob = 1.0;
//        pq.offer(src);
//        Set<Node> seen = new HashSet<>();
//        while (!pq.isEmpty()) {
//            Node node = pq.poll();
//            if (seen.contains(node)) {
//                continue;
//            }
//            seen.add(node);
//            for (int i = 0; i < node.children.size(); i++) {
//                Node child = node.children.get(i);
//                double childProb = node.probList[child.id];
//                if (seen.contains(child)) {
//                    continue;
//                }
//
//                double currProb = node.prob * childProb;
//                if (child.prob < currProb) {
//                    child.prob = currProb;
//                }
//                pq.offer(child);
//            }
//        }
//
//        return nodeMap.get(end).prob;
//    }
//
//
//
//
//
//
//    // 狄杰斯特拉， 照着复杂的写法写的
//    class Point{
//        private int pos;
//        // private double prob;
//        private List<Point> destinations;
//        private double[] probs;
//
//        public Point(int pos, int n){
//            this.pos = pos;
//            destinations = new ArrayList<>();
//            probs = new double[n+1];
//        }
//
//        private void addDes(Point des, double prob){
//            this.destinations.add(des);
//            this.probs[des.pos] = prob;
//        }
//    }
//
//
//    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
//        Map<Integer, Point> map = new HashMap<>();
//
//        for(int i = 0; i < edges.length; i++){
//            int[] edge = edges[i];
//            Point cur0 = new Point(edge[0], n);
//            Point cur1 = new Point(edge[1], n);
//
//            cur0.addDes(cur1, succProb[i]);
//            map.put(edge[0], cur0);
//
//            cur1.addDes(cur0, succProb[i]);
//            map.put(edge[1], cur1);
//        }
//
//
//
//
//        Point startPoint;
//        if(map.containsKey(start)) {
//            startPoint = map.get(start);
//            startPoint.probs[start] = 1.0;
//        }
//        else return 0;
//
//        // Comparator<Point> com = (p1, p2) -> {
//        //     return (int)startPoint.probs[p2.pos] - (int)startPoint.probs[p1.pos];
//        // };
//
//        boolean[] visited = new boolean[n+1];
//        double[] sProbs = startPoint.probs;
//        PriorityQueue<Point> stops = new PriorityQueue<>( (a, b) -> {
//            return sProbs[a.pos] < sProbs[b.pos]? 1 : -1;
//        });
//
//        stops.offer(startPoint);
//        while(!stops.isEmpty()){
//            Point curStop = stops.poll();
//            visited[curStop.pos] = true;
//            double[] curProbs = curStop.probs;
//
//            for(Point des : curStop.destinations){
//                int desID = des.pos;
//                if(sProbs[desID] < sProbs[curStop.pos] * curProbs[desID]){
//                    sProbs[desID] = sProbs[curStop.pos] * curProbs[desID];
//                    if(!visited[desID]) stops.offer(map.get(desID));
//                }
//            }
//        }
//
//
//        return sProbs[end];
//    }
}
