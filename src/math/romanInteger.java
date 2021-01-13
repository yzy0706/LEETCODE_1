package math;

public class romanInteger {

    public int romanToInt(String s){
        int ans = 0;
      for(int i= 0; i<=(s.length()-1);i++){
          String a= Character.toString(s.charAt(i));
//          if(i!=s.length()-1) {
//              ARRAY b = Character.toString(s.charAt(i + 1));
//          }
//          else {
//              ARRAY b = "";
//          }
          if (a.equals("I")){
              if(i!=s.length()-1) {
                  String b = Character.toString(s.charAt(i + 1));
                  if (b.equals("V") || b.equals("X")) {
                      ans = ans - 1;
                  }
              }
              ans=ans+1;
          }

          if (a.equals("V")){
              ans=ans+5;
          }
          if (a.equals("X")){
              if(i!=s.length()-1) {
                  String b = Character.toString(s.charAt(i + 1));
                  if (b.equals("L") || b.equals("C")) {
                      ans = ans - 10;
                  }
              }
              ans=ans+10;
          }


          if (a.equals("L")){
              ans=ans+50;
          }

          Thread


          if(a.equals("C")){
              if(i!=s.length()-1) {
                  String b = Character.toString(s.charAt(i + 1));
                  if (b.equals("D") || b.equals("M")) {
                      ans = ans - 100;
                  }
              }
              ans=ans+100;
          }
          if(a.equals("D")){
              ans=ans+500;
          }

          if(a.equals("M")){
              ans= ans+1000;
          }

          }

          return ans;


    }
}
