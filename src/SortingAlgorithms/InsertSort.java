package SortingAlgorithms;

public class InsertSort {
    //做法： i从0开始到arr.length-2, j从i+1开始到1， 每次都把arr[i+1]这个数如果小于之前的数就往前移动，如果不再小于前面的了就直接
    //break开始下一个循环
    //Runtime：最坏的情况是O(n^2)， Space是O(1）

    //上升
    public static void insertSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {  //重复n次
            for (int j = i + 1; j > 0; j--) { //从i+1开始到0， 每次都把浏览到的每一个小于前面一个的数两两替换
                if (arr[j] < arr[j - 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
        }
    }
    }

