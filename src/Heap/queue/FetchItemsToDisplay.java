package Heap.queue;

import java.util.*;

public class FetchItemsToDisplay {

    //做法： 简单来说就是用了一个pq按照sortParameter和sortOrder来排列所有的name，
    // 然后再按照itemsPerPage一页一页的把pq里的items全部poll()出来，直到我们想要的pageNumber那一页的所有item都被加到res里
    // 注意：要注意pq里面是不是poll()完了， 如果是的话就没必要继续poll()下去了
    // Runtime: O(klog(n)) , Space: O(nlog(n)), k是pageNumber * itemsPerpage

    PriorityQueue<String> pq;
    public List<String> fetchItemsToDisplay(int sortParameter, int sortOrder, int itemsPerPage, int pageNumber, int numOfItems, HashMap<String, int[]> items){
        if(sortParameter == 0) pq = new PriorityQueue<>((a, b) -> sortOrder == 0 ? a.compareTo(b) : b.compareTo(a));
        else if(sortParameter == 1) pq = new PriorityQueue<>((a, b) -> sortOrder == 0 ?  items.get(a)[0] - items.get(b)[0] : items.get(b)[0] - items.get(a)[0]);
        else if(sortParameter == 2) pq = new PriorityQueue<>((a, b) -> sortOrder == 0 ? items.get(a)[1] - items.get(b)[1] : items.get(b)[1] - items.get(a)[1]);
        for(String name : items.keySet()) pq.offer(name);
        List<String> res = new ArrayList<>();
        for(int i = 0; i <= pageNumber; i++){
            int cnt = 0;
            while(!pq.isEmpty() && cnt < itemsPerPage) {
                if (i == pageNumber) res.add(pq.poll());
                else pq.poll();
                cnt++;
            }
            if(pq.isEmpty()) break;
        }
        return res;
    }














    //做法: 主要就是建了一个item的class, 然后建一个pq, 这个pq要注意sortOrder和sortParameter,
    // 我们需要用一个pq来存map里所有的值, 如果sortOrder = 0, 那他需要这个书是从小到大的排序的,
    // 但我们的pq不应该是maxHeap, 我们正好相反要是minHeap, 因为最后一步我们要把所有比目标页数的页数小的页数的item都poll()出来,
    // 然后再把目标页的item的name全部poll()到一个list里才算完成
    //Runtime: 应该就是O(Nlog(N)), space也是O(Nlog(N)), 因为map也只是N


    class item{
        String name;
        int relevance;
        int price;

        public item(String name, int relevance, int price){
              this.name = name;
              this.relevance = relevance;
              this.price = price;
        }

    }
    public List<String> fetchItemsToDisplay(int numOfItems, HashMap<String, int[]> items, int sortParameter, int sortOrder, int itemsPerPage, int pageNumber){
        Comparator<item> com = (o1,o2) -> {
            //如果order == 1，这个书应该是降序，所以pq是应该是maxHeap，这样书越往后翻数才越小， 因为大的已经被poll掉了， pq也是从大到小， 跟list是一个顺序
            if (sortOrder == 1) {
                if (sortParameter == 0) return o2.name.compareTo(o1.name);
                if (sortParameter == 1) return o2.relevance - o1.relevance;
                if (sortParameter == 2) return o2.price - o1.price;
            }

            if (sortParameter == 0) return o1.name.compareTo(o2.name);
            if (sortParameter == 1) return o1.relevance - o2.relevance;
            if (sortParameter == 2) return o1.price - o2.price;
            return 0;
        };

//        Queue<item> pq = new PriorityQueue<>(com);
        List<item> list = new ArrayList<>();

        for(String name : items.keySet()){
            list.add(new item(name, items.get(name)[0], items.get(name)[1]));
        }

        list.sort(com);
        List<String> res = new ArrayList<>();

        for(int i = pageNumber * itemsPerPage; i < list.size() && i < (pageNumber+1) * itemsPerPage; i++){
            res.add(list.get(i).name);
        }

        return res;


    }


    //pq写法
    public List<String> fetchItemsToDisplay_pq(int numOfItems, HashMap<String, int[]> items, int sortParameter, int sortOrder, int itemsPerPage, int pageNumber){
        Comparator<item> com = (o1,o2) -> {
            //如果order == 1， pq是应该是maxHeap，这样书越往后翻数才越小， 因为大的已经被poll掉了
            if (sortOrder == 1) {
                if (sortParameter == 0) return o2.name.compareTo(o1.name);
                if (sortParameter == 1) return o2.relevance - o1.relevance;
                if (sortParameter == 2) return o2.price - o1.price;
            }

            if (sortParameter == 0) return o1.name.compareTo(o2.name);
            if (sortParameter == 1) return o1.relevance - o2.relevance;
            if (sortParameter == 2) return o1.price - o2.price;
            return 0;
        };

        Queue<item> pq = new PriorityQueue<>(com);

        for(String name : items.keySet()){
            pq.offer(new item(name, items.get(name)[0], items.get(name)[1]));
        }

        for (int i = 0; i < pageNumber; i++){
            int cnt = 0;
            while(cnt < itemsPerPage){
                pq.poll();
                cnt++;
            }
        }

        List<String> res = new ArrayList<>();
        while(!pq.isEmpty() && res.size() < itemsPerPage){
            res.add(pq.poll().name);
        }

        return res;


    }

}
