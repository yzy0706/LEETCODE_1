package Array.string;

import java.util.ArrayList;
import java.util.List;

public class ZigzagConversion {
    //做法: 主要就是用到了一个List<StringBuilder>来代表每一行的string, 然后按照往下或者往上的顺序逐一分配
    //Runtime: O(n), Space: O(n);

    public String convert_reviewed(String s, int numRows) {
        if(s.length() < 1 || numRows == 1) return s;
        List<StringBuilder> list = new ArrayList<>();
        for(int i = 0; i < numRows; i++) list.add(new StringBuilder());
        boolean up = false;
        int curRow = 0;
        for(Character c : s.toCharArray()){
            list.get(curRow).append(c);
            if(curRow == 0 || curRow == numRows - 1) up = !up;
            if(up) curRow++;
            else curRow--;
        }
        StringBuilder res = new StringBuilder();
        for(StringBuilder sb : list) res.append(sb);
        return res.toString();
    }




    // 这个方法主要是去建立一个拥有numRows个StringBuilder()的Array，然后分别在各个StringBuilder()上分配character， 如果排列趋势是向上的话就往前浏览StringBuilder,
    // 反之就向后浏览
    public String convert(String s, int numRows) {
        if(numRows == 1) return s;
        List<StringBuilder> rows = new ArrayList<>();
        for(int i = 0; i < Math.min(numRows, s.length()); i++) rows.add(new StringBuilder());

        int curRow = 0;
        Boolean down = false;
        for(char c: s.toCharArray()){
            rows.get(curRow).append(c);
            if(curRow == 0 || curRow == numRows - 1) down = !down;
            curRow += down? 1 : -1;
        }

        StringBuilder res = new StringBuilder();
        for(StringBuilder row : rows) res.append(row);

        return res.toString();
    }
}
