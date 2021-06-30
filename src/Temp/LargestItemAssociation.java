package Temp;

import java.util.*;

// 做法： graph + dfs
// RUntime: O(n), Space: O(n^2)

public class LargestItemAssociation {
    class PairString {
        String first;
        String second;

        PairString(String first, String second)
        {
            this.first = first;
            this.second = second;
        }
    }

    public List<String> largestItemAssociation(List<PairString> itemAssociation) {
        HashMap<String, HashSet<String>> map = new HashMap<>();
        for (PairString pairs : itemAssociation) {
            if (pairs.first.equals(pairs.second)) continue;
            map.computeIfAbsent(pairs.first, val -> new HashSet<>()).add(pairs.second);
            map.computeIfAbsent(pairs.second, val -> new HashSet<>()).add(pairs.first);
        }
        List<String> result = new ArrayList<>();
        for (String name : map.keySet()) {
            HashSet<String> visited = new HashSet<>();
            List<String> cur = dfs(name, map, visited);
            if (cur.size() > result.size()) {
                result = cur;
            }
        }
        return result;
    }

    private static List<String> dfs(String name, Map<String, HashSet<String>> map, Set<String> visited) {
        if (!visited.add(name)) return new ArrayList<>();
        List<String> cur = new ArrayList<>();
        for (String dep : map.get(name)) {
            List<String> next = dfs(dep, map, visited);
            if (next.size() > cur.size()) {
                cur = next;
            }
        }
        cur.add(name);
        return cur;
    }
}
