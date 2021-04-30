package Temp;

import java.util.List;

public class MinDistanceBetweenRobots {
    public int minDists(int numRobots, List<Integer> positionX, List<Integer> positionY){
        int res = Integer.MAX_VALUE;
        if(positionX.size() < 1 || positionY.size() < 1 || positionX.size() != positionY.size()) return res;
        for(int i = 0; i < positionX.size(); i++){
            int curX = positionX.get(i);
            int curY = positionY.get(i);
            for(int j = i+1; j < positionX.size(); j++){
                int otherX = positionX.get(j);
                int otherY = positionY.get(j);
                int dist = (curX - otherX) * (curX - otherX) + (curY - otherY) * (curY - otherY);
                if(dist < res) res = dist;
            }
        }
        return res;

    }

}
