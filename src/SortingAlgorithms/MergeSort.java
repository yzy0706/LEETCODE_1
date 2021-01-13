package SortingAlgorithms;

public class MergeSort {
    //做法：主要是在把arr不断循环切割成两块并且合并left到mid、mid+1到end这两个数列的这个过程， 一直到first == last， 而且这个过程都是
    //把左右两边的有序数列进行合并排序（需要用到一个k来记录合并以后的数列的index）
    //Runtime: 每次我们都是对半分， 那么从切成N个单一个数的小数列应该是logN步， 而且每步都得合并左右两个有序数列也就是N次，
    //所以一共是Nlog（N)次

    //上升
    public static void mergeSort(int[] arr, int l, int r){
        if(l < r){
            int m = (l+r)/2;
            mergeSort(arr, l, m);
            mergeSort(arr, m+1, r); //把两边都分割并排序成只有一个的情况
            merge(arr, l, r); //两边都处理完以后再把两边都merge
        }
    }

    public static void merge(int[] arr, int l, int r){
        int i = l, m = (l+r)/2, j = m+1, k = 0;
        int[] temp = new int[arr.length];

        //标准的合并两个有序数列的过程
        while(i <= m && j <= r){
            if(arr[i] < arr[j]){
                temp[k++] = arr[i++];
            }
            else{
                temp[k++] = arr[j++];
            }
        }
        while(i <= m){  //把剩下的数都送到temp里面去， 左右肯定有一个队列已经被排完了
            temp[k++] = arr[i++];
        }
        while(j <= r){
            temp[k++] = arr[j++];
        }

        for(int ind = 0; ind < k; ind++){ //这里k是代表着这个temp的长度，那么从l+0开始+ind，最后一位应该是l+k-1, 把temp里的值都放到arr里去
            arr[l+ind] = temp[ind];
        }
    }


}
