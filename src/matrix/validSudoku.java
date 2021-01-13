package matrix;

public class validSudoku {


    //第一遍自己想用recursion写，但发现写不成，因为需要同时判断三个条件
    public boolean isValidSudoku(char[][] board) {
        boolean visited = new boolean[9][9];
        boolean res = false;
        Set<Integer> set = new HashSet<Integer>(9);
        for(int i = 0; i < 9;i++){
            for(int j = 0; j < 9; j++){

            }
        }



    }


    public void column(char[][] board,int i,int j,Set set){
        if(i == 8 || i < 0 || j < 0 || visited[i][j]) return;
        if(set.add(board[i][j])){
            column
        }
    }



    //第二遍 用hashset写的
    public boolean isValidSudoku(char[][] board) {
        Map<Integer,Set<Integer>>rows = new HashMap<Integer,Set<Integer>>(),columns = new HashMap<Integer,Set<Integer>>(),boxes = new HashMap<Integer,Set<Integer>>();

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

                    // if(!(num > 0 && num <= 9 && rows.get(i).add(num) && columns.get(j).add(num) && boxes.get(box_num).add(num))) {
                    //     return false;
                    //  }
                }
            }
        }

        return true;
    }

    //leetcode给的答案
    public boolean isValidSudoku(char[][] board) {
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
