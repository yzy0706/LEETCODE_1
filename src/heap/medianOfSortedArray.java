package heap;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class medianOfSortedArray {
    //用两个方向不同的queue分流两个array的数字
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double res = 0;
        int pos1 = 0, pos2 = 0, len1 = nums1.length , len2 = nums2.length;
        Queue<Integer> larger = new PriorityQueue<Integer>();
        Queue<Integer> smaller = new PriorityQueue<>(Collections.reverseOrder());
        if(nums1 == null) return (double)nums2[pos2];
        else if(nums2 == null) return (double)nums1[pos1];

        int i = 0;
        while(i < len1 && i < len2){
            smaller.offer(nums1[i]);
            larger.offer(smaller.poll());
            smaller.offer(nums2[i]);
            larger.offer(smaller.poll());

            // if(nums1[i] < smaller.peek()){
            //     smaller.offer(nums1[i]);
            // }
            // else{}
            // if(nums2[i] < smaller.peek()){
            while (smaller.size() + 1 < larger.size()) smaller.offer(larger.poll());
            i++;
        }

        while (i < len1){
            smaller.offer(nums1[i]);
            larger.offer(smaller.poll());
            while (smaller.size() + 1 < larger.size()) smaller.offer(larger.poll());
            i++;
        }

        while (i < len2){
            smaller.offer(nums2[i]);
            larger.offer(smaller.poll());
            while (smaller.size() + 1 < larger.size()) smaller.offer(larger.poll());
            i++;
        }

        if(smaller.size() == larger.size()) return (double)((smaller.peek() + larger.peek())/2.0);
        else return (double)larger.peek();
    }
}
