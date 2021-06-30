package Temp;

import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

public class ChemicalDeliverySystem {
    // 做法： 用HashMap和keySet来做的
    // 1. 记录每一种flask的index和对应的所有marking,放到HashMap里
    // 2. 在map里foreach loop每一种flask， 根据requirements, 用flaskMarkings.ceiling(requirement)找到每一种flask最接近requirement的marking,
    // 计算每一种flask的totalWaste:
    //      a. 如果totalWaste == minWaste, 比较flaskIndex和res
    //      b. 如果totalWaste < minWaste, 更新minWaste和res
    // 注意：
    //      1. 要正常使用treeSet就不要改comparator
    //      2.找最接近requirement的marking的时候不能用treeSet.higher()； 而是要用treeSet.ceiling(); higher()是不包括等于requirement的marking的

    // Runtime: O(nlog(k)m) Space: O(nklog(k)), n是flask的种类数量， k是一个flask最多的marking数量， m是requirement的数量

    public int chemicalDeliverySystem(int numOrders, List<Integer> requirements, int flaskTypes, int totalMarks, List<int[]> markings){
        HashMap<Integer, TreeSet<Integer>> flasks = new HashMap<>();
        for(int i = 0; i < totalMarks; i++){
            int[] curMark = markings.get(i);
            flasks.putIfAbsent(curMark[0], new TreeSet<>());
            flasks.get(curMark[0]).add(curMark[1]);
        }
        int minWaste = Integer.MAX_VALUE, res = Integer.MAX_VALUE;
        for(int flaskIndex : flasks.keySet()) {
            TreeSet<Integer> flaskMarkings = flasks.get(flaskIndex);
            int totalWaste = 0;
            for (int requirement : requirements) {
                int closestMark = flaskMarkings.ceiling(requirement) == null ? -1 : flaskMarkings.ceiling(requirement);
                if (closestMark == -1) {
                    totalWaste = -1;
                    break;
                }
                totalWaste += closestMark - requirement;
            }
            if (totalWaste == -1) {
                continue;
            } else {
                if (totalWaste == minWaste) {
                    if (flaskIndex < res) {
                        res = flaskIndex;
                    }
                } else if (totalWaste < minWaste) {
                    minWaste = totalWaste;
                    res = flaskIndex;
                }
            }
        }
        return minWaste == Integer.MAX_VALUE ? -1 : res;
    }
}
