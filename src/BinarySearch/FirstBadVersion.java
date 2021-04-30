package BinarySearch;

public class FirstBadVersion {
    //做法: binary search最好都用 l + (r - l)/2; 因为(r + l)可能大于Integer.MAX_VALUE;
    //Runtime: O(nlog(n)), Space:O(1)

    public int firstBadVersion(int n) {
        int l = 1, r = n;
        while(l <= r - 1){
            int mid = l + (r - l)/2;
            if(!isBadVersion(mid)) l = mid + 1;
            else r = mid;
        }
        return l;
    }

    public boolean isBadVersion(int version){
        return false;
    }
//    public int firstBad(int n){
//        int l = 1;
//        int r = n;
//        while(r>l){
//            int mid = l + (l-r)/2;
//            if(!isBadVersion(mid)) l = mid+1;
//            else if(isBadVersion(mid)) r = mid;
//
//        }
//        return l;
//    }
}
