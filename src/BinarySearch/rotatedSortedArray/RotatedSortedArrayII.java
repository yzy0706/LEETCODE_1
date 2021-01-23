package BinarySearch.rotatedSortedArray;
//做法： Rotate Sorted Array的中心做法其实是去找target是在sorted好的那一半里面， 还是没有sorted那一半里面。
// 1. 我们先比较if(nums[start] < nums[mid]), 就是左边那一半是sorted的，这是第一个条件， 判断哪一边是sorted的
// 2. 然后我们判断target是不是在unsorted那一半， if(target < nums[start] || target > nums[mid]) start = mid+1；
// 3. 否则的话target就是在sorted的左边， end = mid-1;
// 4. 同样的我们判断右边那一半是sorted， 然后里面两个判断再进行一遍
// 5. 假设既不是以mid为界限左边是sorted， 也不是右边是sorted， 因为nums[start]和nums[mid]是一样的那么我们只能把start++,
// 去缩小我们的范围， 因为sorted好的部分肯定在中间。
// Runtime: 最差的情况是  O(n), 也就是到了最后一位才找得到我想要的target，之前全都是连续性的一样的数字， 我得一直start++, Space: O(1)

public class RotatedSortedArrayII {
    public boolean search_whileloop(int[] nums, int target) {
        //跟答案一模一样的
        int start = 0, end = nums.length - 1;
        while(start <= end){
            int mid = start + (end-start)/2;
            if(nums[mid] == target) return true;
            //如果左边是排好序的
            if(nums[start] < nums[mid]){
                if(target > nums[mid] || target < nums[start]){
                    start = mid+1;
                }
                else{
                    end = mid-1;
                }
            }
            //左边是没排好序的
            else if(nums[start] > nums[mid]){

                if(target > nums[end] || target < nums[mid]){
                    end = mid - 1;
                }
                else{
                    start = mid + 1;
                }
            }
            else start++;
        }

        return false;
    }


    //第二遍根据答案写的recursion解法
    public boolean search(int[] nums, int target) {
        return binarySearch(target, nums, 0, nums.length-1);
    }

    public boolean binarySearch(int target, int[] nums, int start, int end){
        if(start > end) return false;
        int mid = start + (start + end)/2;
        if(nums[mid] == target) return true;

        if(nums[start] < nums[mid]){ //左边是排序的
            if(target < nums[start] || target > nums[mid]) return binarySearch(target, nums, mid+1, end);//target在rotated里面, 先考虑在混乱里的情况
            else return binarySearch(target, nums, start, mid-1);
        }


        // if(target >= nums[start] && target <= nums[mid]){
        //     return binarySearch(target, nums, start, mid-1);
        // }
        // else return binarySearch(target, nums, mid+1, end);

        else if(nums[start] > nums[mid]){//右边是排好序的,我们先考虑左边混乱的地方的情况
            if(target < nums[mid] || target > nums[end]) return binarySearch(target, nums, start, mid-1);
            else return binarySearch(target, nums, mid+1, end);


            // if(target >= nums[mid] && target <= nums[end]) return binarySearch(target, nums, mid+1, end);
            // return binarySearch(target, nums, start, mid-1);
        }

        return binarySearch(target, nums, start+1, end);
        //假设nums[start] == nums[mid], 那我们只能往后移动
    }







    public boolean search(int[] nums, int target) {
        return binarySearch(target, nums, 0, nums.length-1);
    }

    public boolean binarySearch(int target, int[] nums, int start, int end){
        if(start > end) return false;
        int mid = start + (start + end)/2;
        if(nums[mid] == target) return true;

        if(nums[mid] > nums[start]){ //左边是排序的
            if(target < nums[start] || target > nums[mid]) return binarySearch(target, nums, mid+1, end);//target在rotated里面, 先考虑在混乱里的情况
            else return binarySearch(target, nums, start, mid-1);
        }


        // if(target >= nums[start] && target <= nums[mid]){
        //     return binarySearch(target, nums, start, mid-1);
        // }
        // else return binarySearch(target, nums, mid+1, end);

        else if(nums[start] > nums[mid]){//右边是排好序的,我们先考虑左边混乱的地方的情况
            if(target > nums[end] || target < nums[mid]) return binarySearch(target, nums, start, mid-1);
            else return binarySearch(target, nums, start, mid-1);
            // if(target >= nums[mid] && target <= nums[end]) return binarySearch(target, nums, mid+1, end);
            // return binarySearch(target, nums, start, mid-1);
        }

        return binarySearch(target, nums, start+1, end); //假设nums[start] == nums[mid], 那我们只能往后移动
    }
}





    //第一遍自己写的
    public boolean search(int[] arr, int target) {
        int start = 0, end = arr.length - 1;
        while(start <= end){
            int mid = start + (end - start)/2;

            if(arr[mid] > target){
                if(arr[end] >= arr[mid]){
                    end = mid-1;
                }
                else{
                    if(arr[end] >= target){
                        start = mid+1;
                    }
                    else{
                        end = mid-1;
                    }
                }
            }

            else if(arr[mid] < target){
                if(arr[end] >= arr[mid]){
                    start = mid+1;
                }
                else{
                    if(arr[end] >= target){
                        start  = mid+1;
                    }
                    else{
                        end = mid-1;
                    }
                }
            }

            else return true;
        }
        return false;
    }
}
