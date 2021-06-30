package DataDesign;

import java.util.ArrayList;
import java.util.List;

public class NestedInteger {
    // @return true if this NestedInteger holds a single integer, rather than a nested list.
      public boolean isInteger(){
          return false;
      }

      public Integer getInteger(){
          return -1;
      }


      public List<NestedInteger> getList(){
          return new ArrayList<>();
      }
}
