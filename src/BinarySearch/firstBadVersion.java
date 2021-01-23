package BinarySearch;

public class firstBadVersion {
    public int firstBad(int n){
        int l = 1;
        int r = n;
        while(r>l){
            int mid = l + (l-r)/2;
            if(!isBadVersion(mid)) l = mid+1;
            else if(isBadVersion(mid)) r = mid;

        }
        return l;
    }
}
