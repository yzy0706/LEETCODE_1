package Temp;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ThrottlingGateway {
    static class Item {
        int time;
        int count;

        public Item(int time, int count) {
            this.time = time;
            this.count = count;
        }
    }

    static class Limiter {
        int duration;
        int limit;
        ArrayDeque<Item> queue = new ArrayDeque<>();
        int accum = 0;

        public Limiter(int duration, int limit) {
            this.duration = duration;
            this.limit = limit;
        }

        public void append(int time, int count) {
            while (!queue.isEmpty() && queue.peekFirst().time <= time - duration)
                accum -= queue.pollFirst().count;
                queue.offerLast(new Item(time, count));
                accum += count;
        }
    }
    public static int throttlingGateway(List<Integer> transactionTime) {
        List<Limiter> qs = Arrays.asList(new Limiter(1, 3), new Limiter(10, 20), new Limiter(60, 60));
        if(transactionTime.size() < 1) return 0;
        int dropped = 0;
        Map<Integer, List<Integer>> times = transactionTime.stream().collect(Collectors.groupingBy(i -> i));
        for (Map.Entry<Integer, List<Integer>> e : times.entrySet()) {
            int time = e.getKey();
            int count = e.getValue().size();
            for (Limiter q : qs)
                q.append(time, count);
                dropped += Math.max(0, qs.stream().mapToInt(q -> q.accum - q.limit).max().getAsInt());
        }
        return dropped;
    }
}
