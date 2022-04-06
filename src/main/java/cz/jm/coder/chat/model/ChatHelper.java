package cz.jm.coder.chat.model;

import cz.jm.coder.chat.model.ChatMessage;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ChatHelper {

    private static final String USER_DIVIDER = "-";

    public static String getChatKey(ChatMessage message) {
        return getChatKey(message.getUserName(), message.getToUserName());
    }

    public static String getChatKey(String username, String toUsername) {
        if (username.compareTo(toUsername) > 0) {
            return username + USER_DIVIDER + toUsername;
        } else {
            return toUsername + USER_DIVIDER + username;
        }
    }

    public static List<String> getChatUsers(String chatKey){
        return Arrays.asList(chatKey.split(USER_DIVIDER));
    }

    public static boolean userInConversation(String chatKey, String username){
        return Arrays.asList(chatKey.split(USER_DIVIDER)).contains(username);
    }

    public static void populateKeyUsername(ChatMessage message){
        message.setKey(getChatKey(message.getUserName(), message.getToUserName()));
    }
}
