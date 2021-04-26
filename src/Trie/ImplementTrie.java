package Trie;

public class ImplementTrie {
    //做法:  用的基础的trie, 其实本质上就是一个dfs, 从第一层Trie[] sons开始看每一层有没有当前对应的第一个字母
    // 1. 如果是要找整个词, 就要看最后一个字母对应的Trie next的isWord是不是true
    // 2. 如果是找prefix, 只要prefix能一步步检查到所有的开头字母, 一直到prefix的length()变成0就行
    // 注意:
    // 1. 我们做dfs的时候都是用StringBuilder对当前的word进行修改, 然后看当前的这一层Trie[], 因为最开始我们能用到的就是储存所有开头字母的第一层sons, 进入下一层也是用next.sons[]
    // 2. 我们insert的时候某个字母位置curSons[head - 'a']的Trie()可能之前已经因为insert别的词而存在了, 所以我们要先看是不是之前就已经有一个Trie()了, 是的话就不要新建一个Trie, 直接在这个已经存在的Trie的next上面修改

    // Runtime: O(n), Space: O(n)
    /** Initialize your data structure here. */
    class Trie {
        boolean isWord;
        Trie[] sons;

        public Trie() {
            this.isWord = false;
            this.sons = new Trie[26];
        }
    }

    Trie[] sons;

    /** Inserts a word into the trie. */
    public void insert(String word) {
        dfs(word, sons);
    }

    public void dfs(String word, Trie[] curSons){
        if(word.length() == 0) return;
        StringBuilder sb = new StringBuilder(word);
        Character head = word.charAt(0);
        sb.deleteCharAt(0);
        Trie next = curSons[head - 'a'] == null ? new Trie() : curSons[head - 'a'];
        if(sb.length() == 0) next.isWord = true;
        curSons[head - 'a'] = next;
        dfs(sb.toString(), next.sons);
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        return check(word, sons);
    }

    private boolean check(String word, Trie[] curSons){
        if(word.length() == 0) return false;
        StringBuilder sb = new StringBuilder(word);
        Character head = word.charAt(0);
        sb.deleteCharAt(0);
        if(curSons[head - 'a'] == null) return false;
        Trie next = curSons[head - 'a'];
        if(sb.length() == 0 && next.isWord) return true;
        return check(sb.toString(), next.sons);
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return checkPre(prefix, sons);
    }

    private boolean checkPre(String prefix, Trie[] curSons){
        if(prefix.length() == 0) return true;
        StringBuilder sb = new StringBuilder(prefix);
        Character head = prefix.charAt(0);
        sb.deleteCharAt(0);
        if(curSons[head - 'a'] == null) return false;
        Trie next = curSons[head - 'a'];
        return checkPre(sb.toString(), next.sons);
    }
}

