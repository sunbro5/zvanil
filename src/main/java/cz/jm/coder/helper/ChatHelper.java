package cz.jm.coder.helper;

public class ChatHelper {
    
    public static String getChatKey(String username, String toUsername){
        if(username.compareTo(toUsername) > 0){
            return username + toUsername;
        } else {
            return toUsername + username;
        }
    }
    
}
