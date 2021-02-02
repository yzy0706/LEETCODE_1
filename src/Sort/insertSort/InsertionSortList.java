package Sort.insertSort;

import java.util.ArrayList;
import java.util.List;

public class InsertionSortList {
    //做法:主要就是把当前的linked list所有的值放到一个int[]里再做一个insert sort, 然后创建一个linked list装回去即可
    //Runtime: O(n^ 2), 在一个forloop里还循环了一次, Space: O(n),用一个int[]来装载了所有的值
    public class ListNode {
       int val;
       ListNode next;
       ListNode() {}
       ListNode(int val) { this.val = val; }
       ListNode(int val, ListNode next) { this.val = val; this.next = next; }
   }

    public ListNode insertionSortList(ListNode head) {
        if(head == null) return null;

        List<Integer> vals = new ArrayList<>();
        while(head != null){
            vals.add(head.val);
            head = head.next;
        }
        int[] arr = new int[vals.size()];
        for(int i = 0; i < vals.size(); i++) arr[i] = vals.get(i);

        for(int i= 0; i < arr.length-1; i++){
            for(int j = i+1; j > 0; j--){
                if(arr[j] < arr[j-1]){
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }
            }
        }

        ListNode res = new ListNode(arr[0]), dummy = new ListNode();
        dummy = res;
        for(int i = 1; i < arr.length; i++){
            res.next = new ListNode(arr[i]);
            res = res.next;
        }
        return dummy;
    }
}
