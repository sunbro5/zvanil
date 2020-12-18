package cz.jm.coder.chat;

import cz.jm.coder.chat.ChatMessage;

import java.util.List;

public interface ChatRepository {

    void addChatMessage(ChatMessage message);

    List<ChatMessage> getChatMessagesWithUser(String username, String withUsername);
}
