package DivideAndConquer;

public class MajorityElement {
    // 做法: 如果单单是找频率大于n/2的数而且一定会有这个数, 我们也可以根据一个数的频率是不是大于当前所有已经找到的数的频率/2来divide这个array
    // 1. 我们用 major = nums[0], freq = 1;
    // 2.
    //      a. 如果freq == 0, 证明对于之前找到的这个major来说, nums里有超过之前它之前的频率的不同的数, 证明到当前位置它并不是频率 > n/2的数, 所以我们相当于把前面的结果清空, major = nums[i], freq ++;
    //      b. 如果freq != 0, 证明这个major还是大于前面所有数的数量的一半的
    //          1. 如果当前这个数 nums[i] == major, freq ++;
    //          2. 如果当前这个数  nums[i] != major, freq --; 找到了一个跟当前major不一样的数

    // Runtime: O(n), Space: O(1)
    public int majorityElement(int[] nums) {
        int major = nums[0], freq = 1;
        for(int i = 1; i < nums.length; i++){
            if(freq == 0){
                major = nums[i];
                freq ++;
            }
            else if(nums[i] == major){
                freq ++;
            }
            else{
                freq --;
            }
        }
        return major;
    }

}
