package DataDesign;

import java.util.LinkedList;
import java.util.Queue;

public class DesignSnakeGame {
    //做法: 用LinkedList做的, 为了模拟贪吃蛇的各种功能, 用LinkedList<String>代表它的身体和各个occupied的目标(用String是因为用i + " " + j代表每个位置比用int[]{i, j}来带代表每个位置的方法更方便查重
    // 在move的方程中:
    // 1. 先移动得到新的head移动到的位置, 把尾部去掉(就算是吃到了新食物也是先移动并且去掉尾部, 移动完了再把尾部加回来), 检查head是不是出界了或者与身体重合了, 是的话return -1;
    // 2. 如果是吃到食物的话, 把tail加回来, foodIndex ++;
    // 3. 最后再把头加上, return score;

    // Runtime: O(n), Space: O(n);

    int m, n, score, i, j, foodIndex;
    LinkedList<String> snake;
    int[][] food;

    /** Initialize your data structure here.
     @param width - screen width
     @param height - screen height
     @param food - A list of food positions
     E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */

    public void SnakeGame(int width, int height, int[][] food) {
        i = 0;
        j = 0;
        snake = new LinkedList<>();
        snake.add(i + " " + j);
        n = width;
        m = height;
        foodIndex = 0;
        score = 0;
        this.food = food;
    }

    /** Moves the snake.
     @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
     @return The game's score after the move. Return -1 if game over.
     Game over when snake crosses the screen boundary or bites its body. */

    public int move(String direction) {
        if(direction.equals("U")) i --;
        else if(direction.equals("L")) j --;
        else if(direction.equals("R")) j ++;
        else if(direction.equals("D")) i ++;
        String head = i + " " + j;
        String tail = snake.removeLast();
        if(i < 0 || i >= m || j < 0 || j >= n || snake.contains(head)) return -1;
        if(foodIndex < food.length && i == food[foodIndex][0] && j == food[foodIndex][1]){
            score++;
            snake.addLast(tail);
            foodIndex++;
        }
        snake.addFirst(head);
        return score;
    }








    int[][] matrix;
    Queue<int[]> backupFood = new LinkedList<>();
    boolean[][] occupied;

    /** Initialize your data structure here.
     @param width - screen width
     @param height - screen height
     @param food - A list of food positions
     E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    public void SnakeGame_1(int width, int height, int[][] food) {
        i = 0;
        j = 0;
        snake = new LinkedList<>();
//        snake.add(new int[]{0, 0});
        n = width;
        m = height;
        score = 0;
        occupied = new boolean[m][n];
        occupied[0][0] = true;
        matrix = new int[m][n];
        int[] firstFood = food[0];
        matrix[firstFood[0]][firstFood[1]] = 1;
        for(int i = 1; i < food.length; i++) backupFood.offer(food[i]);
    }

    /** Moves the snake.
     @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
     @return The game's score after the move. Return -1 if game over.
     Game over when snake crosses the screen boundary or bites its body. */

    public int move_1(String direction) {
        if(direction.equals("U")) i --;
        else if(direction.equals("L")) j --;
        else if(direction.equals("R")) j ++;
        else if(direction.equals("D")) i ++;
        if(i < 0 || i >= m || j < 0 || j >= n) return -1;
        if(matrix[i][j] == 1){
            matrix[i][j] = 0;
            score++;
            // System.out.println(i + " " + j + " " + m + " " + n + " " + occupied[i][j]);
            if(!backupFood.isEmpty()){
                int[] next = backupFood.poll();
                matrix[next[0]][next[1]] = 1;
            }
//            snake.addFirst(new int[]{i, j});
        }
        else{
//            snake.addFirst(new int[]{i, j});
//            int[] tail = snake.removeLast();
//            occupied[tail[0]][tail[1]] = false;
        }
        if(occupied[i][j]) return -1;
        occupied[i][j] = true;
        return score;
    }
}
