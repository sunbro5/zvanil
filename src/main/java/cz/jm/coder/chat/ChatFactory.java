package cz.jm.coder.chat;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ChatFactory {

    public static ChatMessage createChatMessage(String username, String toUsername, String messsage){
        return ChatMessage.builder()
                .key(ChatHelper.getChatKey(username, toUsername))
                .userName(username)
                .toUserName(toUsername)
                .message(messsage)
                .build();
    }
    
}
