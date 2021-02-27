package TwoPointer.sameDirection;

public class FirstUniqueCharacterInString {
    // 做法: two pointer, 用一个j在i+1开始找有没有跟他一样的
    // 1. 找得到一样的就把visited上i, j标记为true
    // 2. 如果j都浏览完了, visited[i] 还是false, 证明没找到一样的j,  那么i就是第一个distinct的字符
    // Runtime: O(n^2), Space: O(n)
    public int firstUniqChar(String s) {
        char[] cl = s.toCharArray();
        boolean[] visited = new boolean[s.length()];
        for(int i = 0; i < cl.length; i++){
            if(!visited[i]){
                for(int j = i + 1; j < cl.length; j++){
                    if(cl[j] == cl[i]){
                        visited[j] = true;
                        visited[i] = true;
                    }
                }
                if(!visited[i]) return i;
            }
        }
        return -1;
    }
}
