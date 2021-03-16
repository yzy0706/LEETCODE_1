package Array.matrix;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ValidSudoku {
    // 做法: 修改以后用HashMap<Integer, Set<Integer>>写的,
    // 分别用三个map: boxes, rows, cols记录每一个box, 每一row, 每一col已经碰到的所有的数, 如果出现重复了就return false; 如果能跑完就return true
    // 唯一的难点是怎么去查出当前是在哪一个box, 我这里是先通过i确定在哪一行的几个box, 再通过j去向右移动

    //Runtime: O(mn), Space: O(mn)

    public boolean isValidSudoku_fixed_mapAndHashSet(char[][] board) {
        HashMap<Integer, Set<Integer>> boxes = new HashMap<>();
        HashMap<Integer, Set<Integer>> rows = new HashMap<>();
        HashMap<Integer, Set<Integer>> cols = new HashMap<>();
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(board[i][j] != '.'){
                    int val = board[i][j] - '0';
                    int curBoxNum = 3*(i/3) + 1 + j/3;
                    boxes.putIfAbsent(curBoxNum, new HashSet<>());
                    if(!boxes.get(curBoxNum).add(val)){
                        return false;
                    }
                    rows.putIfAbsent(i, new HashSet<>());
                    if(!rows.get(i).add(val)){
                        return false;
                    }
                    cols.putIfAbsent(j, new HashSet<>());
                    if(!cols.get(j).add(val)){
                        return false;
                    }
                }
            }
        }
        return true;
    }


    public boolean isValidSudoku_map(char[][] board) {
        // init data
        HashMap<Integer, Integer>[] rows = new HashMap[9];
        HashMap<Integer, Integer> [] columns = new HashMap[9];
        HashMap<Integer, Integer> [] boxes = new HashMap[9];
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashMap<Integer, Integer>();
            columns[i] = new HashMap<Integer, Integer>();
            boxes[i] = new HashMap<Integer, Integer>();
        }

        // validate a board
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char num = board[i][j];
                if (num != '.') {
                    int n = (int)num;
                    int box_index = (i / 3 ) * 3 + j / 3;

                    // keep the current cell value
                    rows[i].put(n, rows[i].getOrDefault(n, 0) + 1);
                    columns[j].put(n, columns[j].getOrDefault(n, 0) + 1);
                    boxes[box_index].put(n, boxes[box_index].getOrDefault(n, 0) + 1);

                    // check if this value has been already seen before
                    if (rows[i].get(n) > 1 || columns[j].get(n) > 1 || boxes[box_index].get(n) > 1)
                        return false;
                }
            }
        }

        return true;
    }




//    //第一遍自己想用recursion写，但发现写不成，因为需要同时判断三个条件
//    public boolean isValidSudoku(char[][] board) {
//        boolean visited = new boolean[9][9];
//        boolean res = false;
//        Set<Integer> set = new HashSet<Integer>(9);
//        for(int i = 0; i < 9;i++){
//            for(int j = 0; j < 9; j++){
//
//            }
//        }
//
//
//
//    }
//
//
//    public void column(char[][] board,int i,int j,Set set){
//        if(i == 8 || i < 0 || j < 0 || visited[i][j]) return;
//        if(set.add(board[i][j])){
//            column
//        }
//    }



    //第二遍 用hashset写的
    public boolean isValidSudoku_second(char[][] board) {
        Map<Integer,Set<Integer>> rows = new HashMap<Integer,Set<Integer>>();
        Map<Integer,Set<Integer>> columns = new HashMap<Integer,Set<Integer>>();
        Map<Integer,Set<Integer>> boxes = new HashMap<Integer,Set<Integer>>();

        for(int a = 0 ; a < 9; a++){
            Set<Integer> curS = new HashSet<>();
            rows.put(a,curS);
            columns.put(a,curS);
            boxes.put(a,curS);
        }

        for(int i = 0 ; i < 9 ;i++){
            for(int j = 0; j < 9; j++){
                char cur = board[i][j];
                if(cur != '.'){
                    int num = cur -'0';
                    // int num = Character.getNumericValue(cur) ;
                    int box_num = (i/3)*3 + j/3;
                    if(num < 0 || num > 9) return false;
                    if(rows.get(i).contains(num)) return false;
                    else rows.get(i).add(num);
                    if(columns.get(j).contains(num)) return false;
                    else columns.get(j).add(num);
                    if(boxes.get(box_num).contains(num)) return false;
                    else boxes.get(box_num).add(num);

                }
            }
        }

        return true;
    }

    //leetcode给的答案
    public boolean isValidSudoku_leetcode(char[][] board) {
        // init data
        HashMap<Integer, Integer> [] rows = new HashMap[9];
        HashMap<Integer, Integer> [] columns = new HashMap[9];
        HashMap<Integer, Integer> [] boxes = new HashMap[9];
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashMap<Integer, Integer>();
            columns[i] = new HashMap<Integer, Integer>();
            boxes[i] = new HashMap<Integer, Integer>();
        }

        // validate a board
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char num = board[i][j];
                if (num != '.') {
                    int n = (int)num;
                    int box_index = (i / 3 ) * 3 + j / 3;

                    // keep the current cell value
                    rows[i].put(n, rows[i].getOrDefault(n, 0) + 1);
                    columns[j].put(n, columns[j].getOrDefault(n, 0) + 1);
                    boxes[box_index].put(n, boxes[box_index].getOrDefault(n, 0) + 1);

                    // check if this value has been already seen before
                    if (rows[i].get(n) > 1 || columns[j].get(n) > 1 || boxes[box_index].get(n) > 1)
                        return false;
                }
            }
        }

        return true;
    }
}
