package BinarySearch.rotatedSortedArray;

public class RotatedArrayFindMin {
    //第二遍按照题目的写法写的
    //做法：碰到Rotated Array的题都是以mid为界要在一个在找sorted和没有sorted的过程， 这个题也不例外， 但这个题不可以先去比较左边是不是sorted再去比较右边
    // 因为假如我们第一遍根据不去sorted的部分找目标的原则， 我们把sorted好的左边跳过, 因为最小值肯定在没有无序的地方（最小值在
    // 那么我们在最终得到一个sorted好了的区间，我去找左边的最小值并return start时候， 我们又默认忽略了左边区间，
    // 所以我们就算忽略sorted的部分我们也应该先忽略右边
    // 所以跟寻找target不同的是我们在寻找最小值的过程中应该先比较 nums[mid] < nums[end]， 是的话就mid = end；
    // Runtime:  O(log(n))， space: O(1)

    public int findMin_2(int[] nums) {
        if(nums.length == 1) return nums[0];
        int start = 0, end = nums.length-1;
        int res = Integer.MAX_VALUE;
        while(start < end){
            int mid = start + (end-start)/2;
            if(nums[mid] < nums[end]){ //右边sorted
                end = mid;
            }
            else{
                start = mid+1;
            }
        }
        return nums[start];






        //跟第二个solution一样的解法 但是我把先比较mid和start来寻找unsorted改成了先比较start和mid， 结果就不对了
    public int findMin(int[] nums) {
        if(nums.length == 1) return nums[0];
        int start = 0, end = nums.length-1;
        int res = Integer.MAX_VALUE;
        while(start < end){
            int mid = start + (end-start)/2;
            if(nums[start] <= nums[mid]){ //左边sorted
                start = mid+1; //不考虑mid自己
            }
            else{//右边sorted, nums[start] >= nums[mid], mid自己也有可能是最小值
                end = mid;
            }
        }
        return nums[start];
    }


    //第一遍自己写的
    public int findMin(int[] nums) {
        if(nums.length == 1) return nums[0];
        int start = 0, end = nums.length-1;
        int res = Integer.MAX_VALUE;
        while(start <= end){
            int mid = start + (end-start)/2;
            // if(nums[mid] <= nums[start] && nums[])
            if(mid > 0 && nums[mid] < nums[mid-1]) { //如果nums[mid] < nums[mid-1]; 也就是他小于他左边那个, 那证明mid就是我们要找的起始点
                return nums[mid];
            }

            if(nums[mid] > nums[start]){  //左边是sorted, 我们要找的最小数在右边没有sorted的地方
                if(nums[start] > nums[end]){ //如果最右边小于最左边证明起始点在mid+1到end
                    start = mid+1;
                }
                else{
                    return nums[start]; //如果start > end而且[start, mid]是sorted, 那start就是最小值了
                }
            }

            if(nums[mid] < nums[start]){ //左边没有sorted, 我们要找的最小数子在左边
                if(nums[start] > nums[end]){
                    end = mid-1;
                }
                else{
                    return nums[start]; //右边是sorted, 然后start又还小于等于end, 那最小值不就是start了么
                }
            }
        }
        return nums[start];
    }
}
