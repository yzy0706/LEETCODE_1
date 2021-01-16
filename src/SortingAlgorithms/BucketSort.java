package SortingAlgorithms;
//做法： BucketSort主要是用一些有限的桶把所有的排序对象都装起来， 比如在这个题里就是用一个长度为10的cnt[]存储所有的数，
//1.这个cnt一开始会存储当前余数都为i的数的数量
//2.然后再叠加每一位cnt上的数， 保证每一位都是前面所有位数的和， 这样其实我们已经按当前位数进行了一个排列
//3.然后我们再浏览当前的arr， 把cnt上对应它当前余数的位置--
//4.然后temp[cnt[(arr[j]/agent)%10]]赋值为arr[j], 也就是按照当前的cnt来排列一下temp，这样按位数循环digitNum次， 最后再把temp赋值到arr上，
// Runtime : O(n*k) (k是digit的数量）， space是 O(n), temp的数量
public class BucketSort {
    public void bucketSort(int[] arr, int digit){
        int num = arr.length;
        int[] temp = new int[num];
        int agent = 1;

        for(int i = 0; i < digit; i++, agent *= 10){
            int[] cnt = new int[10];

            for(int j = 0; j < num; j++){
                cnt[(arr[j]/agent) % 10] ++; //找余数相同的数
            }
            for(int j = 1; j < 10; j++){
                cnt[j] += cnt[j-1];
            }
            for(int j = num-1; j >= 0; j--){
                cnt[(arr[j]/agent) % 10]--;
                temp[cnt[(arr[j]/agent) % 10]] = arr[j];
            }
        }
        for(int i = 0; i < temp.length; i++){
            arr[i] = temp[i];
        }
    }
}
