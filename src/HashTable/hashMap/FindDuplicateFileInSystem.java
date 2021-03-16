package HashTable.hashMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindDuplicateFileInSystem {
    // 做法: 用Map<String, List<String>> HashMap做的
    // 把每一个content作为key, 把任何一个包含它的目录 + “/” + 文件名加到value里面, 也就是任意一个可以导出当前内容的路径
    // Runtime: O(n), 如果每个path都包含n个file的话也可以是O(n^2) , Space: O(n)

    public List<List<String>> findDuplicate(String[] paths) {
        List<List<String>> res = new ArrayList<>();
        if(paths.length < 1) return res;
        Map<String, List<String>> map = new HashMap<>();

        for(String path : paths){
            String[] files = path.split(" ");
            String dir = files[0];
            for(int i = 1; i < files.length; i++){
                String file = files[i];
                int lBracket = file.indexOf('('), rBracket = file.indexOf(')');
                String txt = file.substring(0, lBracket);
                String content = file.substring(lBracket+1, rBracket);
                map.putIfAbsent(content, new ArrayList<String>());
                String guide = dir + "/" + txt;
                map.get(content).add(guide);
            }
        }

        for(List<String> val : map.values()){
            if(val.size() >= 2) res.add(val);
        }

        return res;
    }
}
