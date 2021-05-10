package DataDesign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class SnapShotArray {
    //做法: 用TreeMap<Integer, Integer>[]来记录每一个位置的更新记录,
    // 1. 每一个位置一开始在0时间都是0
    // 2. get的时候用 arr[index].floorEntry(snap_id).getValue(); 来得到距离snap_id最近的时间在index上最后的值

    //Runtime: O(log(n)), Space: O(nlog(n));

    int time = 0;
    TreeMap<Integer, Integer>[] arr;

    public void SnapshotArray_treemap(int length) {
        time = 0;
        arr = new TreeMap[length];
        for(int i = 0; i < length; i++) {
            arr[i] = new TreeMap<>();
            arr[i].put(0, 0);
        }
    }

    public void set_treemap(int index, int val) {
        arr[index].put(time, val);
    }

    public int snap_treemap() {
        return time ++;
    }


    public int get_treemap(int index, int snap_id) {
        TreeMap<Integer, Integer> record = arr[index];
        int val = record.floorEntry(snap_id).getValue();
        return val;
    }






    //第一遍尝试用map来记录每一个id的时间的所有set的变化， 没过
    int times;
    HashMap<Integer, List<int[]>> map;

    public void SnapshotArray(int length) {
        times = 0;
        map = new HashMap<>();
    }

    public void set(int index, int val) {
        map.putIfAbsent(times, new ArrayList<>());
        List<int[]> changes = map.get(times);
        for(int i = 0; i < changes.size(); i++){
            if(changes.get(i)[0] == index) changes.remove(i);
        }
        changes.add(new int[]{index, val});
    }

    public int snap() {
        map.put(times ++, new ArrayList<>());
        List<int[]> cur = map.get(times);
        if(map.containsKey(times - 1) && map.get(times - 1).size() > 0) cur.addAll(map.get(times - 1));
        return times - 1;
    }


    public int get(int index, int snap_id) {
        while(snap_id >= 0 && !map.containsKey(snap_id)) snap_id --;
        if(snap_id < 0) return 0;
        List<int[]> changes = map.get(snap_id);
        for(int[] change : changes){
            if(change[0] == index) return change[1];
        }
        return 0;
    }
}
