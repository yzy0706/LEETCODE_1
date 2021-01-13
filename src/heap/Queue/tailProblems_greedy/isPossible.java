package heap.Queue.tailProblems_greedy;

import java.util.PriorityQueue;

public class isPossible {
    //用一个int[2]来记录每个不同的sequence的开始的数和结束的数, 因为是完全连续的所以他们之间的长度就是cur[1] – cur[0] + 1, 我们建立一个minHeap pq,
    // 优先比较长度, 如果长度相等则比较cur[0], 然后我们foreach loop每个在nums上的数,
    // 首先, while当前pq不是空的并且当前pq.cur[1] +1不等于当前的num(这里是peek()), 我们poll()当前的cur出来, 那么如果cur的长度还<3,
    // 直接return false, 我们要在第一个whileloop里把不可能跟当前数相连的并且长度大于三的直接poll掉;
    // 第二, 如果当前pq是空的或者当前peek出来的cur与当前的数是一样的, 那么我们offer一个新的int[]{num, num}进去
    // 第三种情况, 如果当下的cur[1]+1 == num, 也就是和num相连, 那么我们直接poll()出来把当前的cur的尾巴加上当前的num并offer回去, 这样一直foreach loop完,
    // 接下来我们再检查一下pq里是不是每个sequence的长度都大于三就好了
    public boolean isPossible(int[] nums) {
        // Comparator<int[]> com = (a, b)-> {
        //     if(a[1] == b[1]) return a[1] - a[0] - (b[1] - b[0]);
        //     return a[1] - b[1];
        // };
        PriorityQueue<int[]> tails = new PriorityQueue<>((a, b)-> {
            if(a[1] == b[1]) return a[1] - a[0] - (b[1] - b[0]);
            return a[1] - b[1];
        });
        for(int i : nums){
            while(!tails.isEmpty() && tails.peek()[1] + 1 < i){ //tails有东西且tails里面最后一个tail跟当前的number不相连
                int[] cur = tails.poll();
                if(cur[1] - cur[0] + 1 < 3) return false;
            }

            if(tails.isEmpty() || tails.peek()[1] == i){ //tails里面没东西或者tails最后一个尾巴跟当前number是一样的
                tails.offer(new int[]{i, i});
            }

            else if(tails.peek()[1] + 1 == i){ //tails的最后一个尾巴跟当前的是相连的
                int[] cur = tails.poll();
                tails.offer(new int[]{cur[0], i});
            }
        }
        while(!tails.isEmpty()){
            int[] cur = tails.poll();
            if(cur[1] - cur[0] + 1 < 3) return false;
        }
        return true;

    }

}
