package HashTable.hashSet;

import java.util.HashSet;

public class IntersectionOfTwoArrays {
    //做法: 用一个Hashset装nums1的数字, 一个HashSet<>()来装nums1, nums2重复的数字, 最后一个个添加到res里
    //Runtime: O(n), Space: O(n), n是nums1和nums2里比较长的那个

    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        for(int i : nums1) set1.add(i);
        HashSet<Integer> common = new HashSet<>();
        for(int i : nums2) if(set1.contains(i)) common.add(i);
        int[] res = new int[common.size()];
        int pos = 0;
        for(int i : common) res[pos ++] = i;
        return res;
    }
}
