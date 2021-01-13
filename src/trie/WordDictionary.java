package trie;

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
        return find(word,root);
        // char[] cl = word.toCharAray();
        // TrieNode() t = root;
        // for(int i = 0 ; i < cl.length ; i++){
        //     if (c == '.'){
        //         if(i == cl.length - 1) return true;
        //         else{
        //             String remain = word.substring(i+1);
        //             return()
        //         }
        //     }
        //     else
        // }

    }

    public boolean find(String word, TrieNode t){
        TrieNode temp = t;
        char[] cl = word.toCharArray();
        for(int i = 0 ; i < cl.length ; i++){
            // if(cl[i] == '.' && i == cl.length - 1) return true;
            if(cl[i] == '.') {

                for(TrieNode ts : temp.sons){
                    if(ts != null){
                        if (find(word.substring(i+1),ts) == true) return true;
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
}
