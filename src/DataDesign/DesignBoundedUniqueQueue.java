package DataDesign;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class DesignBoundedUniqueQueue {

    // 做法: discussion上给的解法, 主要就是添一个lock的object
    // 1. 在enqueue的方程里:
    //    synchronized(lock){
    //      while(deque.size() == size) lock.wait();
    //      ...
    //      deque.notify();
    //    }
    // 2. 在deque的方程里
    // synchronized(lock){
    //        while(deq.isEmpty()){
    //            lock.wait();
    //        }
    //        res = deq.removeFirst();
    //        lock.notify();
    //    }

    // Runtime: O(1), Space: O(n)

    Deque<Integer> deq;
    int size;
    Object lock;

    public void BoundedBlockingQueue(int capacity) {
        size = capacity;
        deq = new LinkedList<>();
        lock = new Object();
    }

    public void enqueue(int element) throws InterruptedException {
        synchronized(lock){
            while(deq.size() == size){
                lock.wait();
            }
            deq.addLast(element);
            lock.notify();
        }
    }

    public int dequeue() throws InterruptedException {
        int res = 0;
        synchronized(lock){
            while(deq.isEmpty()){
                lock.wait();
            }
            res = deq.removeFirst();
            lock.notify();
        }
        return res;
    }

    public int size() {
        return deq.size();
    }



    //第一遍做的
    Queue<Integer> cur, backup;
    int call;

    public void  BoundedBlockingQueue_1(int capacity) {
        size = capacity;
        call = 0;
        cur = new LinkedList<>();
        backup = new LinkedList<>();
    }

    public void enqueue_1(int element) throws InterruptedException {
        if(cur.size() == size){
            backup.offer(element);
            return;
        }
        else cur.offer(element);
        if(call > 0){
            dequeue_1();
        }
    }

    public int dequeue_1() throws InterruptedException {
        if(cur.isEmpty()){
            call ++;
            return -1;
        }
        else{
            int res = cur.poll();
            if(!backup.isEmpty()){
                cur.offer(backup.poll());
            }
            if(call > 0) call --;
            return res;
        }
    }

    public int size_1() {
        return cur.size();
    }
}
