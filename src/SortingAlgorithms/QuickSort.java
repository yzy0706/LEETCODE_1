package SortingAlgorithms;

public class QuickSort {
    // 做法：挖坑问题， arr[l]是key的大小
    // 主要是在把arr分成比key大和小的两部分去找这个key最终应该在哪里，先从后面j开始--找比key小的放到前面去，
    // 再从前面i开始++把大于等于key的放到后面去，顺便把左右的大小也根据key的大小初步分配了， 接下来再做(l, i-1)和(i+1, r)的recursion
    // 的循环就会快很多
    // Runtime： 左右分配排序是log(n)， 最多分配n次， 所以是nlog(n)

    // 由小到大
    public static void quickSort(int[] arr, int l, int r){
        if(l >= r) return;
        int i = l, j = r, key = arr[l];
        while(i < j){
            while(arr[j] >= key && i < j){
                j--;
            }
            if(i < j){
                arr[i++] = arr[j];
            } //找到第一个j < key, 并且去填i

            while(arr[i] < key && i < j){  //i < j的条件一定要加不然i，j可能会交错而过
                i++;
            }
            if(i < j){
                arr[j--] = arr[i];
            } //找到第一个i >= key， 并且去填j
        }
        arr[i] = key; //填来填去当i == j的时候， 就拿key把它填上
        quickSort(arr, l, i-1); //i的左右已经都是小于和大于等于key的数了， 接下来我们就要根据除了这个数的区间继续sort
        quickSort(arr, i+1, r);
    }
}
