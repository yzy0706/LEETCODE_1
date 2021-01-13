package SortingAlgorithms;

public class HeapSort {
    //做法： 首先我们找到最后一个父亲节点， 也就是arr.length/2 - 1, 从这个节点开始往前每一个节点做heapify的活动， （heapify就是在
    //找寻当前这个节点start和他的左右两个叶子节点 start*2+1, start*2+1 +1哪个更大， 如果找到了右边叶子或者左边叶子更大， 就跟当前的
    //根节点置换， 然后再顺着这个比较大的节点往这个子树的底端在一定范围内延伸排序）； 然后我们再从最后一个节点， 也就是arr.length-1开始，
    //依次和0置换， 并且从0开始往后heapify， heapify的最远节点由arr.length-

    //Runtime： 我们从一个节点开始往左叶子节点或者右叶子节点依次heapify，那么根据二元树状图的深度最多就是log(n)次， 这样的行为平均下来最多
    //O(nlog(n))次， Space： 没有用到什么别的数据结构所以是O(1)

    //下降
    private void heapSort(int[] arr) {
        //根据本身的arr的前半部分的父节点们一起总体heapyfy一下这个arr
        int lastFather = arr.length / 2 - 1; //找到最后一个父节点
        int lastIdx = arr.length - 1;
        for (int i = lastFather; i >= 0; i++) { //根据这个父节点的位置向前一直成堆
            maxHeapify(i, lastIdx, arr);
        }
        //从最后一个数开始， 跟0置换， 0是已知的最大值
        for (int i = lastIdx; i > 0; i--) {
            swap(0, i, arr); //置换0和i
            maxHeapify(0, i - 1, arr); //从0开始从新排序成堆， 一直排到i-1
        }
    }

    private static void swap(int l, int r, int[] arr){
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }


    private static void maxHeapify(int start, int len, int[] arr){
        int lSon = start*2+1;
        int rSon = lSon+1;
        int curMaxSon = lSon;
        if(lSon > len) return; //如果左叶子大于查询的最后一个数了可以直接return
        if(rSon <= len && arr[rSon] > arr[lSon]) curMaxSon = rSon; //如果右叶子大于左叶子了那么查询的方向就变成右边了
        if(arr[curMaxSon] > arr[start]){
            swap(curMaxSon, start, arr);
            maxHeapify(curMaxSon, len, arr);
        }
    }

}
