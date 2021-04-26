package hashTable查重;

import java.util.HashSet;
import java.util.Set;

public class uniqueEmailAddress {
    public int numUniqueEmails(String[] emails) {
        if(emails==null) return 0;
        Set<String> emls = new HashSet<>();

        for( String email : emails){
            StringBuilder sb = new StringBuilder();
            for(int i = 0 ; i < email.length() ; i++){
                char ch = email.charAt(i);
                switch(ch){
                    case '.':
                        break;

                    case '+':
                        while(ch != '@'){
                            i++;
                            ch=email.charAt(i);
                        }
                        sb.append(email.substring(i));
                        i= email.length();
                        break;

                    case '@':
                        sb.append(email.substring(i));
                        i= email.length();
                        break;

                    default:
                        sb.append(ch);
                }

            }
            emls.add(sb.toString());
        }

        return emls.size();


    }
















//    public int numUniqueEmails(String[] emails) {
//        Set<String> emls = new HashSet<>();
//        StringBuilder sb = new String
//
//        for( int i = 0 ;i < emails.length ; i++) {
//
//            String domain = emails[i].substring(emails[i].indexOf("@"),emails[i].length());
//
//            emails[i] = emails[i].substring(0,emails[i].indexOf("@"));
//
//
//            if(emails[i].indexOf(".")!=-1){
//                char[] c = emails[i].toCharArray();
//
//                for(char ch : c){
//                    int cnt= 0;
//                    if(ch != '.'){
//                        c[cnt] = ch;
//                    }
//                    cnt++;
//                }
//
//                emails[i]= c.toString();
//            }
//
//            if(emails[i].indexOf("+")!=-1){
//
//                emails[i] = emails[i].substring(0,emails[i].indexOf("+"));
//
//            }
//
//            emails[i] = emails[i].concat(domain);
//
//            emls.add(emails[i]);
//
//        }
//
//        return emls.size();
//
//    }
}
