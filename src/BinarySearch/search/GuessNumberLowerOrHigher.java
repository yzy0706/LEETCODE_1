package BinarySearch.search;

public class GuessNumberLowerOrHigher {
    //做法: 用binarySearch根据guess(mid)的大小来更新start和end, mid最好写start + (end - start)/2
    //Runtime: O(nlog(n)), space : O(1)

    public int guessNumber(int n) {
        int start = 1, end = n;
        while(start <= end){
            int mid = start + (end - start)/2;
            if(guessNumber(mid) == 0) return mid;
            else if(guessNumber(mid) == 1) start = mid + 1;
            else if(guessNumber(mid) == -1) end = mid - 1;
        }
        return -1;
    }
}
