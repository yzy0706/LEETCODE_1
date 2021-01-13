package Sort.MergeSort;


import java.util.ArrayList;
import java.util.List;

public class SortList {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode sortList(ListNode head) {
        //做法:要求是O(nlog(n))时间和O(1)时间, 我想到了merge sort或者heap sort, 可以先用merge sort试试
        //Runtime: O(nlog(n))，我们每次一分为二是log(n)，做n次所以是O(nlog(n))
        if (head == null) return null;
        List<Integer> vals = new ArrayList<>();

        while (head != null) {
            vals.add(head.val);
            head = head.next;
        }

        int[] arr = new int[vals.size()];
        for (int i = 0; i < vals.size(); i++) {
            arr[i] = vals.get(i);
        }


        mergeSort(arr, 0, arr.length - 1);

        ListNode res = new ListNode(arr[0]), dummy = res;

        for (int i = 1; i < arr.length; i++) {
            res.next = new ListNode(arr[i]);
            res = res.next;
        }

        return dummy;

    }

    private void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, r);
        }

    }

    private void merge(int[] arr, int l, int r) {
        int m = (l + r) / 2;
        int i = l, j = m + 1, k = 0;
        int[] temp = new int[arr.length];
        while (i <= m && j <= r) {
            if (arr[i] < arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        while (i <= m) temp[k++] = arr[i++];
        while (j <= r) temp[k++] = arr[j++];

        for (int ind = 0; ind < k; ind++) {
            arr[l + ind] = temp[ind];
        }
    }
}
