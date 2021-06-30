package Math;

public class RobotRoundInCircle {
    // 做法：拿二位坐标来代替东西南北也是比较简便的办法，i所在坐标代表现在的方向。
    // 如果i > 0  或者 x,y变了坐标以后还等于0就代表经过一轮方向变了或者坐标回到原点了
    // Runtime: O(n), Space: O(1)
    public boolean isRobotBounded_2DimensionalVertices(String instructions) {
        int x = 0, y = 0, i = 0, d[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; //东西南北可以用四个二位坐标来代替
        for (int j = 0; j < instructions.length(); ++j)
            if (instructions.charAt(j) == 'R')
                i = (i + 1) % 4;
            else if (instructions.charAt(j) == 'L')
                i = (i + 3) % 4;
            else {
                x += d[i][0]; y += d[i][1];
            }
        return x == 0 && y == 0 || i > 0;
    }





    //做法: 把当前的指令都跑完, 如果最后能回到原点或者方向不是north的话证明能循环在一个圆里跑
    //Runtime: O(n), space: O(1);
    private Character dir;
    private int[] pos;
    public boolean isRobotBounded(String instructions) {
        pos = new int[]{0, 0};
        dir = 'N';

        for(int i = 0; i < instructions.length();  i++){
            move(instructions.charAt(i));
        }

        if((pos[0] == 0 && pos[1] == 0) || dir != 'N' ) return true;
        return false;
    }

    public void move(Character instruction){
        if(instruction == 'G'){
            if(dir == 'N') pos[1] ++;
            else if(dir == 'S') pos[1] --;
            else if(dir == 'W') pos[0] --;
            else if(dir == 'E') pos[0] ++;
        }
        else{
            if(instruction == 'L'){
                if(dir == 'N') dir = 'W';
                else if(dir == 'W') dir = 'S';
                else if(dir == 'S') dir = 'E';
                else if(dir == 'E') dir = 'N';
            }
            else if(instruction == 'R'){
                if(dir == 'N') dir = 'E';
                else if(dir == 'W') dir = 'N';
                else if(dir == 'S') dir = 'W';
                else if(dir == 'E') dir = 'S';
            }
        }
    }
}
