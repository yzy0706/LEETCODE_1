package Trie;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordSearchII {

    class TrieNode{
        boolean isWord;
        TrieNode[] sons;

        public TrieNode(){
            isWord = false;
            sons = new TrieNode[26];
        }
    }

    public void insert1(String word){
        TrieNode t = root;
        char[] cl = word.toCharArray();
        for(char c : cl){
            int n = c - 'a';
            if(t.sons[n] == null) t.sons[n] = new TrieNode();
            t = t.sons[n];
        }
        t.isWord = true;
    }

    public TrieNode root;

    public List<String> wordSearchII(char[][] board, List<String> words) {
        int length  =  board.length;
        int width =  board[0].length;
        if ( length < 1 || width < 1 || words.size() < 1) return new ArrayList<String>();

        root = new TrieNode();
        List<String> res = new ArrayList<>();

        for(String word :  words){
            insert(word);
        }


        for(int i = 0 ; i < length ; i++){
            for (int j = 0 ; j <  width ; j ++){
                boolean[][] visited = new boolean[length][width];
                search(board,res,length,width,i,j,new StringBuilder(), root, visited);
            }
        }

        return res;

    }


    public void search(char[][] board, List<String> res, int length , int width, int i , int j , StringBuilder sb , TrieNode node , boolean[][] visited){
        if(i < 0 || i >= length || j < 0 || j >= width) return;
        if(visited[i][j]) return;

        char c = board[i][j];
        int n = c - 'a';
        if(node.sons[n] != null){
            visited[i][j] = true;
            sb.append(c);
            node = node.sons[n];
            if(node.isWord) res.add(sb.toString());
        }

        search(board,res,length,width,i+1,j,sb,node,visited);
        search(board,res,length,width,i-1,j,sb,node,visited);
        search(board,res,length,width,i,j+1,sb,node,visited);
        search(board,res,length,width,i,j-1,sb,node,visited);

        sb.setLength(sb.length() -1 );
        visited[i][j] = false;

    }


    //第二遍
//    class TrieNode{
//        TrieNode[] sons;
//        boolean isWord;
//
//        public TrieNode(){
//            sons = new TrieNode[26];
//            isWord = false;
//        }
//
//    }


//    public TrieNode root;


    public void insert(String word){
        TrieNode t = root;
        char[] cl = word.toCharArray();
        for(char c: cl){
            int n = c - 'a';
            if(t.sons[n] == null) t.sons[n] = new TrieNode();
            t = t.sons[n];
        }
        t.isWord = true;
    }


    public List<String> findWords(char[][] board, String[] words) {
        root = new TrieNode();
        // int[] dx = new int[]{-1,0,1};
        // int[] dy = new int[]{-1,0,1};
        int length = board.length;
        int width = board[0].length;
        if(length < 1 || width < 1 || words.length < 1) return new ArrayList<String>();
        // List<String> res = new ArrayList<>();
        Set<String> res = new HashSet<>();

        for(String word : words){
            insert(word);
        }


        for(int i = 0 ; i < length ; i ++){
            for (int j = 0 ; j  < width ; j ++){
                boolean[][] visited = new boolean[length][width];
                search(res, board, length, width, visited, new StringBuilder(), i, j, root);
            }
        }

        return new ArrayList<String>(res);

    }

    private void  search(Set<String> res, char[][] board, int length, int width, boolean[][] visited, StringBuilder sb, int i, int j, TrieNode node){
        if(i < 0 || i >= length || j < 0 || j >= width) return;
        if(visited[i][j]) return;

        char c = board[i][j];
        int n = c - 'a';

        if(node.sons[n] != null){
            visited[i][j] = true;
            sb.append(c);
            node = node.sons[n];
            if(node.isWord) res.add(sb.toString());
            // System.out.println(sb.toString());
            search(res, board, length , width , visited, sb, i-1, j , node);
            search(res, board, length , width , visited, sb, i+1, j , node);
            search(res, board, length, width, visited, sb, i, j-1, node);
            search(res, board, length, width, visited, sb, i, j+1, node);
            sb.deleteCharAt(sb.length()-1);
            visited[i][j] = false;
        }
    }








}

