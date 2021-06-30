package Temp;

public class RoverControl {
    public static int roverMove(String[] cmnds, int n){
        int row=0;
        int col=0;
        for(String cmnd:cmnds) {
            if(cmnd=="RIGHT") {
                if(col<n-1)col=col+1;
            }
            else if(cmnd=="LEFT") {
                if(col>0)col=col-1;
            }
            else if(cmnd=="UP") {
                if(row>0)row=row-1;
            }
            else {
                if(row<n-1) row=row+1;
            }
        }
        return (row*n)+col;
    }
}
