package SortingAlgorithms;

public class BubbleSort {
    // 从倒数第一个开始， 如果arr[j] < arr[j-1]， 则第j个数跟他左边的数两两交换， 以保证小的数都在前面， 这个操作一直循环n-1次
    //也就是从n-1到1都跟他之前的交换
    // Runtime： i从0开始< arr.length -1， 所以一共是n-1次， j从n-1开始到1， 也是n-1次， 所以是O(n^2), space就是temp O(1）

    //上升
    public static void bSort(int[] arr){
        for(int i = 0; i < arr.length-1; i++){ //这里是 < length-1 因为是循环n-1次
            for(int j = arr.length-1; j > 0; j--){
                if(arr[j] < arr[j-1]){
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }
            }
        }
    }

    //优化算法是在里面加一个flag来表示在这一趟排序的过程中有没有出现两两交换， 如果出现了代表我们的我们不需要再去进行接下来的排序了
    //Runtime: 最坏情况仍然是最小的数在最后一位所以我们要把他移动n-1次才能移动到第一位， 所以还是O(n), 但实际上我们的运算时间已经
    // 减少了， space仍然是O(1)
    public static void bSort1(int[] arr){
        for(int i = 0; i < arr.length-1; i++){ //arr.length-1
            boolean flag = false;
            for(int j = arr.length-1; j > 0; j--){ //arr.length-1, 其实相当于arr.length， 因为也是最后一位
                if(arr[j] < arr[j-1]){
                    int temp =arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                    flag = true;
                }
            }
            if(flag == false) break;
        }
    }



}