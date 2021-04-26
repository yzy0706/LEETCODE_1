package Heap;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

class kClosestPointsToOrigin {






    class pair {
        int x, y;

        public pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    class com implements Comparator<pair> {
        public int compair(pair a, pair b) {
            return (a.x * a.x + a.y * a.y) - (b.x * b.x - b.y * b.y);
        }

        @Override
        public int compare(pair o1, pair o2) {
            return 0;
        }
    }

    public int[][] kClosest(int[][] points, int k) {
        int w = points.length;
        if (w == 0) return new int[][]{{0, 0}};
        int l = w > 0 ? points[0].length : 0;
        int[] di = new int[]{0, 1}, dj = new int[]{1, 0};

        boolean[][] visited = new boolean[w][l];
        Queue<pair> queue = new PriorityQueue<pair>(k, new com());
        queue.offer(new pair(0, 0));

        for (int i = 0; i <  - 1; i++) {
            pair cur = queue.poll();
            for (int j = 0; j < 2; j++) {
                int curi = cur.x + di[j];
                int curj = cur.y + dj[j];
                if (!visited[curi][curj] && i >= 0 && j >= 0 && curi < w && curj < l) {
                    queue.offer(new pair(curi, curj));
                    visited[curi][curj] = true;
                }
            }
        }

        int[][] res = new int[w][l];
        int i = 1;
        pair head = queue.poll();
        int distance = head.x * head.x + head.y * head.y;
        res[0] = new int[]{head.x, head.y};

        while (!queue.isEmpty()) {
            pair cur = queue.poll();
            int curDistance = cur.x * cur.x + cur.y * cur.y;
            if (curDistance == distance) {
                res[i] = new int[]{cur.x, cur.y};
                i++;
            } else break;
        }

        return res;

    }
}

