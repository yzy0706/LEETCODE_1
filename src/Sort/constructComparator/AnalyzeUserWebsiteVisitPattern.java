package Sort.constructComparator;

import java.util.*;

public class AnalyzeUserWebsiteVisitPattern {
    //做法: 这题我理解错误了, 或者说这个题本身就有误导, 这题是在让我找到所有user所访问的网站的轨迹里连续的三个出现频率最高的情况
    //1. 新建一个pair(int timestamp, String web)的class
    //2. 先建立一个Map<String, List<Pair>> visitTrack, 把每个user对应的访问轨迹找出来, 做成一个list放在对应value里, 而且访问轨迹这个list应该按timestamp排列
    //3. 建立一个Map<String, Integer> cnt来记录每一个String sequence出现的频率;
    //4. 建立一个 String res = "" 来记录当前出现频率最高的sequence
    //5. forloop所有的key对应的所有的value, 也就是所有的List<pair> track, 代表着什么时间访问了什么网站, 根据timestamp sort这个list, 建立一个hashset查重所有的sequence组合, 准备开始permutation
    //6. 用三个forloop的方法permute (新的permutation的方法), 这个list上的每三个元素都三三组合一下变成String, 如果sequence里查重成功代表出现频率+1了, map里面也改变一下, 然后再判断1. res是不是"" 2. 或者当前cur的频率高于res 3.或者cur比res要 lexicographically smaller

    //Runtime: O(n^4), Space: O(n);

    class pair{
        int timestamp;
        String web;

        public pair(int timestamp, String web){
            this.timestamp = timestamp;
            this.web = web;
        }
    }

    public List<String> mostVisitedPattern_brutalForce(String[] username, int[] timestamp, String[] website) {
        HashMap<String, List<pair>> visitTrack = new HashMap<>();

        for(int i = 0; i < timestamp.length; i++){ //建好这个 <user, List<Pair>> 的map
            visitTrack.putIfAbsent(username[i], new ArrayList<pair>());
            visitTrack.get(username[i]).add(new pair(timestamp[i], website[i]));
        }

        Map<String, Integer> cnt = new HashMap<>();
        String res = "";

        for(String user : visitTrack.keySet()){
            Set<String> sequences = new HashSet<>();
            List<pair> track = visitTrack.get(user);
            track.sort((a, b) -> (a.timestamp - b.timestamp));
            for(int i = 0; i < track.size(); i++){
                for(int j = i + 1; j < track.size(); j++){
                    for(int k = j + 1; k < track.size(); k++){
                        String cur = track.get(i).web + " " + track.get(j).web + " " + track.get(k).web;
                        if(!sequences.contains(cur)){
                            cnt.putIfAbsent(cur, 0);
                            cnt.replace(cur, cnt.get(cur) + 1);
                            sequences.add(cur);
                        }
                        if(res.equals("") || cnt.get(cur) > cnt.get(res) || cnt.get(cur) == cnt.get(res) && cur.compareTo(res) < 0){
                            res = cur;
                        }
                    }
                }
            }
        }

        String[] list = res.split(" ");
        List<String> ret = new ArrayList<String>();
        for(String s : list) ret.add(s);
        return ret;
    }










    //第一遍自己写的， 用了两个map
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {

        HashMap<String, int[]> records = new HashMap<>();
        HashMap<String, Set<String>> users = new HashMap<>();


        for(int i = 0; i < timestamp.length; i++){
            String web = website[i];
            String visitor = username[i];

            if(!users.containsKey(web)){
                Set<String> visitors = new HashSet<>();
                visitors.add(visitor);
                users.put(web, visitors);
                records.put(web, new int[]{1, 1});
            }

            else{
                Set<String> visitors = users.get(web);
                if(visitors.contains(visitor)){
                    users.put(web, visitors);
                    int[] record = records.get(web);;
                    record[0] ++;
                    records.put(web, record);
                }
                else{
                    visitors.add(visitor);
                    users.put(web, visitors);
                    int[] record = records.get(web);
                    record[0] ++;
                    record[1] ++;
                    records.put(web, record);
                }
            }
        }

        List<String> result = new ArrayList<>(records.keySet());

        result.sort((a, b) -> {
            if(records.get(a)[1] == records.get(b)[1]) return a.compareTo(b);
            return records.get(b)[1] - records.get(a)[1];
        });

        System.out.println(result);

        result = result.subList(0, 3);

        // result.sort((a, b) -> {
        //     if(records.get(a)[0] == records.get(b)[0]) return a.compareTo(b);
        //     return records.get(a)[0] - records.get(b)[0];
        // });

        return result;
    }
}
