package Array.twoFactorSum;

import java.util.HashMap;
import java.util.Map;

class TwoSum {


//        public int[] twosum(int[] nums, int target){
//
//            Map <Integer,Integer> ansMap = new HashMap();
//            for(int i=0;i<nums.length;i++){
//                ansMap.put(nums[i],i);
//            }
//            for(int i=0; i< nums.length;i++){
//                if((ansMap.containsKey(target-nums[i]))&&ansMap.get(target-nums[i]) != i){
//                    return new int[] {ansMap.get(target-nums[i]), i}；
//                }
//                return null;
//
//        }



            public int[] twoSum(int[] nums, int target) {
                int[] ans= null;
                Map<Integer, Integer> map = new HashMap<>();
                for(int i=0; i<nums.length;i++){
                    map.put(nums[i],i);
                }
                for(int i=0;i<nums.length;i++){
                    if(map.containsKey(target-nums[i])&&map.get(target-nums[i])!=i){
                        ans= new int[] {i,map.get(target-nums[i])};
                        break;
                    }
                    continue;
                }
                return ans;
            }





//
//            public int[] twoSum(int[] nums, int target) {
//                Map<Integer, Integer> map = new HashMap<>();
//                for (int i = 0; i < nums.length; i++) {
//                    map.put(nums[i], i);
//                }
//                for (int i = 0; i < nums.length; i++) {
//                    int complement = target - nums[i];
//                    if (map.containsKey(complement) && map.get(complement) != i) {
//                        return new int[] { i, map.get(complement) };
//                    }
//                }
//                throw new IllegalArgumentException("No two sum solution");
//            }


}
