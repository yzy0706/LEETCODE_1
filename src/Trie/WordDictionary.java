package Trie;

public class WordDictionary {
    class TrieNode{
        TrieNode[] sons;
        boolean isWord;

        public TrieNode(){
            sons  = new TrieNode[26];
            isWord = false;
        }

    }

    public TrieNode root;
    /** Initialize your data structure here. */
    public WordDictionary() {
        root  = new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode t = root;
        char[] cl = word.toCharArray();
        for(char c: cl){
            int n = c - 'a';
            if(t.sons[n] == null) t.sons[n] = new TrieNode();
            t = t.sons[n];
        }
        t.isWord = true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return find(word, root);
    }

    public boolean find(String word, TrieNode t){
        TrieNode temp = t;
        char[] cl = word.toCharArray();
        for(int i = 0 ; i < cl.length ; i++){
            // if(cl[i] == '.' && i == cl.length - 1) return true;
            if(cl[i] == '.') {
                for(TrieNode ts : temp.sons){
                    if(ts != null){
                        if (find(word.substring(i+1), ts) == true) return true;
                    }
                }
                return false;
            }
            else{
                int n = cl[i] - 'a';
                if(temp.sons[n] == null) return false;
                if(temp.sons[n] != null) temp = temp.sons[n];
            }
        }
        return (temp.isWord && temp!= null);
    }



//    public TrieNode[] parents;
//    /** Initialize your data structure here. */
//    public WordDictionary() {
//        parents = new TrieNode[26];
//    }
//
//    /** Adds a word into the data structure. */
//    public void addWord(String word) {
//        TrieNode[] cur = parents;
//        TrieNode prev = new TrieNode();
//        char[] cl = word.toCharArray();
//        for(char c: cl){
//            int n = c - 'a';
//            if(cur[n] == null) cur[n] = new TrieNode();
//            prev = cur[n];
//            cur = prev.sons;
//        }
//        prev.isWord = true;
//    }
//
//    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
//    public boolean search(String word) {
//        return find(word, parents);
//    }
//
//    public boolean find(String word, TrieNode[] parents){
//        char[] cl = word.toCharArray();
//        TrieNode next = new TrieNode();
//        for(int i = 0 ; i < cl.length ; i++){
//            if(cl[i] == '.') {
//                for(TrieNode t : parents){
//                    if(t != null){
//                        if (find(word.substring(i+1), t.sons)) return true;
//                    }
//                }
//                return false;
//            }
//            else{
//                int n = cl[i] - 'a';
//                next = parents[n];
//                if(next == null) return false;
//                parents = next.sons;
//            }
//        }
//        return next.isWord;
//    }


//    class TrieNode{
//        TrieNode[] sons;
//        boolean isWord;
//
//        public TrieNode(){
//            sons = new TrieNode[26];
//            isWord = false;
//        }
//    }
//
//    TrieNode[] parents;
//
//    public WordDictionary() {
//        parents = new TrieNode[26];
//    }
//
//    /** Adds a word into the data structure. */
//    public void addWord(String word) {
//        if(word.length() < 1) return;
//        dfs(word, 0, parents);
//    }
//
//    public void dfs(String word, int i, TrieNode[] cur){
//        if(i == word.length() - 1){
//            Character c = word.charAt(i);
//            if(cur[c - 'a'] == null) cur[c - 'a'] = new TrieNode();
//            TrieNode next = cur[c - 'a'];
//            next.isWord = true;
//            return;
//        }
//        Character c = word.charAt(i);
//        cur[c - 'a'] = new TrieNode();
//        TrieNode next = cur[c - 'a'];
//        dfs(word, i + 1, next.sons);
//    }
//
//
//
//    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
//    public boolean search(String word) {
//        if(word.equals(".")) return true;
//        return check(word, 0, parents);
//    }
//
//    private boolean check(String word, int i, TrieNode[] cur){
//        if(i == word.length() - 1){
//            Character c = word.charAt(i);
//            if(c == '.') return true;
//            if(cur[c - 'a'] == null) return false;
//            TrieNode end = cur[c - 'a'];
//            if(end.isWord == true) return true;
//            return false;
//        }
//        Character c = word.charAt(i);
//        if(c == '.'){
//            for(TrieNode t : cur){
//                if(t != null){
//                    if(check(word, i + 1, t.sons)) return true;
//                }
//            }
//            return false;
//        }
//        TrieNode next = cur[c - 'a'];
//        if(next != null && check(word, i + 1, next.sons)){
//            return true;
//        }
//        return false;
//    }
}
