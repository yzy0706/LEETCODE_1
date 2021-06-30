package Temp;

import java.util.LinkedList;
import java.util.List;

public class Turnsites {
    public int[] getTimes(int[] times, int[] directions) {
        // Store index of the cars
        List<Integer> entry = new LinkedList<>();
        List<Integer> exit = new LinkedList<>();
        for (int i = 0; i < times.length; i++) {
            if (directions[i] == 0) {
                entry.add(i);
            } else exit.add(i);
        }
        int curTime = -1;
        int prevDir = 1;
        int[] ret = new int[times.length];
        while (!entry.isEmpty() && !exit.isEmpty()) {
            int curEntry = entry.get(0);
            int curExit = exit.get(0);
            int curEnterTime = Math.max(times[curEntry], curTime);
            int curExitTime = Math.max(times[curExit], curTime);

            if (curEnterTime < curExitTime) {
                ret[curEntry] = curEnterTime;
                prevDir = 0;
                entry.remove(0);
                curTime = curEnterTime + 1;
            } else if (curExitTime < curEnterTime) {
                ret[curExit] = curExitTime;
                prevDir = 1;
                exit.remove(0);
                curTime = curExitTime + 1;
            } else {
                if (prevDir == 0) {
                    ret[curEntry] = curEnterTime;
                    entry.remove(0);
                    curTime = curEnterTime + 1;
                } else {
                    ret[curExit] = curExitTime;
                    exit.remove(0);
                    curTime = curExitTime + 1;
                }
            }
        }

        while (!entry.isEmpty()) {
            int curEntry = entry.remove(0);
            int curEnterTime = Math.max(times[curEntry], curTime);
            ret[curEntry] = curEnterTime;
            curTime = curEnterTime + 1;
        }

        while (!exit.isEmpty()) {
            int curExit = exit.remove(0);
            int curExitTime = Math.max(times[curExit], curTime);
            ret[curExit] = curExitTime;
            curTime = curExitTime + 1;
        }

        return ret;
    }
}
