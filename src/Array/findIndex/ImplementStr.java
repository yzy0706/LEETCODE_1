package Array.findIndex;

public class ImplementStr {

    public int str(String haystack, String needle){
        if (needle.equals(null)){
            return 0;
        }
        return(haystack.indexOf(needle,0));

    }
}
