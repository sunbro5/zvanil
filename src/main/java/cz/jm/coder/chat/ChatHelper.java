package cz.jm.coder.chat;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ChatHelper {

    public static String getChatKey(ChatMessage message) {
        return getChatKey(message.getUserName(), message.getToUserName());
    }

    public static String getChatKey(String username, String toUsername) {
        if (username.compareTo(toUsername) > 0) {
            return username + toUsername;
        } else {
            return toUsername + username;
        }
    }

    public static void populateKeyUsername(ChatMessage message){
        message.setKey(getChatKey(message.getUserName(), message.getToUserName()));
    }
}
