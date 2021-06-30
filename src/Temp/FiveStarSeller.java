package Temp;

import java.util.List;
import java.util.PriorityQueue;

public class FiveStarSeller {
    // 做法： 主要就是用到了pq来比较哪一个product加入1个五星好评以后, 五星好评百分比的增加最多
    // Runtime: O(nlog(n)), Space: O(nlog(n))

    static class Product{
        int review;
        int fsReview;
        double ratio;
        double increase;

        public Product(int review, int fsReview){
            this.review = review;
            this.fsReview = fsReview;
            this.ratio = (double) fsReview/ review;
            this.increase = (double) (fsReview + 1)/ (review + 1) - ratio;
        }

    }

    public static int fiveStarSeller(List<List<Integer>> ratings, int threshold){
        int len = ratings.size();
        PriorityQueue<Product> pq = new PriorityQueue<Product>((a,b) -> Double.compare(b.increase, a.increase));
        double ratios = 0;
        for(List<Integer> rating : ratings) {
            Product cur = new Product(rating.get(1), rating.get(0));
            pq.offer(cur);
            ratios += cur.ratio;
        }
        int res = 0;
        boolean match = false;
        while (!pq.isEmpty()){
            Product cur = pq.poll();
            Product increased = new Product(cur.review + 1, cur.fsReview + 1);
            pq.offer(increased);
            res ++;
            ratios += increased.increase;
            if((double)ratios / len >= threshold){
                match = true;
                break;
            }
        }
        return match ? res : -1;
    }
}
