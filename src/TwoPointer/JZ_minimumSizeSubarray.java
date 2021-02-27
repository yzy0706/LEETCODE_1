package TwoPointer;

public class JZ_minimumSizeSubarray {
    //普通算法
    public int minSubArrayLen(int s, int[] nums) {
        int res = Integer.MAX_VALUE;
        if(nums.length < 1) return 0;

        for(int i = 0 ; i < nums.length ; i++ ){
            if(nums[i]  >= s) return 1;
            else {
                int cnt = nums[i];
                int cur = 1;
                for (int j = i+1 ; j < nums.length ; j++){
                    cnt += nums[j];
                    cur ++ ;
                    if(cnt >= s){
                        if(cur <= res) res = cur;
                        break;
                    }
                }
            }
            if(i == nums.length - 1 && res == Integer.MAX_VALUE) return 0;
        }

        return res;
    }



//
//    //同向双指针算法
//    int res = Integer.MAX_VALUE;
//        if(nums.length < 1) return -1;
//    int j = 0 , cur = 0 ;
//
//        for(int i = 0 ; i < nums.length ; i++ ){
//        if(nums[i]  >= s) return 1;
//        else {
//            cur += nums[i];
//            if(cur < s) continue;
//            while(j < i){
//                cur -= nums[j];
//                if(cur - nums[j] >= s && cur - nums[j+1] >= s) {
//                    j ++ ;
//                }
//                else if( cur - nums[j] >=s && cur - nums[j+1] < s){
//                    int len = i - j + 1;
//                    if(len < res) res = len;
//                    continue;
//                }
//            }
//
//        }
//    }
//            if(res == Integer.MAX_VALUE) return -1;
//
//            return res;
//}
}
