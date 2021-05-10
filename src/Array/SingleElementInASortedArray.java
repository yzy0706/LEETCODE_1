package Array;

public class SingleElementInASortedArray {
    // 做法: 用的binary search加上类似于merge sort的divide into sub problem,
    // 每次都把当前arr分成两段,排除m就是single element的情况, 然后去找除了m这两个以外的左右两段
    // 1. 从整段array nums开始, l = 0, r = len - 1; 每次都把当前的l, r放进check方程里检查:
    //   a. 如果l > r, 那么直接return -1, 代表当前这一段没有我们想要的结果
    //   b. 如果l == r, 那么当前l的位置就是我们要找的single element
    //   c. 当前m是(l + r)/2 :
    //      I. 如果m位置和它左边那个位置相等, 左边进行recursion的那一段是check(nums, l, m - 2), 右边是check(nums, m + 1, r);
    //      II. 如果m位置和它右边那个位置相等, 左边的recursion是check(nums, l, m - 1), 右边是check(nums, m + 2, r)
    //      III. 如果当前m和左右位置都不相等, 那么m就是那个single element, return nums[m];
    //   d. 检查左右两边recursion的结果left和right, 如果有一个不是-1就往上return回去

    // Runtime: O(log(n)), Space: O(n)

    int len;
    public int singleNonDuplicate(int[] nums) {
        len = nums.length;
        if(len == 1) return nums[0];
        return check(nums, 0, len - 1);
    }


    public int check(int[] nums, int l, int r){
        if(l > r) return -1;
        if(l == r) return nums[l];
        int m = (l + r)/2, left = 0, right = 0;
        if((m - 1 >= 0 && nums[m - 1] == nums[m])){
            left = check(nums, l, m - 2);
            right = check(nums, m + 1, r);
        }
        else if(m + 1 < len && nums[m + 1] == nums[m]){
            left = check(nums, l, m - 1);
            right = check(nums, m + 2, r);
        }
        else{
            return nums[m];
        }
        return left == -1 ? right : left;
    }


    // 更简便的方法：
    // 1. 如果m是偶数， 且它的值不等于m+1的值， 那single element肯定在[l, m]之间；
    // 2. 如果它的值的等于m+1的值， 那么single element肯定在[m+2, r]之间
    // Runtime: O(log(n)), Space: O(1);

    public int singleNonDuplicate_2(int[] nums) {
        len = nums.length;
        if(len == 1) return nums[0];
        int l = 0, r = len - 1;
        while(l < r){
            int m = (l + r) /2;
            if(m % 2 == 1) m --;
            if(nums[m] == nums[m + 1]) l = m + 2;
            else r = m;
            // if m is not equal to element on m + 1, and m is even, then single element must on its left(containing itself)
        }
        return nums[l];
    }
}
