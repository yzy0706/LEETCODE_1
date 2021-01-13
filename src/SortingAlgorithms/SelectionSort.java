package SortingAlgorithms;

public class SelectionSort {
    //做法：从i开始找后面的最小值并调换
    // 从i开始一直循环到arr.length-2, 找下面所有数的最小值，一直找到arr.length-1，如果找到的minIndex != i的话就把minIndex和i
    //交换一下
    //Runtime：O(n^2), space: O(1)

    //上升

    public static void selectionSort(int[] arr){
        for(int i = 0; i < arr.length-1; i++){ //arr.length - 1;
            int minIndex = i;

            for(int j  = i; j < arr.length; j++) { // arr.length， 找到到末尾最小的
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            if(minIndex != i){ //如果minIndex != i, 证明找到了最小值， 置换一下
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }
}
